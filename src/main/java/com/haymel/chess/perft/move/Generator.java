package com.haymel.chess.perft.move;

import com.haymel.chess.perft.Chess;

import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Color.white;
import static com.haymel.chess.perft.Direction.NORTH;
import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.File.A;
import static com.haymel.chess.perft.File.H;
import static com.haymel.chess.perft.Init.file;
import static com.haymel.chess.perft.Piece.*;
import static com.haymel.chess.perft.move.BishopMoves.bishopMoves;
import static com.haymel.chess.perft.move.KingMoves.kingMoves;
import static com.haymel.chess.perft.move.KnightMoves.knightMoves;
import static com.haymel.chess.perft.move.RookMoves.rookMoves;

public final class Generator {

   private static final int up = 8;
   private static final int upUp = up + up;
   private static final int down = -up;
   private static final int downDown = down + down;
   private static final int right = 1;
   private static final int rightDown = right + down;
   private static final int left = -right;
   private static final int leftDown = left + down;
   private static final int leftUp = left + up;
   private static final int rightUp = right + up;
   private final Chess c;

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
         case pawn:
            genPawn(from);
            break;
         case knight:
            gen(knightMoves, from);
            break;
         case bishop:
            genQrb(bishopMoves, from);
            break;
         case rook:
            genQrb(rookMoves, from);
            break;
         case queen:
            genQrb(bishopMoves, from);
            genQrb(rookMoves, from);
            break;
         case king:
            gen(kingMoves, from);
            break;
         default:
            break;
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
      if (isWhitePawn(from)) addMove(from, c.enPassantField);
   }

   private void blackEnPassant(int direction) {
      int from = c.enPassantField + direction;
      if (isBlackPawn(from)) addMove(from, c.enPassantField);
   }

   private void genCastling() {
      if (c.itsWhitesTurn()) {
         if (kingSideCastling(white) && c.isEmpty(f1) && c.isEmpty(g1)) addMove(e1, g1);
         if (queenSideCastling(white) && c.isEmpty(d1) && c.isEmpty(c1) && c.isEmpty(b1)) addMove(e1, c1);
      } else {
         if (kingSideCastling(black) && c.isEmpty(f8) && c.isEmpty(g8)) addMove(e8, g8);
         if (queenSideCastling(black) && c.isEmpty(d8) && c.isEmpty(c8) && c.isEmpty(b8)) addMove(e8, c8);
      }
   }

//   private void genPawn(int from) {
//      if (c.itsWhitesTurn()) genPawn(from, up);
//      else genPawn(from, down);
//   }

   private void genPawn(int from) {
      if (c.itsWhitesTurn()) {
         if (c.isEmpty(from + up)) {
            addPawnMove(from, from + up);
            if (from <= h2) pawnDoubleStep(from, upUp);
         }
         if (file[from] > A) pawnCapture(from, leftUp);
         if (file[from] < H) pawnCapture(from, rightUp);
      } else {
         if (c.isEmpty(from + down)) {
            addPawnMove(from, from + down);
            if (from >= a7) pawnDoubleStep(from, downDown);
         }
         if (file[from] > A) pawnCapture(from, leftDown);
         if (file[from] < H) pawnCapture(from, rightDown);
      }
   }

   private void genPawn(int from, int dir) {
      if (c.isEmpty(from + dir)) {
         addPawnMove(from, from + dir);
         if (from <= h2) pawnDoubleStep(from, dir + dir);
      }
      if (file[from] > A) pawnCapture(from, left + dir);
      if (file[from] < H) pawnCapture(from, rightUp + dir);
   }

   private void pawnDoubleStep(int from, int direction) {
      int to = from + direction;
      if (c.isEmpty(to)) addMove(from, to);
   }

   private void pawnCapture(int from, int direction) {
      int to = from + direction;
      if (c.isOpponent(to)) addPawnMove(from, to);
   }

   private void genQrb(int[][] moves, int from) {
      for (int i = 0; i < 4; i++)
         genDirection(from, i, moves);
   }

   private void genDirection(int from, int direction, int[][] moves) {
      int to = moves[from][direction];
      while (isValid(to)) {
         if (c.isEmpty(to)) addMove(from, to);
         else if (c.isOpponent(to)) addMove(from, to);
         else return;
         to = moves[to][direction];
      }
   }

   private void gen(int[][] moves, int from) {
      int to = moves[from][NORTH];
      for (int direction = NORTH + 1; isValid(to); direction++) {
         if (c.isEmptyOrOpponent(to)) addMove(from, to);
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

   private boolean isPromotion(int to) {
      return to >= a8 || to <= h1;
   }

   private boolean isWhitePawn(int field) {
      return c.color[field] == white && c.board[field] == pawn;
   }

   private boolean isBlackPawn(int field) {
      return c.color[field] == black && c.board[field] == pawn;
   }

   private boolean queenSideCastling(int color) {
      return c.gameList[c.hply].castle.queenside[color];
   }

   private boolean kingSideCastling(int color) {
      return c.gameList[c.hply].castle.kingside[color];
   }

}
