package com.haymel.chess.perft;

import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Color.white;
import static com.haymel.chess.perft.Direction.*;
import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.KingMoves.kingMoves;
import static com.haymel.chess.perft.Piece.*;

public final class Gen {

   private final Chess c;
   //    public static move g;
//
//    public static final int px[] = {0, 10, 20, 30, 40, 0};
//    public static final int nx[] = {-3, 7, 17, 27, 37, 0};
//    public static final int bx[] = {-3, 7, 17, 27, 37, 0};
//    public static final int rx[] = {-5, 5, 15, 25, 35, 0};
//    public static final int qx[] = {-9, 1, 11, 21, 31, 0};
//    public static final int kx[] = {0, 10, 20, 30, 40, 0};
//
   //   public static final int ForwardSquare[] = {8, -8};
//    public static final int Double[] = {16, -16};
//    public static final int Left[] = {7, -9};
//    public static final int Right[] = {9, -7};
//    public static final int OtherSide[] = {1, 0};

   public Gen(Chess c) {
      this.c = c;
   }

   public static Gen NewGen(Chess chess) {
      return new Gen(chess);
   }

   public void execute() {
      c.mc = c.firstMove[c.ply];

      genEnPassant();

      genCastle();

      for (int x = 0; x < 64; x++) {
         if (c.color[x] == c.side) {
            switch (c.board[x]) {
               case pawn:
                  GenPawn(x);
                  break;
               case knight:
                  GenKnight(x);
                  break;
               case bishop:
                  GenBishop(x, NE);
                  GenBishop(x, SE);
                  GenBishop(x, SW);
                  GenBishop(x, NW);
                  break;
               case rook:
                  GenRook(x, NORTH);
                  GenRook(x, EAST);
                  GenRook(x, SOUTH);
                  GenRook(x, WEST);
                  break;
               case queen:
                  GenQueen(x, NE);
                  GenQueen(x, SE);
                  GenQueen(x, SW);
                  GenQueen(x, NW);
                  GenQueen(x, NORTH);
                  GenQueen(x, EAST);
                  GenQueen(x, SOUTH);
                  GenQueen(x, WEST);
                  break;
               case king:
                  genKing(x);
                  break;
               default:
                  break;
            }
         }
      }
      c.firstMove[c.ply + 1] = c.mc;
   }

   private void genEnPassant() {
      if (c.enPassantField == Field.invalid)
         return;
      if (c.side == white) {
         if (c.enPassantField > a6) addCapture(c.enPassantField - 9, c.enPassantField);
         if (c.enPassantField < h6) addCapture(c.enPassantField - 7, c.enPassantField);
      } else {
         if (c.enPassantField > a3) addCapture(c.enPassantField + 7, c.enPassantField);
         if (c.enPassantField < h3) addCapture(c.enPassantField + 9, c.enPassantField);
      }
   }

   private void genKing(int from) {
      int direction = 0;
      int to = kingMoves[from][direction++];

      while (to != invalid) {
         if (c.color[to] != c.side)
            addMove(from, to);
         to = kingMoves[from][direction++];
      }
   }

   private void genCastle() {
      if (c.side == white) {
         if (kingSideCastling(white) && isEmpty(f1) && isEmpty(g1))
            addMove(e1, g1);
         if (queenSideCastling(white) && isEmpty(d1) && isEmpty(c1) && isEmpty(b1))
            addMove(e1, c1);
      } else {
         if (kingSideCastling(black) && isEmpty(f8) && isEmpty(g8))
            addMove(e8, g8);
         if (queenSideCastling(black) && isEmpty(d8) && isEmpty(c8) && isEmpty(b8))
            addMove(e8, c8);
      }
   }

   private boolean isEmpty(int field) {
      return c.board[field] == empty;
   }

   private boolean queenSideCastling(int color) {
      return c.gameList[c.hply].castle.queenside[color];
   }

   private boolean kingSideCastling(int color) {
      return c.gameList[c.hply].castle.kingside[color];
   }

   private void genBlackCastling() {
//         if (game_list[hply].castle_k[side] != 0) {
//            if (board[F8] == EMPTY && board[G8] == EMPTY) {
//               AddMove(E8, G8);
//            }
//         }
//         if (game_list[hply].castle_q[side] != 0) {
//            if (board[B8] == EMPTY && board[C8] == EMPTY && board[D8] == EMPTY) {
//               AddMove(E8, C8);
//            }
//         }
   }

   /*
   GenPawn generates single and double pawn moves and pawn
   captures for a pawn.
   */
   public static void GenPawn(int x) {
//      if (board[x + ForwardSquare[side]] == EMPTY) {
//         AddMove(x, x + ForwardSquare[side]);
//         if (rank[side][x] == 1 && board[x + Double[side]] == EMPTY) {
//            AddMove(x, x + Double[side]);
//         }
//      }
//      if (file[x] > 0 && color[x + Left[side]] == OtherSide[side]) {
//         AddCapture(x, x + Left[side], px[board[x + Left[side]]]);
//      }
//      if (file[x] < 7 && color[x + Right[side]] == OtherSide[side]) {
//         AddCapture(x, x + Right[side], px[board[x + Right[side]]]);
//      }
   }

   /*
   GenKnight generates knight moves and captures by using the
   knight_moves look up
   table created in init.cpp.
   */
   public static void GenKnight(int sq) {
//      int k = 0;
//      int sq2 = knight_moves[sq][k++];
//      while (sq2 > -1) {
//         if (color[sq2] == EMPTY)
//            AddMove(sq, sq2);
//         else if (color[sq2] == xside)
//            AddCapture(sq, sq2, nx[board[sq2]]);
//         sq2 = knight_moves[sq][k++];
//      }
   }

