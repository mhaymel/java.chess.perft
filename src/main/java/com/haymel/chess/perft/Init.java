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

   public static void initBoard() {
      for (int x = 0; x < 64; ++x) {
         color[x] = initColor[x];
         board[x] = initBoard[x];
//            rank[0][x] = row[x];
//            rank[1][x] = 7 - row[x];
      }

      side = white;
      xside = black;
//        fifty = 0;
      ply = 0;
      hply = 0;
      firstMove[0] = 0;
//        kingloc[0] = Globals.E1;
//        kingloc[1] = Globals.E8;

//        game_list[hply].castle_q[0] = 1;
//        game_list[hply].castle_q[1] = 1;
//        game_list[hply].castle_k[0] = 1;
//        game_list[hply].castle_k[1] = 1;
   }



}
