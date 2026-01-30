package com.haymel.chess.perft;

public final class LowestAttacker {

   private final Chess c;

   public LowestAttacker(Chess c) {
      this.c = c;
   }

   public static LowestAttacker NewLowestAttacker(Chess chess) {
      return new LowestAttacker(chess);
   }

      /*
LowestAttacker is similar to Attack. It returns the square the weakest attacker of the given side and given square.
It returns -1 if there are no attackers.
It is used to find the next piece that will recapture, but can have other uses.
*/

   public int calculate(int s, int x) {
//      if (s == 0) {
//         if (row[x] > 1) {
//            if (col[x] < 7 && color[x - 7] == s && board[x - 7] == 0) {
//               return x - 7;
//            }
//            if (col[x] > 0 && color[x - 9] == s && board[x - 9] == 0) {
//               return x - 9;
//            }
//         }
//      } else if (row[x] < 6) {
//         if (col[x] > 0 && color[x + 7] == s && board[x + 7] == 0) {
//            return x + 7;
//         }
//         if (col[x] < 7 && color[x + 9] == s && board[x + 9] == 0) {
//            return x + 9;
//         }
//      }
//
//      int k = 0;
//      int sq = knight_moves[x][k];
//
//      while (sq > -1) {
//         if (color[sq] == s && board[sq] == N)
//            return sq;
//         k++;
//         sq = knight_moves[x][k];
//      }
//
//      sq = LineCheck(s, x, NE, B);
//      if (sq > -1) return sq;
//      sq = LineCheck(s, x, NW, B);
//      if (sq > -1) return sq;
//      sq = LineCheck(s, x, SW, B);
//      if (sq > -1) return sq;
//      sq = LineCheck(s, x, SE, B);
//      if (sq > -1) return sq;
//
//      sq = LineCheck(s, x, NORTH, R);
//      if (sq > -1) return sq;
//      sq = LineCheck(s, x, SOUTH, R);
//      if (sq > -1) return sq;
//      sq = LineCheck(s, x, EAST, R);
//      if (sq > -1) return sq;
//      sq = LineCheck(s, x, WEST, R);
//      if (sq > -1) return sq;
//
//      sq = LineCheck(s, x, NORTH, Q);
//      if (sq > -1) return sq;
//      sq = LineCheck(s, x, SOUTH, Q);
//      if (sq > -1) return sq;
//      sq = LineCheck(s, x, EAST, Q);
//      if (sq > -1) return sq;
//      sq = LineCheck(s, x, WEST, Q);
//      if (sq > -1) return sq;
//
//      sq = LineCheck(s, x, NE, Q);
//      if (sq > -1) return sq;
//      sq = LineCheck(s, x, NW, Q);
//      if (sq > -1) return sq;
//      sq = LineCheck(s, x, SW, Q);
//      if (sq > -1) return sq;
//      sq = LineCheck(s, x, SE, Q);
//      if (sq > -1) return sq;
//
//      if (abs(col[x] - col[kingloc[s]]) < 2 && abs(row[x] - row[kingloc[s]]) < 2) {
//         return kingloc[s];
//      }
      return -1;
   }

   /*
LineCheck searches a line in direction d for the given piece of the given side.
It returns -1 if there are none.
*/
   public static int LineCheck(int s, int sq, int d, int p) {
//      sq = qrb_moves[sq][d];
//      while (sq > -1) {
//         if (color[sq] != EMPTY) {
//            if (board[sq] == p && color[sq] == s)
//               return sq;
//            break;
//         }
//         sq = qrb_moves[sq][d];
//      }
      return -1;
   }

}
