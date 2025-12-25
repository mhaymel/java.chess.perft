package com.haymel.chess.util;

import com.haymel.chess.perft.Move;
import com.haymel.chess.perft.Piece;

import static com.haymel.chess.perft.Piece.empty;
import static java.lang.String.format;

public final class MoveFromString {

   private final String moveString;

   public MoveFromString(String moveString) {
      this.moveString = verified(moveString);
   }

   private static String verified(String moveString) {
      if (moveString.length() < 4)
         throw new IllegalArgumentException(format("wrong length of '%s'", moveString.length()));
      return moveString;
   }

   public Move value() {
      int from = new FieldFromAlgebraic(moveString.substring(0, 2)).value();
      int to = new FieldFromAlgebraic(moveString.substring(2, 4)).value();

      return Move.NewMove(from, to, promotion());
   }

   private int promotion() {
      if (moveString.length() == 4) return empty;

      return switch(moveString.charAt(4)) {
         case 'q' -> Piece.queen;
         case 'b' -> Piece.bishop;
         case 'n' -> Piece.knight;
         case 'r' -> Piece.rook;
         default -> throw new IllegalStateException("Unexpected value: " + moveString.charAt(4));
      };
   }
}
