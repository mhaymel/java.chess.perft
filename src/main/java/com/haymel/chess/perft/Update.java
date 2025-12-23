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
      return abs(from - to) != 8;
   }

   public void addPiece(int side, int piece, int field) {
      c.board[field] = piece;
      c.color[field] = side;
   }

   private boolean isCastleMove(Move move) {
      int from = move.from;
      int to = move.to;
      return c.board[from] == king && abs(from - to) == 2;
   }

   private boolean isAttacked(int field) {
      return a.attack(other(c.side), field);
   }

   private boolean makeRookMoveForCastling(Move move) {
      final int from = move.from;
      final int to = move.to;

      if (isCastleMove(move)) {
         if (isAttacked(from)) return false;
         if (to == g1) {
            if (isAttacked(from)) return false;
            updateRookCastling(h1, f1);
         } else if (to == c1) {
            if (isAttacked(d1)) return false;
            updateRookCastling(a1, d1);
         } else if (to == g8) {
            if (isAttacked(f8)) return false;
            updateRookCastling(h8, f8);
         } else if (to == c8) {
            if (isAttacked(d8)) return false;
            updateRookCastling(a8, d8);
         }
      }
      return true;
   }

   public boolean MakeMove(Move move) {
      if (!makeRookMoveForCastling(move)) return false;

      final int from = move.from;
      final int to = move.to;

      Game g = c.gameList[c.hply];
      g.from = from;
      g.to = to;
      g.capturePiece = c.board[to];
      g.enPassantField = invalid;

      c.ply++;
      c.hply++;

      g.castle.assign(c.gameList[c.hply].castle);

      if (isEnPassant(move)) removePiece(to + reverseSquare[c.side]);
      if (isCapture(to)) removePiece(to);
      if (isPawnDoubleStep(move)) g.enPassantField = to + reverseSquare[c.side];
      if (isPromotion(move)) {
         removePiece(from);
         addPiece(c.side, move.promotion, to);
         g.promote = move.promotion;
      } else {
         g.promote = empty;
         updatePiece(c.board[from], move);
      }

      whiteCastling(move, g.castle);
      blackCastling(move, g.castle);

      c.side ^= 1;
      if (!a.attack(c.side, c.kingloc[c.otherSide()])) return true;

      unMakeMove();
      return false;
   }

   private boolean isPawnDoubleStep(Move move) {
      return c.isPawn(move.from) && (abs(move.from - move.to) == doubleStep);
   }

   private void unMakeMove() {
//      c.side ^= 1;
//      c.ply--;
//      c.hply--;
//
//      Game m = c.gameList[c.hply];
//
//      int from = m.from;
//      int to = m.to;
//
//      if (c.isPawn(to) && m.capture == EMPTY && col[from] != col[to]) {
//         addPiece(c.otherSide(), P, to + ReverseSquare[side]);
//      }
//      if (game_list[hply + 1].promote == Q) {
//         AddPiece(side, P, from);
//         RemovePiece(side, board[to], to);
//      } else {
//         UpdatePiece(side, board[to], to, from);
//      }
//      if (m.capture != EMPTY) {
//         AddPiece(xside, m.capture, to);
//      }
//      if (abs(from - to) == 2 && board[from] == K) {
//         if (to == G1)
//            UpdatePiece(side, R, F1, H1);
//         else if (to == C1)
//            UpdatePiece(side, R, D1, A1);
//         else if (to == G8)
//            UpdatePiece(side, R, F8, H8);
//         else if (to == C8)
//            UpdatePiece(side, R, D8, A8);
//      }
   }

   private static void blackCastling(Move move, Castling castling) {
      if (move.to == a8 || move.from == a8)         castling.queenside[black] = false;
      else if (move.to == h8 || move.from == h8)    castling.kingside[black] = false;
      else if (move.from == e8) {
         castling.queenside[black] = false;
         castling.kingside[black] = false;
      }
   }

   private static void whiteCastling(Move move, Castling castling) {
      if (move.to == a1 || move.from == a1)         castling.queenside[white] = false;
      else if (move.to == h1 || move.from == h1)    castling.kingside[white] = false;
      else if (move.from == e1) {
         castling.queenside[white] = false;
         castling.kingside[white] = false;
      }
   }

   private boolean isEnPassant(Move move) {
      return c.isPawn(move.from) && c.isEmpty(move.to) && isPawnCapture(move.from, move.to);
   }

   private boolean isPromotion(Move move) { return c.isPawn(move.from) && (move.to >= a8 || move.to <= h1); }

   private boolean isCapture(int to) { return !c.isEmpty(to); }

   public void removePiece(int field) {
      c.board[field] = Piece.empty;
      c.color[field] = Color.empty;
   }

   private void updatePiece(int piece, Move move) {
      update(piece, move.from, move.to);
      if (piece == king)
         c.kingloc[c.side] = move.to;
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
