package com.haymel.chess.perft;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

final class SearchTest {

   @Test
   void testSearchDepth1() {
      test(1);
   }

   @Test
   void testSearchDepth2() {
      test(2);
   }

   @Test
   void testSearchDepth3() {
      test(3);
   }

   @Test
   void testSearchDepth4() {
      test(4);
   }

   @Test
   void testSearchDepth5() {
      test(5);
   }

   @Test
   void testSearchDepth6() {
      test(6);
   }

   void test(int depth) {
      //given
      Chess chess = Fen.load(Fen.initial);
      Search search = new Search(chess);

      //when
      Move bestMove = search.search(depth);

      //then
      assertThat(bestMove).isNotNull();
   }
}