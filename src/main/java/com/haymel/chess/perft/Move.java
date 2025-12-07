package com.haymel.chess.perft;

public final class Move {

   public int start;
   public int dest;
   public int promotion;

   public Move() {
      this(Field.invalid, Field.invalid, Piece.empty);
   }

   public Move(int start, int dest) {
      this(start, dest, Piece.empty);
   }

   public Move(int start, int dest, int promotion) {
      this.start = start;
      this.dest = dest;
      this.promotion = promotion;
   }

   public String uci() {
      if (start == Field.invalid || dest == Field.invalid)
         return "invalid";

      return Notation.uci(start, dest, promotion);
   }

   @Override
   public String toString() {
      return uci();
   }
}
