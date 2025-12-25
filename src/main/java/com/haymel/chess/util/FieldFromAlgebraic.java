package com.haymel.chess.util;

import static java.lang.String.format;

public final class FieldFromAlgebraic {
   private final String field;

   public FieldFromAlgebraic(String field) {
      this.field = verified(field);
   }

   private static String verified(String field) {
      if (field.length() != 2)
         throw new IllegalArgumentException(format("wrong length of '%s'", field.length()));
      return field;
   }

   public int value() {
      int x = field.charAt(0) - 'a';
      int y = field.charAt(1) - '1';
      return y*8 + x;
   }
}
