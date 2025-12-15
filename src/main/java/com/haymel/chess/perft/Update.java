package com.haymel.chess.perft;

public final class Update {

   private static final int[] ReverseSquare = {-8, 8};

   private final Chess c;

   public Update(Chess c) {
      this.c = c;
   }

   public void AddPiece(int s, int p, int sq) {
      c.board[sq] = p;
      c.color[sq] = s;
   }

   public boolean MakeMove(int start, int dest) {
//      if (abs(start - dest) == 2 && c.board[start] == Piece.king) {
//         if (attack(c.xside, start))
//            return false;
//         if (dest == g1) {
//            if (attack(c.xside, f1))
//               return false;
//            UpdatePiece(c.side, rook, h1, f1);
//         } else if (dest == c1) {
//            if (attack(c.xside, d1))
//               return false;
//            UpdatePiece(c.side, rook, a1, d1);
//         } else if (dest == g8) {
//            if (attack(c.xside, f8))
//               return false;
//            UpdatePiece(c.side,rook, h8, f8);
//         } else if (dest == c8) {
//            if (attack(c.xside, d8))
//               return false;
//            UpdatePiece(c.side, rook, a8,d8);
//         }
//      }
//
//      g = game_list[hply];
//      g.start = start;
//      g.dest = dest;
//      g.capture = c.board[dest];
//      g.fifty = fifty;
//
//      ply++;
//      hply++;
//
//      g = game_list[hply];
//      g.castle_q[0] = game_list[hply - 1].castle_q[0];
//      g.castle_q[1] = game_list[hply - 1].castle_q[1];
//      g.castle_k[0] = game_list[hply - 1].castle_k[0];
//      g.castle_k[1] = game_list[hply - 1].castle_k[1];
//
//      fifty++;
//
//      if (board[start] == P) {
//         fifty = 0;
//         if (board[dest] == EMPTY && col[start] != col[dest]) {
//            RemovePiece(xside, P, dest + ReverseSquare[side]);
//         }
//      }
//
//      if (board[dest] < 6) {
//         fifty = 0;
//         RemovePiece(xside, board[dest], dest);
//      }
//
//      if (board[start] == P && (row[dest] == 0 || row[dest] == 7))//promotion
//      {
//         RemovePiece(side, P, start);
//         AddPiece(side, Q, dest);
//         g.promote = Q;
//      } else {
//         g.promote = 0;
//         UpdatePiece(side, board[start], start, dest);
//      }
//
//      if (dest == A1 || start == A1)
//         g.castle_q[0] = 0;
//      else if (dest == H1 || start == H1)
//         g.castle_k[0] = 0;
//      else if (start == E1) {
//         g.castle_q[0] = 0;
//         g.castle_k[0] = 0;
//      }
//
//      if (dest == A8 || start == A8)
//         g.castle_q[1] = 0;
//      else if (dest == H8 || start == H8)
//         g.castle_k[1] = 0;
//      else if (start == E8) {
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
