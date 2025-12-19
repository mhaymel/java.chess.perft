package com.haymel.chess.perft.util;

import com.haymel.chess.perft.Color;

public enum ColorEnum {
   white(Color.white),
   black(Color.black);

   private final int color;

   ColorEnum(int color) { this.color = color; }

   public int color() { return color; }
}
