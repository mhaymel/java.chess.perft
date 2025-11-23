package com.haymel.chess.perft;

public final class Chess {

   public int ply;
   public int hply;
   public int side = Color.white;
   public int xside = Color.black;

   public static final int maxPly = 128;
   public static final int moveStackSize = 2000;
   public static final int gameStackSize = 2000;

   public final Move[] moveList = newMove(moveStackSize);
   public final int[] firstMove = new int[maxPly];
   public final Game[] gameList = newGame(gameStackSize);
   public final int[] board = new int[64];
   public final int[] color = new int[64];

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