   /*
   GenBishop generates bishop moves and
   captures for each diagonal.
   */
   public static void GenBishop(int x, int dir) {
//      int sq = qrb_moves[x][dir];
//      while (sq > -1) {
//         if (color[sq] != EMPTY) {
//            if (color[sq] == xside)
//               AddCapture(x, sq, bx[board[sq]]);
//            break;
//         }
//         AddMove(x, sq);
//         sq = qrb_moves[sq][dir];
//      }
   }

   /*
   GenRook generates straight moves and captures
   for each rank and file.
   */
   public static void GenRook(int x, int dir) {
//      int sq = qrb_moves[x][dir];
//      while (sq > -1) {
//         if (color[sq] != EMPTY) {
//            if (color[sq] == xside) {
//               AddCapture(x, sq, rx[board[sq]]);
//            }
//            break;
//         }
//         AddMove(x, sq);
//         sq = qrb_moves[sq][dir];
//      }
   }

   /*
   GenQueen generates queen moves and captures
   for line.
   */
   public static void GenQueen(int x, int dir) {
//      int sq = qrb_moves[x][dir];
//      while (sq > -1) {
//         if (color[sq] != EMPTY) {
//            if (color[sq] == xside) {
//               AddCapture(x, sq, qx[board[sq]]);
//            }
//            break;
//         }
//         AddMove(x, sq);
//         sq = qrb_moves[sq][dir];
//      }
   }

   /*
   GenCaptures is very similar to Gen, except that only captures
   are being generated instead of all moves.
   */
   public static void GenCaptures() {
//      first_move[ply + 1] = first_move[ply];
//      mc = first_move[ply];//
//
//      for (int x = 0; x < 64; x++) {
//         if (color[x] == side) {
//            switch (board[x]) {
//               case P:
//                  CapPawn(x);
//                  break;
//               case N:
//                  CapKnight(x);
//                  break;
//               case B:
//                  CapBishop(x, NE);
//                  CapBishop(x, SE);
//                  CapBishop(x, SW);
//                  CapBishop(x, NW);
//                  break;
//               case R:
//                  CapRook(x, EAST);
//                  CapRook(x, SOUTH);
//                  CapRook(x, WEST);
//                  CapRook(x, NORTH);
//                  break;
//               case Q:
//                  CapQueen(x, NE);
//                  CapQueen(x, SE);
//                  CapQueen(x, SW);
//                  CapQueen(x, NW);
//                  CapQueen(x, EAST);
//                  CapQueen(x, SOUTH);
//                  CapQueen(x, WEST);
//                  CapQueen(x, NORTH);
//                  break;
//               case K:
//                  CapKing(x);
//                  break;
//               default:
//                  break;
//            }
//         }
//      }
//      first_move[ply + 1] = mc;
   }

   /*
   CapPawn generates pawn captures.
   */
   public static void CapPawn(int x) {
//
//      if (file[x] > 0 && color[x + Left[side]] == OtherSide[side]) {
//         AddCapture(x, x + Left[side], px[board[x + Left[side]]]);
//      }
//      if (file[x] < 7 && color[x + Right[side]] == OtherSide[side]) {
//         AddCapture(x, x + Right[side], px[board[x + Right[side]]]);
//      }
//
   }

   /*
   CapKnight generates knight captures.
   */
   public static void CapKnight(int x) {
//      int k = 0;
//      int sq = knight_moves[x][k++];
//      while (sq > -1) {
//         if (color[sq] == xside)
//            AddCapture(x, sq, nx[board[sq]]);
//         sq = knight_moves[x][k++];
//      }
   }

   /*
   CapBishop generates bishop captures.
   */
   public static void CapBishop(int x, int dir) {
//
//      int sq = qrb_moves[x][dir];
//      while (sq > -1) {
//         if (color[sq] != EMPTY) {
//            if (color[sq] == xside)
//               AddCapture(x, sq, bx[board[sq]]);
//            break;
//         }
//         sq = qrb_moves[sq][dir];
//      }
//
   }

   /*
   CapRook generates rook captures.
   */
   public static void CapRook(int x, int dir) {
//      int sq = qrb_moves[x][dir];
//      while (sq > -1) {
//         if (color[sq] != EMPTY) {
//            if (color[sq] == xside) {
//               AddCapture(x, sq, rx[board[sq]]);
//            }
//            break;
//         }
//         sq = qrb_moves[sq][dir];
//      }
   }

   /*
   CapQueen generates queen captures.
   */
   public static void CapQueen(int x, int dir) {
//      int sq = qrb_moves[x][dir];
//      while (sq > -1) {
//         if (color[sq] != EMPTY) {
//            if (color[sq] == xside) {
//               AddCapture(x, sq, qx[board[sq]]);
//            }
//            break;
//         }
//         sq = qrb_moves[sq][dir];
//      }
   }

   /*
   AddMove adds the start and dest squares of a move to the movelist.
   The score is the history value.
   */
   public void addMove(int x, int sq) {
      c.moveList[c.mc].start = x;
      c.moveList[c.mc].dest = sq;
      c.mc++;
   }

   private void addCapture(int x, int sq) {
      c.moveList[c.mc].start = x;
      c.moveList[c.mc].dest = sq;
      c.mc++;
   }

}
