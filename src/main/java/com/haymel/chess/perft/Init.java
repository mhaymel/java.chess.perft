package com.haymel.chess.perft;

import static com.haymel.chess.perft.Color.*;
import static com.haymel.chess.perft.Color.empty;
import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.Field.h1;
import static com.haymel.chess.perft.File.*;
import static com.haymel.chess.perft.Piece.*;

public final class Init {

   public static final int[] initColor = {
         white, white, white, white, white, white, white, white,
         white, white, white, white, white, white, white, white,
         empty, empty, empty, empty, empty, empty, empty, empty,
         empty, empty, empty, empty, empty, empty, empty, empty,
         empty, empty, empty, empty, empty, empty, empty, empty,
         empty, empty, empty, empty, empty, empty, empty, empty,
         black, black, black, black, black, black, black, black,
         black, black, black, black, black, black, black, black
   };

   public static final int[] initBoard = {
         rook, knight, bishop, queen, king, bishop, knight, rook,
         pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn,
         empty, empty, empty, empty, empty, empty, empty, empty,
         empty, empty, empty, empty, empty, empty, empty, empty,
         empty, empty, empty, empty, empty, empty, empty, empty,
         empty, empty, empty, empty, empty, empty, empty, empty,
         pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn,
         rook, knight, bishop, queen, king, bishop, knight, rook
   };

   public static final int[] file = {
         A, B, C, D, E, F, G, H,
         A, B, C, D, E, F, G, H,
         A, B, C, D, E, F, G, H,
         A, B, C, D, E, F, G, H,
         A, B, C, D, E, F, G, H,
         A, B, C, D, E, F, G, H,
         A, B, C, D, E, F, G, H,
         A, B, C, D, E, F, G, H
   };

   public static final int[][] rank = {
      {
         0, 0, 0, 0, 0, 0, 0, 0,
         1, 1, 1, 1, 1, 1, 1, 1,
         2, 2, 2, 2, 2, 2, 2, 2,
         3, 3, 3, 3, 3, 3, 3, 3,
         4, 4, 4, 4, 4, 4, 4, 4,
         5, 5, 5, 5, 5, 5, 5, 5,
         6, 6, 6, 6, 6, 6, 6, 6,
         7, 7, 7, 7, 7, 7, 7, 7
      },
      {
         7, 7, 7, 7, 7, 7, 7, 7,
         6, 6, 6, 6, 6, 6, 6, 6,
         5, 5, 5, 5, 5, 5, 5, 5,
         4, 4, 4, 4, 4, 4, 4, 4,
         3, 3, 3, 3, 3, 3, 3, 3,
         2, 2, 2, 2, 2, 2, 2, 2,
         1, 1, 1, 1, 1, 1, 1, 1,
         0, 0, 0, 0, 0, 0, 0, 0,
      }
   };

   public static final int[] flip = {
      a8, b8, c8, d8, e8, f8, g8, h8,
      a7, b7, c7, d7, e7, f7, g7, h7,
      a6, b6, c6, d6, e6, f6, g6, h6,
      a5, b5, c5, d5, e5, f5, g5, h5,
      a4, b4, c4, d4, e4, f4, g4, h4,
      a3, b3, c3, d3, e3, f3, g3, h3,
      a2, b2, c2, d2, e2, f2, g2, h2,
      a1, b1, c1, d1, e1, f1, g1, h1
   };

}
