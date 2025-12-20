package com.haymel.chess.perft;

import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.Piece.king;
import static com.haymel.chess.perft.Piece.rook;
import static java.lang.Math.abs;

public final class Update {

   private static final int[] ReverseSquare = {-8, 8};

   private final Chess c;
   private final Attack a;

   public Update(Chess c) {
      this(c, new Attack(c));
   }

   public Update(Chess c, Attack attack) {
      this.c = c;
      this.a = attack;
   }

   public void AddPiece(int s, int p, int sq) {
      c.board[sq] = p;
      c.color[sq] = s;
   }

   private boolean isCastleMove(int from, int to) {
      return c.board[from] == king && abs(from - to) == 2;
   }

   private boolean isAttacked(int field) {
      return a.attack(c.xside, field);
   }

   public boolean MakeMove(int from, int to) {
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
            UpdatePiece(c.side,rook, h8, f8);
         } else if (to == c8) {
            if (isAttacked(d8)) return false;
            UpdatePiece(c.side, rook, a8,d8);
         }
      }

      Game g = c.gameList[c.hply];
      g.from = from;
      g.to = to;
      g.capturePiece = c.board[to];

      c.ply++;
      c.hply++;
//
//      g = game_list[hply];
//      g.castle_q[0] = game_list[hply - 1].castle_q[0];
//      g.castle_q[1] = game_list[hply - 1].castle_q[1];
//      g.castle_k[0] = game_list[hply - 1].castle_k[0];
//      g.castle_k[1] = game_list[hply - 1].castle_k[1];
//
//      fifty++;
//
//      if (board[from] == P) {
//         fifty = 0;
//         if (board[to] == EMPTY && col[from] != col[to]) {
//            RemovePiece(xside, P, to + ReverseSquare[side]);
//         }
//      }
//
//      if (board[to] < 6) {
//         fifty = 0;
//         RemovePiece(xside, board[to], to);
//      }
//
//      if (board[from] == P && (row[to] == 0 || row[to] == 7))//promotion
//      {
//         RemovePiece(side, P, from);
//         AddPiece(side, Q, to);
//         g.promote = Q;
//      } else {
//         g.promote = 0;
//         UpdatePiece(side, board[from], from, to);
//      }
//
//      if (to == A1 || from == A1)
//         g.castle_q[0] = 0;
//      else if (to == H1 || from == H1)
//         g.castle_k[0] = 0;
//      else if (from == E1) {
//         g.castle_q[0] = 0;
//         g.castle_k[0] = 0;
//      }
//
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
