package com.haymel.chess.util;

import com.haymel.chess.perft.Move;

import static com.haymel.chess.util.MoveFromString.NewMoveFromString;
import static java.util.Arrays.stream;

public final class MovesFromString {

   private final String movesString;

   public static MovesFromString NewMovesFromString(String movesString) { return new MovesFromString(movesString); }
   public MovesFromString(String movesString) { this.movesString = movesString; }

   public Move[] values() {
      return stream(movesAsStrings())
         .map(s -> NewMoveFromString(s).value())
         .toArray(Move[]::new);
   }

   private String[] movesAsStrings() {
      return movesString.split(" ");
   }
}
