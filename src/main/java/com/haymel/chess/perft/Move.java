package com.haymel.chess.perft;

public final class Move {

   public int from;
   public int to;
   public int promotion;

   public static Move NewMove(int from, int to) { return new Move(from, to); }
   public Move() {
      this(Field.invalid, Field.invalid, Piece.empty);
   }

   public Move(int from, int to) {
      this(from, to, Piece.empty);
   }

   public Move(int from, int to, int promotion) {
      this.from = from;
      this.to = to;
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
