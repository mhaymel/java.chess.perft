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

   @Test
   void testPosition2() throws InterruptedException {
      //given
      MinimalUci minimalUci = new MinimalUci();
      String position =
         "position startpos moves " +
            "e2e4 d7d6 f1c4 e7e6 g1f3 b8c6 e1g1 g8f6 b1c3 c6e5 f3e5 d6e5 d2d3 d8d4 c1e3 d4d6 c3b5 d6c6 c2c3 " +
            "f8c5 e3c5 c6c5 b2b4 c5c6 d1d2 e8g8 f1c1 f8e8 a2a4 c8d7 d2e2 a8d8 d3d4 c6e4 e2e4 f6e4 b5c7 e5d4 " +
            "c7e8 d7e8 c3d4 d8d4 b4b5 d4d2 f2f3 e4f6 c1d1 d2d1 a1d1 e8d7 a4a5 g8h8 b5b6 d7c6 b6a7 c6d5 a7a8q " +
            "f6g8 c4d5 e6d5 d1d5 b7b6 a5b6 f7f6 d5d8";
      minimalUci.handle("uci");
      minimalUci.handle(position);
      //when
      minimalUci.handle("go wtime 50000 btime 50000 winc 0 binc 0");
      minimalUci.waitForSearchFinished();
   }

}