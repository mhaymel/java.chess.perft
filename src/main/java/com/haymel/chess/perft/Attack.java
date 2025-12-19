package com.haymel.chess.perft;

import com.haymel.chess.perft.move.Generator;

import static com.haymel.chess.perft.File.A;
import static com.haymel.chess.perft.File.H;
import static com.haymel.chess.perft.Init.file;
import static com.haymel.chess.perft.Init.rank;
import static com.haymel.chess.perft.move.Generator.*;

/*
LowestAttacker is similar to Attack. It returns the square the weakest attacker of the given side and given square.
It returns -1 if there are no attackers.
It is used to find the next piece that will recapture, but can have other uses.
*/

//    public static int LowestAttacker(int s, int x) {
//        if (s == 0) {
//            if (row[x] > 1) {
//                if (col[x] < 7 && color[x - 7] == s && board[x - 7] == 0) {
//                    return x - 7;
//                }
//                if (col[x] > 0 && color[x - 9] == s && board[x - 9] == 0) {
//                    return x - 9;
//                }
//            }
//        } else if (row[x] < 6) {
//            if (col[x] > 0 && color[x + 7] == s && board[x + 7] == 0) {
//                return x + 7;
//            }
//            if (col[x] < 7 && color[x + 9] == s && board[x + 9] == 0) {
//                return x + 9;
//            }
//        }
//
//        int k = 0;
//        int sq = knight_moves[x][k];
//
//        while (sq > -1) {
//            if (color[sq] == s && board[sq] == N)
//                return sq;
//            k++;
//            sq = knight_moves[x][k];
//        }
//
//        sq = LineCheck(s, x, NE, B);
//        if (sq > -1) return sq;
//        sq = LineCheck(s, x, NW, B);
//        if (sq > -1) return sq;
//        sq = LineCheck(s, x, SW, B);
//        if (sq > -1) return sq;
//        sq = LineCheck(s, x, SE, B);
//        if (sq > -1) return sq;
//
//        sq = LineCheck(s, x, NORTH, R);
//        if (sq > -1) return sq;x
//        sq = LineCheck(s, x, SOUTH, R);
//        if (sq > -1) return sq;
//        sq = LineCheck(s, x, EAST, R);
//        if (sq > -1) return sq;
//        sq = LineCheck(s, x, WEST, R);
//        if (sq > -1) return sq;
//
//        sq = LineCheck(s, x, NORTH, Q);
//        if (sq > -1) return sq;
//        sq = LineCheck(s, x, SOUTH, Q);
//        if (sq > -1) return sq;
//        sq = LineCheck(s, x, EAST, Q);
//        if (sq > -1) return sq;
//        sq = LineCheck(s, x, WEST, Q);
//        if (sq > -1) return sq;
//
//        sq = LineCheck(s, x, NE, Q);
//        if (sq > -1) return sq;
//        sq = LineCheck(s, x, NW, Q);
//        if (sq > -1) return sq;
//        sq = LineCheck(s, x, SW, Q);
//        if (sq > -1) return sq;
//        sq = LineCheck(s, x, SE, Q);
//        if (sq > -1) return sq;
//
//        if (abs(col[x] - col[kingloc[s]]) < 2 && abs(row[x] - row[kingloc[s]]) < 2) {
//            return kingloc[s];
//        }
//        return -1;
//    }
//
//    /*
//    LineCheck searches a line in direction d for the given piece of the given side.
//    It returns -1 if there are none.
//    */
//    public int LineCheck(int s, int sq, int d, int p) {
//        sq = qrb_moves[sq][d];
//        while (sq > -1) {
//            if (color[sq] != EMPTY) {
//                if (board[sq] == p && color[sq] == s)
//                    return sq;
//                break;
//            }
//            sq = qrb_moves[sq][d];
//        }
//        return -1;
//    }
//
//    /*
//    LineCheck2 searches a line in direction d for the given pieces of the given side.
//    On diagonals it searches for bishops and queens, while on ranks or files it
//    searches for rooks and queens.
//    It returns -1 if there are none.
//    */
//public static boolean LineCheck2(int s, int sq, int d, int p1, int p2) {
//        sq = qrb_moves[sq][d];
//        while (sq > -1) {
//            if (color[sq] != EMPTY) {
//                if ((board[sq] == p1 || board[sq] == p2) && color[sq] == s)
//                    return true;
//                break;
//            }
//            sq = qrb_moves[sq][d];
//        }
//   return false;
//}


public final class Attack {

   private final Chess c;

   public static Attack NewAttack(Chess chess) { return new Attack(chess); }

   public Attack(Chess c) {
      this.c = c;
   }


   private static final int[] left = {leftDown, leftUp};
   private static final int[] right = {rightDown, rightUp};

   public boolean isPawnAttack(int side, int field) {
      if (rank[side][field] == 0) return false;
      if (file[field] > A && isPawn(side, field + left[side])) return true;
      if (file[field] < H && isPawn(side, field + right[side])) return true;
      return false;
   }

   private boolean isPawn(int side, int field) {
      return c.board[field] == Piece.pawn && c.color[field] == side;
   }

   public boolean attack(int side, int field) {
      if (isPawnAttack(side, field)) return true;

//        if (isWhite(side)) {
//            if (x > h2) {
//                if (col[x] < 7 && isWhitePawn(x - 7)) return true;
//                if (col[x] > 0 && isWhitePawn(x - 9)) return true;
//            }
//        } else if (x < h7) {
//            if (col[x] > 0 && isBlackPawn(x + 7)) return true;
//            if (col[x] < 7 && isBlackPawn(x + 9)) return true;
//        }
////
//        int k = 0;
//        int sq = knightMoves[x][k];
//        while (sq > -1) {
//            if (c.color[sq] == side && c.board[sq] == knight) return true;
//            k++;
//            sq = knightMoves[x][k];
//        }
//
//        if (LineCheck2(side, x, NE, B, Q)) return true;
//        if (LineCheck2(side, x, NW, B, Q)) return true;
//        if (LineCheck2(side, x, SW, B, Q)) return true;
//        if (LineCheck2(side, x, SE, B, Q)) return true;
//
//        if (LineCheck2(side, x, NORTH, R, Q)) return true;
//        if (LineCheck2(side, x, SOUTH, R, Q)) return true;
//        if (LineCheck2(side, x, EAST, R, Q)) return true;
//        if (LineCheck2(side, x, WEST, R, Q)) return true;
//
//        if (abs(col[x] - col[c.kingloc[side]]) < 2 && abs(row[x] - row[c.kingloc[side]]) < 2) {
//            return true;
//        }
      return false;
   }
}
