package com.haymel.chess.perft;

import static com.haymel.chess.perft.Color.*;
import static com.haymel.chess.perft.Color.empty;
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

}
