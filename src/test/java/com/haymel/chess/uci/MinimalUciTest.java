package com.haymel.chess.uci;

import org.junit.jupiter.api.Test;

final class MinimalUciTest {

   @Test
   void testPosition1() {
      //given
      MinimalUci minimalUci = new MinimalUci();
      String position =
         "position startpos moves " +
            "g1f3 c7c6 g2g3 d7d6 d2d4 c8g4 f1g2 d8a5 b1c3 c6c5 d4c5 a5c5 f3e5 c5e5 g2b7 " +
            "e5c5 d1d5 c5b4 b7c6 b8d7 c6d7 e8d7 d5a8 g4e2 a8a7 d7c8 e1e2 b4c4 e2e1 c4c6 " +
            "h1f1 g8f6 c1e3 f6g4 c3a4 g4e3 a4b6 c6b6 a7b6 e3c2 e1e2 c2a1 b6a7 a1b3 a2b3 c8d8 f1c1";
      minimalUci.handle("uci");
      minimalUci.handle(position);
      //when
      minimalUci.handle("go wtime 1067728 btime 707181 winc 0 binc 0");
   }

}