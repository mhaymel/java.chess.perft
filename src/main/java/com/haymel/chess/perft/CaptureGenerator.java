package com.haymel.chess.perft;

import static com.haymel.chess.eval.Evaluation.mvvLva;
import static com.haymel.chess.perft.Field.isValid;
import static com.haymel.chess.perft.File.A;
import static com.haymel.chess.perft.File.H;
import static com.haymel.chess.perft.Generator.*;
import static com.haymel.chess.perft.Init.file;
import static com.haymel.chess.perft.MoveTables.*;
import static com.haymel.chess.perft.Piece.*;

public final class CaptureGenerator {

   private final Chess c;

   public CaptureGenerator(Chess c) {
      this.c = c;
   }

   public static CaptureGenerator NewCaptureGenerator(Chess chess) {
      return new CaptureGenerator(chess);
   }

   public void execute() {
      c.mc = c.firstMove[c.ply];
      genPieces();
      c.firstMove[c.ply + 1] = c.mc;
   }

   private void genPieces() {
      for (int from = 0; from < 64; from++)
         if (c.color[from] == c.side) generate(from);
   }

   private void generate(int from) {
      switch (c.board[from]) {
         case pawn:     genPawn(from);                            break;
         case knight:   gen(knightMoves, from);                   break;
         case bishop:   genSliding(bishopMoves, from, 4);  break;
         case rook:     genSliding(rookMoves, from, 4);    break;
         case queen:    genSliding(queenMoves, from, 8);   break;
         case king:     gen(kingMoves, from);                     break;
         default:                                                 break;
      }
   }

   private void genPawn(int from) {
      if (file[from] > A) pawnCapture(from, leftCapture[c.side]);
      if (file[from] < H) pawnCapture(from, rightCapture[c.side]);
   }

   private void pawnCapture(int from, int step) {
      int to = from + step;
      if (c.isOpponent(to))
         addMove(from, to);
   }

   private void genSliding(int[][] moves, int from, int count) {
      for (int i = 0; i < count; i++)
         genDirection(from, i, moves);
   }

   private void genDirection(int from, int direction, int[][] moves) {
      int to = moves[from][direction];
      while (isValid(to)) {
         if (c.isOpponent(to)) {
            addMove(from, to);
            return;
         }
         if (!c.isEmpty(to)) return;
         to = moves[to][direction];
      }
   }

   private void gen(int[][] moves, int from) {
      int to = moves[from][0];
      for (int direction = 1; isValid(to); direction++) {
         if (c.isOpponent(to)) addMove(from, to);
         to = moves[from][direction];
      }
   }

   private void addMove(int from, int to) {
      c.moveList[c.mc].from = from;
      c.moveList[c.mc].to = to;
      c.moveList[c.mc].score = CAPTURE_SCORE + mvvLva[c.board[from]][c.board[to]];
      c.mc++;

   }

}
