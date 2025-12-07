package com.haymel.chess.perft;

import static com.haymel.chess.perft.Field.a1;
import static com.haymel.chess.perft.Field.h8;
import static java.lang.String.format;

public final class Notation {
   public static String algebraic(int field) {
      if (field < a1 || field > h8) throw new IllegalArgumentException(format("invalid field value '%s'", field));
      char letter = (char) ('a' + field % 8);
      char digit = (char) ('1' + field / 8);
      return String.valueOf(letter) + digit;
   }

   public static String uci(int from, int to) {
      return algebraic(from) + algebraic(to);
   }

   public static String uci(int from, int to, int piece) {
      return algebraic(from) + algebraic(to) + promotion(piece);
   }

   private static String promotion(int piece) {
      return switch (piece) {
         case Piece.empty -> "";
         case Piece.queen -> "q";
         case Piece.bishop -> "b";
         case Piece.knight -> "n";
         case Piece.rook -> "r";
         default -> throw new IllegalStateException("Unexpected value: " + piece);
      };
   }

}
