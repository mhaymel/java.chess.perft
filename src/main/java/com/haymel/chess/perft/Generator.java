package com.haymel.chess.perft;

import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Color.white;
import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.File.A;
import static com.haymel.chess.perft.File.H;
import static com.haymel.chess.perft.Init.file;
import static com.haymel.chess.perft.Init.rank;
import static com.haymel.chess.perft.MoveTables.*;
import static com.haymel.chess.perft.Piece.*;

public final class Generator {

   public static final int up = 8;
   public static final int down = -up;
   private static final int right = 1;
   public static final int rightDown = right + down;
   private static final int left = -right;
   public static final int leftDown = left + down;
   public static final int leftUp = left + up;
   public static final int rightUp = right + up;

   private static final int[] pawnStep = {up, down};
   private static final int[] pawnDoubleStep = {2 * up, 2 * down};

   private final Chess c;

   public Generator(Chess c) { this.c = c; }

   public static Generator NewGenerator(Chess chess) { return new Generator(chess); }

   public void execute() {
      c.mc = c.firstMove[c.ply];
      genEnPassant();
      genCastling();
      genPieces();
      c.firstMove[c.ply + 1] = c.mc;
   }

   private void genPieces() {
      for (int from = 0; from < 64; from++)
         if (c.color[from] == c.side) generate(from);
   }

   private void generate(int from) {
      switch (c.board[from]) {
         case pawn:     genPawn(from);                          break;
         case knight:   gen(knightMoves, from);                 break;
         case bishop:   genSliding(bishopMoves, from,4); break;
         case rook:     genSliding(rookMoves, from,4);   break;
         case queen:    genSliding(queenMoves, from,8);  break;
         case king:     gen(kingMoves, from);                   break;
         default:                                               break;
      }
   }

   private void genEnPassant() {
      if (isInvalid(c.enPassantField)) return;
      if (c.itsWhitesTurn()) {
         if (c.enPassantField > a6) whiteEnPassant(leftDown);
         if (c.enPassantField < h6) whiteEnPassant(rightDown);
      } else {
         if (c.enPassantField > a3) blackEnPassant(leftUp);
         if (c.enPassantField < h3) blackEnPassant(rightUp);
      }
   }

   private void whiteEnPassant(int direction) {
      int from = c.enPassantField + direction;
      if (c.isWhitePawn(from)) addMove(from, c.enPassantField);
   }

   private void blackEnPassant(int direction) {
      int from = c.enPassantField + direction;
      if (c.isBlackPawn(from)) addMove(from, c.enPassantField);
   }

   private void genCastling() {
      if (c.itsWhitesTurn()) {
         if (c.kingSideCastling(white) && c.isEmpty(f1) && c.isEmpty(g1)) addMove(e1, g1);
         if (c.queenSideCastling(white) && c.isEmpty(d1) && c.isEmpty(c1) && c.isEmpty(b1)) addMove(e1, c1);
      } else {
         if (c.kingSideCastling(black) && c.isEmpty(f8) && c.isEmpty(g8)) addMove(e8, g8);
         if (c.queenSideCastling(black) && c.isEmpty(d8) && c.isEmpty(c8) && c.isEmpty(b8)) addMove(e8, c8);
      }
   }

   private static final int[] leftCapture = { left + pawnStep[white], left + pawnStep[black] };
   private static final int[] rightCapture = { right + pawnStep[white], right + pawnStep[black] };

   private void genPawn(int from) {
      int to = from + pawnStep[c.side];
      if (c.isEmpty(to)) {
         addPawnMove(from, to);
         if (rank[c.side][from] == 1) pawnDoubleStep(from);
      }
      if (file[from] > A) pawnCapture(from, leftCapture[c.side]);
      if (file[from] < H) pawnCapture(from, rightCapture[c.side]);
   }

   private void pawnDoubleStep(int from) {
      int to = from + pawnDoubleStep[c.side];
      if (c.isEmpty(to)) addMove(from, to);
   }

   private void pawnCapture(int from, int step) {
      int to = from + step;
      if (c.isOpponent(to)) addPawnMove(from, to);
   }

   private void genSliding(int[][] moves, int from, int count) {
      for (int i = 0; i < count; i++)
         genDirection(from, i, moves);
   }

   private void genDirection(int from, int direction, int[][] moves) {
      int to = moves[from][direction];
      while (isValid(to)) {
         if (c.isEmpty(to))         addMove(from, to);
         else if (c.isOpponent(to)) addMove(from, to);
         else return;
         to = moves[to][direction];
      }
   }

   private void gen(int[][] moves, int from) {
      int to = moves[from][0];
      for (int direction = 1; isValid(to); direction++) {
         if (c.isEmptyOrOpponent(to)) addMove(from, to);
         to = moves[from][direction];
      }
   }

   private void addMove(int from, int to) {
      c.moveList[c.mc].from = from;
      c.moveList[c.mc].to = to;
      c.moveList[c.mc].promotion = empty;
      c.mc++;
   }

   private void addPawnMove(int from, int to) {
      if (isFirstOrLastRow(to)) {
         addPromotion(from, to, queen);
         addPromotion(from, to, rook);
         addPromotion(from, to, bishop);
         addPromotion(from, to, knight);
      } else
         addMove(from, to);
   }

   private void addPromotion(int from, int to, int piece) {
      c.moveList[c.mc].from = from;
      c.moveList[c.mc].to = to;
      c.moveList[c.mc].promotion = piece;
      c.mc++;
   }

   public static boolean isFirstOrLastRow(int to) { return to >= a8 || to <= h1; }

}
