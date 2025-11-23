package com.haymel.chess.perft;

import static com.haymel.chess.perft.Field.E2;
import static com.haymel.chess.perft.Field.E4;
import static com.haymel.chess.perft.Piece.pawn;

public final class Util {

   private static final int doubleMove = E4.index() - E2.index();

   public static boolean isPawnDoubleMove(Game game) {
      return isPawn(game.dest) && distance(game) == doubleMove;
   }

   private static int distance(Game game) {
      return distance(game.dest, game.start);
   }

   public static boolean isPawn(Field field) {
      return board[field.index()] == pawn;
   }

   public static int distance(Field field1, Field field2) {
      return abs(field1.index() - field2.index());
   }
}


