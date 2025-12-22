package com.haymel.chess.perft;

import static com.haymel.chess.perft.Chess.other;
import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Color.white;
import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.Generator.down;
import static com.haymel.chess.perft.Generator.up;
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

   public void AddPiece(int side, int piece, int field) {
      c.board[field] = piece;
      c.color[field] = side;
   }

   private boolean isCastleMove(int from, int to) {
      return c.board[from] == king && abs(from - to) == 2;
   }

   private boolean isAttacked(int field) {
      return a.attack(other(c.side), field);
   }

   public boolean MakeMove(Move move) {
      final int from = move.from;
      final int to = move.to;
      if (isCastleMove(from, to)) {
         if (isAttacked(from)) return false;
         if (to == g1) {
            if (isAttacked(f1)) return false;
            UpdatePiece(c.side, rook, h1, f1);
         } else if (to == c1) {
            if (isAttacked(d1)) return false;
            UpdatePiece(c.side, rook, a1, d1);
         } else if (to == g8) {
            if (isAttacked(f8)) return false;
            UpdatePiece(c.side, rook, h8, f8);
         } else if (to == c8) {
            if (isAttacked(d8)) return false;
            UpdatePiece(c.side, rook, a8, d8);
         }
      }

      Game g = c.gameList[c.hply];
      g.from = from;
      g.to = to;
      g.capturePiece = c.board[to];

      c.ply++;
      c.hply++;

      g.castle.assign(c.gameList[c.hply].castle);

      if (isEnPassant(move)) RemovePiece(other(c.side), pawn, to + reverseSquare[c.side]);
      if (isCapture(to)) RemovePiece(c.otherSide(), c.board[to], to);

      if (isPromotion(move)) {
         RemovePiece(c.side, pawn, from);
         AddPiece(c.side, move.promotion, to);
         g.promote = move.promotion;
      } else {
         g.promote = empty;
         UpdatePiece(c.side, c.board[from], from, to);
      }

      whiteCastling(move, g.castle);
      blackCastling(move, g.castle);

//      if (to == A8 || from == A8)
//         g.castle_q[1] = 0;
//      else if (to == H8 || from == H8)
//         g.castle_k[1] = 0;
//      else if (from == E8) {
//         g.castle_q[1] = 0;
//         g.castle_k[1] = 0;
//      }
//
//      side ^= 1;
//      xside ^= 1;
//      if (Attack(side, kingloc[xside])) {
//         TakeBack();
//         return false;
//      }
      return true;
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

   public void RemovePiece(int s, int p, int sq) {
      c.board[sq] = Piece.empty;
      c.color[sq] = Color.empty;
   }

   private void UpdatePiece(int s, int p, int start, int dest) {
//      AddKey(s, p, start);
//      AddKey(s, p, dest);
//      board[dest] = p;
//      color[dest] = s;
//      board[start] = EMPTY;
//      color[start] = EMPTY;
//      if (p == K)
//         kingloc[s] = dest;
   }

//    public static void TakeBack() {
//        side ^= 1;
//        xside ^= 1;
//        ply--;
//        hply--;
//
//        game m = game_list[hply];
//        int start = m.start;
//        int dest = m.dest;
//
//        fifty = m.fifty;
//
//        if (board[dest] == P && m.capture == EMPTY && col[start] != col[dest]) {
//            AddPiece(xside, P, dest + ReverseSquare[side]);
//        }
//        if (game_list[hply + 1].promote == Q) {
//            AddPiece(side, P, start);
//            RemovePiece(side, board[dest], dest);
//        } else {
//            UpdatePiece(side, board[dest], dest, start);
//        }
//        if (m.capture != EMPTY) {
//            AddPiece(xside, m.capture, dest);
//        }
//        if (abs(start - dest) == 2 && board[start] == K) {
//            if (dest == G1)
//                UpdatePiece(side, R, F1, H1);
//            else if (dest == C1)
//                UpdatePiece(side, R, D1, A1);
//            else if (dest == G8)
//                UpdatePiece(side, R, F8, H8);
//            else if (dest == C8)
//                UpdatePiece(side, R, D8, A8);
//        }
//    }


}
