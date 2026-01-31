package com.haymel.chess.perft;

/*
LowestAttacker is similar to Attack. It returns the square the weakest attacker of the given side and given square.
It returns -1 if there are no attackers.
It is used to find the next piece that will recapture, but can have other uses.
*/

import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.File.A;
import static com.haymel.chess.perft.File.H;
import static com.haymel.chess.perft.Generator.*;
import static com.haymel.chess.perft.Init.file;
import static com.haymel.chess.perft.MoveTables.*;
import static com.haymel.chess.perft.Piece.*;

public final class LowestAttacker {

   private final Chess c;

   public LowestAttacker(Chess c) {
      this.c = c;
   }

   public static LowestAttacker NewLowestAttacker(Chess chess) {
      return new LowestAttacker(chess);
   }

   public int calculate(int side, int field) {
      if (c.itsWhitesTurn()) {
         if (field > h2) {
            if (file[field] > A && c.isWhitePawn(field - whitePawnLeftCapture))
               return field - whitePawnLeftCapture;
            if (file[field] < H && c.isWhitePawn(field - whitePawnRightCapture))
               return field - whitePawnRightCapture;
         }
      } else if (field < h7) {
         if (file[field] > A && c.isBlackPawn(field - blackPawnLeftCapture))
            return field - blackPawnLeftCapture;
         if (file[field] < H && c.isBlackPawn(field - blackPawnRightCapture))
            return field - blackPawnRightCapture;
      }

      int k = 0;
      int sq = knightMoves[field][k];

      while (sq > -1) {
         if (c.color[sq] == side && c.isKnight(sq))
            return sq;
         k++;
         sq = knightMoves[field][k];
      }

      sq = lineCheck(side, bishop, field, bishopMoves, 4);
      if (sq > -1) return sq;

      sq = lineCheck(side, rook, field, rookMoves, 4);
      if (sq > -1) return sq;

      sq = lineCheck(side, queen, field, queenMoves, 8);
      if (sq > -1) return sq;
      //
//      sq = LineCheck(side, field, NE, B);
//      if (sq > -1) return sq;
//      sq = LineCheck(side, field, NW, B);
//      if (sq > -1) return sq;
//      sq = LineCheck(side, field, SW, B);
//      if (sq > -1) return sq;
//      sq = LineCheck(side, field, SE, B);
//      if (sq > -1) return sq;
//
//      sq = LineCheck(side, field, NORTH, R);
//      if (sq > -1) return sq;
//      sq = LineCheck(side, field, SOUTH, R);
//      if (sq > -1) return sq;
//      sq = LineCheck(side, field, EAST, R);
//      if (sq > -1) return sq;
//      sq = LineCheck(side, field, WEST, R);
//      if (sq > -1) return sq;
//
//      sq = LineCheck(side, field, NORTH, Q);
//      if (sq > -1) return sq;
//      sq = LineCheck(side, field, SOUTH, Q);
//      if (sq > -1) return sq;
//      sq = LineCheck(side, field, EAST, Q);
//      if (sq > -1) return sq;
//      sq = LineCheck(side, field, WEST, Q);
//      if (sq > -1) return sq;
//
//      sq = LineCheck(side, field, NE, Q);
//      if (sq > -1) return sq;
//      sq = LineCheck(side, field, NW, Q);
//      if (sq > -1) return sq;
//      sq = LineCheck(side, field, SW, Q);
//      if (sq > -1) return sq;
//      sq = LineCheck(side, field, SE, Q);
//      if (sq > -1) return sq;
//
//      if (abs(col[field] - col[kingloc[side]]) < 2 && abs(row[field] - row[kingloc[side]]) < 2) {
//         return kingloc[side];
//      }
      return -1;
   }

   private int lineCheck(int side, int piece, int from, int[][] moves, int count) {
      for (int direction = 0; direction < count; direction++) {
         int to = moves[from][direction];
         while (isValid(to)) {
            if (c.color[to] == side && c.board[to] == piece)
               return to;
            to = moves[to][direction];
         }
      }
      return -1;
   }

}
