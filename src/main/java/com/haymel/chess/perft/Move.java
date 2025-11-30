package com.haymel.chess.perft;

public final class Move {

   public int start;
   public int dest;

   public String alg() {
      return Notation.alg(start, dest);
   }

   @Override
   public String toString() {
      return alg();
   }
}
