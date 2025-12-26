package com.haymel.chess.perft;

import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Color.white;

public final class VerifyChess {

   private final Chess chess;

   public VerifyChess(String fen) {
      this(Fen.load(fen));
   }

   public VerifyChess(Chess chess) {
      this.chess = chess;
   }

   public boolean isOk() {
      if (chess.kingloc[white] == Field.invalid)
         return false;
      if (chess.kingloc[black] == Field.invalid)
         return false;

      return true;
   }
}
