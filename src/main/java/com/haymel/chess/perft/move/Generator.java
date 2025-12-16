package com.haymel.chess.perft.move;

import com.haymel.chess.perft.Chess;
import com.haymel.chess.perft.Piece;

import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Color.white;
import static com.haymel.chess.perft.Direction.NORTH;
import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.Init.file;
import static com.haymel.chess.perft.Init.rank;
import static com.haymel.chess.perft.Piece.*;
import static com.haymel.chess.perft.move.BishopMoves.bishopMoves;
import static com.haymel.chess.perft.move.KingMoves.kingMoves;
import static com.haymel.chess.perft.move.KnightMoves.knightMoves;
import static com.haymel.chess.perft.move.RookMoves.rookMoves;

public final class Generator {

   private final Chess c;

   private static final int up = 8;
   private static final int[] singleStep = {up, -up};
   private static final int[] doubleStep = {2 * up, -2 * up};
   private static final int[] left = {7, -9};
   private static final int[] right = {9, -7};

   public Generator(Chess c) {
      this.c = c;
   }

   public static Generator NewGen(Chess chess) {
      return new Generator(chess);
   }

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
         case pawn:     genPawn(from);             break;
         case knight:   gen(knightMoves, from);    break;
         case bishop:   genQrb(bishopMoves, from); break;
         case rook:     genQrb(rookMoves, from);   break;
         case queen:    genQrb(bishopMoves, from);
                        genQrb(rookMoves, from);   break;
         case king:     gen(kingMoves, from);      break;
         default:                                  break;
      }
   }

   private void genEnPassant() {
      if (isInvalid(c.enPassantField)) return;
      if (c.itsWhitesTurn()) {
         if (c.enPassantField > a6) whiteEnPassant(right[white]);
         if (c.enPassantField < h6) whiteEnPassant(left[white]);
      } else {
         if (c.enPassantField > a3) blackEnPassant(right[black]);
         if (c.enPassantField < h3) blackEnPassant(left[black]);
      }
   }

   private void whiteEnPassant(int direction) {
      int from = c.enPassantField - direction;
      if (isWhitePawn(from)) addMove(from, c.enPassantField);
   }

   private void blackEnPassant(int direction) {
      int from = c.enPassantField - direction;
      if (isBlackPawn(from)) addMove(from, c.enPassantField);
   }

   private void genCastling() {
      if (c.itsWhitesTurn()) {
         if (kingSideCastling(white) && isEmpty(f1) && isEmpty(g1)) addMove(e1, g1);
         if (queenSideCastling(white) && isEmpty(d1) && isEmpty(c1) && isEmpty(b1)) addMove(e1, c1);
      } else {
         if (kingSideCastling(black) && isEmpty(f8) && isEmpty(g8)) addMove(e8, g8);
         if (queenSideCastling(black) && isEmpty(d8) && isEmpty(c8) && isEmpty(b8)) addMove(e8, c8);
      }
   }

   private void genPawn(int from) {
      if (isEmpty(from + singleStep[c.side])) {
         addPawnMove(from, from + singleStep[c.side]);
         if (rank[c.side][from] == 1 && isEmpty(from + doubleStep[c.side])) addMove(from, from + doubleStep[c.side]);
      }
      if (file[from] > 0 && isOpponent(from + left[c.side])) addPawnMove(from, from + left[c.side]);
      if (file[from] < 7 && isOpponent(from + right[c.side])) addPawnMove(from, from + right[c.side]);
   }

   private void genQrb(int[][] moves, int from) {
      for(int i = 0; i < 4; i++)
         genDirection(from, i, moves);
   }

   private void genDirection(int from, int direction, int[][] moves) {
      int to = moves[from][direction];
      while (isValid(to)) {
         if (isEmpty(to)) addMove(from, to);
         else if (isOpponent(to))
            addMove(from, to);
         else return;
         to = moves[to][direction];
      }
   }

   private void gen(int[][] moves, int from) {
      int to = moves[from][NORTH];
      for (int direction = NORTH + 1; isValid(to); direction++) {
         if (isEmptyOrOpponent(to)) addMove(from, to);
         to = moves[from][direction];
      }
   }

   private void addMove(int from, int to) {
      c.moveList[c.mc].start = from;
      c.moveList[c.mc].dest = to;
      c.moveList[c.mc].promotion = empty;
      c.mc++;
   }

   private void addPawnMove(int from, int to) {
      if (isPromotion(to)) {
         addPromotion(from, to, queen);
         addPromotion(from, to, rook);
         addPromotion(from, to, bishop);
         addPromotion(from, to, knight);
      } else
         addMove(from, to);
   }

    private void addPromotion(int from, int to, int piece) {
      c.moveList[c.mc].start = from;
      c.moveList[c.mc].dest = to;
      c.moveList[c.mc].promotion = piece;
      c.mc++;
   }

   private boolean isPromotion(int to) { return to >= a8 || to <= h1; }

   private boolean isOpponent(int field) { return c.color[field] == c.xside; }

   private boolean isEmpty(int field) { return c.board[field] == Piece.empty; }

   private boolean isEmptyOrOpponent(int field) { return isEmpty(field) || isOpponent(field); }

   private boolean isWhitePawn(int field) { return c.color[field] == white && c.board[field] == pawn; }

   private boolean isBlackPawn(int field) { return c.color[field] == black && c.board[field] == pawn; }

   private boolean queenSideCastling(int color) { return c.gameList[c.hply].castle.queenside[color]; }

   private boolean kingSideCastling(int color) { return c.gameList[c.hply].castle.kingside[color]; }

}
