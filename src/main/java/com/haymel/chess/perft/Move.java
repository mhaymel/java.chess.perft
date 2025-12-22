package com.haymel.chess.perft;

public final class Move {

   public int from;
   public int to;
   public int promotion;

   public Move() {
      this(Field.invalid, Field.invalid, Piece.empty);
   }

   public Move(int start, int dest) {
      this(start, dest, Piece.empty);
   }

   public Move(int start, int dest, int promotion) {
      this.from = start;
      this.to = dest;
      this.promotion = promotion;
   }

   public String uci() {
      if (from == Field.invalid || to == Field.invalid) return "invalid";
      return Notation.uci(from, to, promotion);
   }

   @Override
   public String toString() {
      return uci();
   }
}
