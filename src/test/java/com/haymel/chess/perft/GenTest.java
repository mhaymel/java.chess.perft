package com.haymel.chess.perft;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class GenTest {

   @Test
   void sampleTest() {
      //given
      Chess chess = new Chess();
      chess.initBoard();
      Gen gen = new Gen(chess);

      //when
      gen.execute();

      //then
   }
}