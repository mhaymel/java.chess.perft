package com.haymel.chess.perft.move;

import com.haymel.chess.perft.Chess;
import com.haymel.chess.perft.Piece;

import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Color.white;
import static com.haymel.chess.perft.Direction.*;
import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.Init.file;
import static com.haymel.chess.perft.Init.rank;
import static com.haymel.chess.perft.Piece.*;
import static com.haymel.chess.perft.move.KingMoves.kingMoves;
import static com.haymel.chess.perft.move.KnightMoves.knightMoves;
import static com.haymel.chess.perft.move.QueenRookBishopMoves.queenRookBishopMoves;

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
         case pawn:     genPawn(from);    break;
         case knight:   genKnight(from);  break;
         case bishop:   genBishop(from);  break;
         case rook:     genRook(from);    break;
         case queen:    genQueen(from);   break;
         case king:     genKing(from);    break;
         default:                         break;
      }
   }

   private void genBishop(int from) {
      genDirection(from, NE);
      genDirection(from, SE);
      genDirection(from, SW);
      genDirection(from, NW);
   }

   private void genRook(int from) {
      genDirection(from, NORTH);
      genDirection(from, EAST);
      genDirection(from, SOUTH);
      genDirection(from, WEST);
   }

   private void genQueen(int from) {
      genRook(from);
      genBishop(from);
   }

   private void genEnPassant() {
      if (isInvalid(c.enPassantField)) return;
      if (itsWhitesTurn()) {
         if (c.enPassantField > a6 && isWhitePawn(c.enPassantField - 9))
            addMove(c.enPassantField - 9, c.enPassantField);
         if (c.enPassantField < h6 && isWhitePawn(c.enPassantField - 7))
            addMove(c.enPassantField - 7, c.enPassantField);
      } else {
         if (c.enPassantField > a3 && isBlackPawn(c.enPassantField + 7))
            addMove(c.enPassantField + 7, c.enPassantField);
         if (c.enPassantField < h3 && isBlackPawn(c.enPassantField + 9))
            addMove(c.enPassantField + 9, c.enPassantField);
      }
   }

   private void genKnight(int from) {
      genKnightOrKing(from, knightMoves);
   }

   private void genKing(int from) {
      genKnightOrKing(from, kingMoves);
   }

   private void genCastling() {
      if (itsWhitesTurn()) {
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

   private void genDirection(int from, int direction) {
      int to = queenRookBishopMoves[from][direction];
      while (isValid(to)) {
         if (isEmpty(to)) addMove(from, to);
         else if (isOpponent(to))
            addMove(from, to);
         else return;
         to = queenRookBishopMoves[to][direction];
      }
   }

   private void genKnightOrKing(int from, int[][] moves) {
      int to = moves[from][NORTH];
      for (int direction = NORTH + 1; isValid(to); direction++) {
         if (isEmptyOrOpponent(to)) addMove(from, to);
         to = moves[from][direction];
      }
   }

   private boolean queenSideCastling(int color) {
      return c.gameList[c.hply].castle.queenside[color];
   }

   private boolean kingSideCastling(int color) {
      return c.gameList[c.hply].castle.kingside[color];
   }

   private void addMove(int from, int to) {
      c.moveList[c.mc].start = from;
      c.moveList[c.mc].dest = to;
      c.moveList[c.mc].promotion = empty;
      c.mc++;
   }

   private void addPawnMove(int from, int to) {
      if (to >= a8 || to <= h1) {
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

   private boolean itsWhitesTurn() {
      return c.side == white;
   }

   private boolean isOpponent(int field) {
      return c.color[field] == c.xside;
   }

   private boolean isEmpty(int field) {
      return c.board[field] == Piece.empty;
   }

   private boolean isEmptyOrOpponent(int field) {
      return isEmpty(field) || isOpponent(field);
   }

   private boolean isWhitePawn(int field) {
      return c.color[field] == white && c.board[field] == pawn;
   }

   private boolean isBlackPawn(int field) {
      return c.color[field] == black && c.board[field] == pawn;
   }
}
