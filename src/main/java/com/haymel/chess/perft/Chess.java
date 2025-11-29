package com.haymel.chess.perft;

import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Color.white;
import static com.haymel.chess.perft.Field.e1;
import static com.haymel.chess.perft.Field.e8;

public final class Chess {

   public int ply;
   public int hply;
   public int side = white;
   public int xside = black;

   public static final int maxPly = 128;
   public static final int moveStackSize = 2000;
   public static final int gameStackSize = 2000;

   public final Move[] moveList = newMove(moveStackSize);
   public final int[] firstMove = new int[maxPly];
   public final Game[] gameList = newGame(gameStackSize);
   public final int[] board = new int[64];
   public final int[] color = new int[64];
   public final int[] kingloc = new int[2];

   public void initBoard() {
      for (int x = 0; x < 64; ++x) {
         color[x] = Init.initColor[x];
         board[x] = Init.initBoard[x];
      }

      side = white;
      xside = black;
      ply = 0;
      hply = 0;
      firstMove[0] = 0;
      kingloc[white] = e1;
      kingloc[black] = e8;

//        game_list[hply].castle_q[0] = 1;
//        game_list[hply].castle_q[1] = 1;
//        game_list[hply].castle_k[0] = 1;
//        game_list[hply].castle_k[1] = 1;
   }

   private static Move[] newMove(int size) {
      Move[] moves = new Move[size];
      for (int i = 0; i < moves.length; i++)
         moves[i] = new Move();
      return moves;
   }

   private static Game[] newGame(int size) {
      Game[] game = new Game[size];
      for (int i = 0; i < game.length; i++)
         game[i] = new Game();
      return game;
   }

}
