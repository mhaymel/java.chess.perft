package com.haymel.chess.util;

public final class Util {

   public static String withDots(long value) {
      String s = Long.toString(value);
      StringBuilder sb = new StringBuilder();

      int count = 0;
      for (int i = s.length() - 1; i >= 0; i--) {
         sb.append(s.charAt(i));
         count++;
         if (count == 3 && i > 0) {
            sb.append('.');
            count = 0;
         }
      }

      String formatted = sb.reverse().toString();

      // Pad to width 15 (enough for billions)
      return String.format("%15s", formatted);
   }
}
