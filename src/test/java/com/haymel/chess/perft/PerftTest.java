package com.haymel.chess.perft;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

final class PerftTest {

   @Test
   void test1() {
      Perft perft = new Perft("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
      long count = perft.execute(1);
      assertThat(count).isEqualTo(20);
   }

   @Test
   void test2() {
      Perft perft = new Perft("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
      long count = perft.execute(2);
      assertThat(count).isEqualTo(400);
   }

   @Test
   void test3() {
      Perft perft = new Perft("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
      long count = perft.execute(3);
      assertThat(count).isEqualTo(8902);
   }

   @Test
   void test4() {
      Perft perft = new Perft("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
      long count = perft.execute(4);
      assertThat(count).isEqualTo(197281);
   }

   @Test
   void test5() {
      Perft perft = new Perft("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
      long count = perft.execute(5);
      assertThat(count).isEqualTo(4_865_609);
   }

}