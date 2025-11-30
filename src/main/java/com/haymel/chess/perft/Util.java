package com.haymel.chess.perft;

import static com.haymel.chess.perft.Piece.pawn;

public final class Util {

   public static boolean isPawn(int field, int[] board) {
      return board[field] == pawn;
   }

}


