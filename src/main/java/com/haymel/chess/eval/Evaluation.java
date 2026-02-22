package com.haymel.chess.eval;

import com.haymel.chess.perft.Chess;

import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.Init.flip;
import static com.haymel.chess.perft.Piece.*;

//values from copilot
public final class Evaluation {

   public static final int pawnValue =      100;
   public static final int knightValue =    320;
   public static final int bishopValue =    330;
   public static final int rookValue =      500;
   public static final int queenValue =     900;
   public static final int kingValue =        0;
   public static final int emptyValue =       0;

   public static final int[] pieceValue = {
      pawnValue, knightValue, bishopValue, rookValue, queenValue, kingValue, emptyValue
   };

   public static final int[] pstPawn = {
      0,   0,  0,  0,  0,  0,  0,  0,
      10, 10, 10, 10, 10, 10, 10, 10,
       5,  5,  5, 15, 15,  5,  5,  5,
       2,  2,  2, 10, 10,  2,  2,  2,
       1,  1,  1,  5,  5,  1,  1,  1,
       0,  0,  0,  0,  0,  0,  0,  0,
       0,  0,  0,  0,  0,  0,  0,  0,
       0,  0,  0,  0,  0,  0,  0,  0
   };

   public static final int[] pstKnight = {
      -5, -4, -3, -3, -3, -3, -4, -5,
      -4, -2,  0,  0,  0,  0, -2, -4,
      -3,  0,  1,  1,  1,  1,  0, -3,
      -3,  0,  1,  2,  2,  1,  0, -3,
      -3,  0,  1,  2,  2,  1,  0, -3,
      -3,  0,  1,  1,  1,  1,  0, -3,
      -4, -2,  0,  0,  0,  0, -2, -4,
      -5, -4, -3, -3, -3, -3, -4, -5
   };

   public static final int[] pstBishop = {
      -2, -1, -1, -1, -1, -1, -1, -2,
      -1,  1,  0,  0,  0,  0,  1, -1,
      -1,  0,  1,  1,  1,  1,  0, -1,
      -1,  0,  1,  2,  2,  1,  0, -1,
      -1,  0,  1,  2,  2,  1,  0, -1,
      -1,  0,  1,  1,  1,  1,  0, -1,
      -1,  1,  0,  0,  0,  0,  1, -1,
      -2, -1, -1, -1, -1, -1, -1, -2
   };

   public static final int[] pstKing = {
      -3, -4, -4, -5, -5, -4, -4, -3,
      -3, -4, -4, -5, -5, -4, -4, -3,
      -3, -4, -4, -5, -5, -4, -4, -3,
      -3, -4, -4, -5, -5, -4, -4, -3,
      -2, -3, -3, -4, -4, -3, -3, -2,
      -1, -2, -2, -2, -2, -2, -2, -1,
       2,  2,  0,  0,  0,  0,  2,  2,
       2,  3,  1,  0,  0,  1,  3,  2
   };

   public static final int[] pstRook = {
      0,  0,  0,  5,  5,  0,  0,  0,
     -5,  0,  0,  0,  0,  0,  0, -5,
     -5,  0,  0,  0,  0,  0,  0, -5,
     -5,  0,  0,  0,  0,  0,  0, -5,
     -5,  0,  0,  0,  0,  0,  0, -5,
     -5,  0,  0,  0,  0,  0,  0, -5,
      5, 10, 10, 10, 10, 10, 10,  5,  // 7. rank
      0,  0,  0,  5,  5,  0,  0,  0
   };

   public static final int[] pstQueen = {
      -2, -1, -1, -0, -0, -1, -1, -2,
      -1,  0,  0,  0,  0,  0,  0, -1,
      -1,  0,  1,  1,  1,  1,  0, -1,
       0,  0,  1,  2,  2,  1,  0,  0,
       0,  0,  1,  2,  2,  1,  0,  0,
      -1,  0,  1,  1,  1,  1,  0, -1,
      -1,  0,  0,  0,  0,  0,  0, -1,
      -2, -1, -1, -0, -0, -1, -1, -2
   };

   private static final int[] flipFlipped = {
      a1, b1, c1, d1, e1, f1, g1, h1,
      a2, b2, c2, d2, e2, f2, g2, h2,
      a3, b3, c3, d3, e3, f3, g3, h3,
      a4, b4, c4, d4, e4, f4, g4, h4,
      a5, b5, c5, d5, e5, f5, g5, h5,
      a6, b6, c6, d6, e6, f6, g6, h6,
      a7, b7, c7, d7, e7, f7, g7, h7,
      a8, b8, c8, d8, e8, f8, g8, h8
   };

   public static final int[][] mvvLva =  {
                             //enpassant
      {0, 10, 20, 30, 40, 0, 0},    //pawn
      {-3, 7, 17, 27, 37, 0},       //knight
      {-3, 7, 17, 27, 37, 0},       //bishop
      {-5, 5, 15, 25, 35, 0},       //rook
      {-9, 1, 11, 21, 31, 0},       //queen
      {0, 10, 20, 30, 40, 0},       //king
   };

   private static final int[][] flipped = {flip, flipFlipped};

   private static int evaluate(int[] board, int[] color) {
      int[] score = new int[] { 0, 0 };
      for (int i = 0; i < board.length; i++) {
         int piece = board[i];
         int c = color[i];
         switch (piece) {
            case pawn -> score[c] += pawnValue + pstPawn[flipped[c][i]];
            case knight -> score[c] += knightValue + pstKnight[flipped[c][i]];
            case bishop -> score[c] += bishopValue + pstBishop[flipped[c][i]];
            case rook -> score[c] += rookValue + pstRook[flipped[c][i]];
            case queen -> score[c] += queenValue + pstQueen[flipped[c][i]];
            case king -> score[c] += kingValue + pstKing[flipped[c][i]];
            default -> {}
         }
      }
      return score[0] - score[1];
   }

   public static int evaluate(Chess chess) {
      return evaluate(chess.board, chess.color);
   }
}
