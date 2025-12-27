package com.haymel.chess.perft;

import static com.haymel.chess.perft.Chess.other;
import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Color.white;
import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.Generator.*;
import static com.haymel.chess.perft.Piece.*;
import static java.lang.Math.abs;

public final class Update {

   private static final int[] reverseSquare = {down, up};

   private final Chess c;
   private final Attack a;

   public Update(Chess c) { this(c, new Attack(c)); }

   public Update(Chess c, Attack attack) {
      this.c = c;
      this.a = attack;
   }

   private static boolean isPawnCapture(int from, int to) {
      int step = abs(from - to);
      return step != up && step != doubleStep;
   }

   public void addPiece(int side, int piece, int field) {
      c.board[field] = piece;
      c.color[field] = side;
   }

   private boolean isCastleMove(int from, int to) {
      return c.board[from] == king && abs(from - to) == 2;
   }

   private boolean isAttacked(int field) {
      return a.attack(other(c.side), field);
   }

   private boolean makeRookMoveForCastling(Move move) {
      final int from = move.from;
      final int to = move.to;

      if (isCastleMove(from, to)) {
         if (isAttacked(from))
            return false;
         if (to == g1) {
            if (isAttacked(f1))
               return false;
            updateRookCastling(h1, f1);
         } else if (to == c1) {
            if (isAttacked(d1))
               return false;
            updateRookCastling(a1, d1);
         } else if (to == g8) {
            if (isAttacked(f8))
               return false;
            updateRookCastling(h8, f8);
         } else if (to == c8) {
            if (isAttacked(d8))
               return false;
            updateRookCastling(a8, d8);
         }
      }
      return true;
   }

   public boolean MakeMove(Move move) {
      final int from = move.from;
      final int to = move.to;

      if (c.isKing(from))
         c.kingloc[c.side] = to;

      if (!makeRookMoveForCastling(move))
         return false;

      Game g = c.gameList[c.hply];
      g.from = from;
      g.to = to;
      g.capturePiece = c.board[to];

      c.ply++;
      c.hply++;

      g = c.gameList[c.hply];
      g.castle.assign(c.gameList[c.hply - 1].castle);

      if (isEnPassant(move))
         removePiece(to + reverseSquare[c.side]);

      if (isCapture(to))
         removePiece(to);

      c.gameList[c.hply].enPassantField = invalid;
      if (isPawnDoubleStep(move))
         c.gameList[c.hply].enPassantField = to + reverseSquare[c.side];

      if (isPromotion(move)) {
         removePiece(from);
         addPiece(c.side, move.promotion, to);
         c.gameList[c.hply-1].promote = move.promotion;
      } else {
         c.gameList[c.hply-1].promote = empty;
         update(c.board[from], from, to);
      }

      whiteCastling(move, g.castle);
      blackCastling(move, g.castle);

      c.side ^= 1;
      if (!a.attack(c.side, c.kingloc[c.otherSide()]))
         return true;

      unMakeMove();

      return false;
   }

   public void unMakeMove() {
      c.side ^= 1;
      c.ply--;
      c.hply--;

      Game g = c.gameList[c.hply];

      final int from = g.from;
      final int to = g.to;

      if (c.isKing(to))
         c.kingloc[c.side] = from;

      if (isEnPassant(g))
         addPiece(c.otherSide(), pawn, to + reverseSquare[c.side]);

      if (isPromotion(g)) {
         addPiece(c.side, pawn, from);
         removePiece(to);
      } else {
         update(c.board[to], to, from);
      }

      if (g.capturePiece != empty)
         addPiece(c.otherSide(), g.capturePiece, to);

      unMakeRookMoveForCastling(from, to);
   }

   private void unMakeRookMoveForCastling(int from, int to) {
      if (!isCastleMove(from, to))
         return;
      if (to == g1)
         updateRookCastling(f1, h1);
      else if (to == c1)
         updateRookCastling(d1, a1);
      else if (to == g8)
         updateRookCastling(f8, h8);
      else if (to == c8)
         updateRookCastling(d8, a8);
   }

   private boolean isPawnDoubleStep(Move move) {
      return c.isPawn(move.from) && (abs(move.from - move.to) == doubleStep);
   }

   private static void blackCastling(Move move, Castling castling) {
      if (move.to == a8 || move.from == a8)
         castling.queenside[black] = false;
      else if (move.to == h8 || move.from == h8)
         castling.kingside[black] = false;
      else if (move.from == e8) {
         castling.queenside[black] = false;
         castling.kingside[black] = false;
      }
   }

   private static void whiteCastling(Move move, Castling castling) {
      if (move.to == a1 || move.from == a1)
         castling.queenside[white] = false;
      else if (move.to == h1 || move.from == h1)
         castling.kingside[white] = false;
      else if (move.from == e1) {
         castling.queenside[white] = false;
         castling.kingside[white] = false;
      }
   }

   private boolean isEnPassant(Move move) {
      return c.isPawn(move.from) && c.isEmpty(move.to) && isPawnCapture(move.from, move.to);
   }

   private boolean isEnPassant(Game g) {
      return c.isPawn(g.to) && g.capturePiece == empty && isPawnCapture(g.from, g.to);
   }

   private boolean isPromotion(Move move) { return c.isPawn(move.from) && (move.to >= a8 || move.to <= h1); }

   private boolean isPromotion(Game g) {
      return g.promote != empty;
   }

   private boolean isCapture(int to) { return !c.isEmpty(to); }

   public void removePiece(int field) {
      c.board[field] = Piece.empty;
      c.color[field] = Color.empty;
   }

   private void updateRookCastling(int from, int to) {
      update(rook, from, to);
   }

   private void update(int piece, int from, int to) {
      c.board[to] = piece;
      c.color[to] = c.side;
      c.board[from] = empty;
      c.color[from] = empty;
   }

}
