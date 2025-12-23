package com.haymel.chess.perft;

import java.util.Arrays;

import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Color.white;

public final class Castling {

   public final boolean[] queenside = new boolean[]{false, false};
   public final boolean[] kingside = new boolean[]{false, false};

   public void reset() {
      kingside[white] = false;
      queenside[white] = false;
      kingside[black] = false;
      queenside[black] = false;
   }

   public void assign(Castling castle) {
      kingside[white] = castle.kingside[white];
      queenside[white] = castle.queenside[white];
      kingside[black] = castle.kingside[black];
      queenside[black] = castle.queenside[black];
   }

   @Override
   public String toString() {
      return "Castling{" +
         "queenside=" + Arrays.toString(queenside) +
         ", kingside=" + Arrays.toString(kingside) +
         '}';
   }
}
