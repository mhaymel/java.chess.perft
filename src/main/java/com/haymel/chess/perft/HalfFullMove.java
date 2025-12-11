package com.haymel.chess.perft;

public final class HalfFullMove {
   public static HalfFullMove NewHalfFullMove() { return new HalfFullMove(); }
   
   public int halfMoveClock;
   public int fullMoveNumber;
}
