package com.haymel.chess.perft;

import org.junit.jupiter.api.Test;

import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.Notation.algebraic;
import static org.assertj.core.api.Assertions.assertThat;

final class NotationTest {

   @Test
   void test() {
      assertThat(algebraic(a1)).isEqualTo("a1");
      assertThat(algebraic(h1)).isEqualTo("h1");
      assertThat(algebraic(a8)).isEqualTo("a8");
      assertThat(algebraic(h8)).isEqualTo("h8");
      assertThat(algebraic(e4)).isEqualTo("e4");
      assertThat(algebraic(d4)).isEqualTo("d4");
   }

}