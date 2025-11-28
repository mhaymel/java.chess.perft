package com.haymel.chess.perft;

import static com.haymel.chess.perft.Field.e2;
import static com.haymel.chess.perft.Field.e4;
import static com.haymel.chess.perft.Piece.pawn;

public final class Util {

   private static final int doubleMove = e4 - e2;

   public static boolean isPawnDoubleMove(Game game, int[] board) {
      return isPawn(game.dest, board) && distance(game) == doubleMove;
   }

   private static int distance(Game game) { return Math.abs(game.dest - game.start); }

   public static boolean isPawn(int field, int[] board) {
      return board[field] == pawn;
   }

}


