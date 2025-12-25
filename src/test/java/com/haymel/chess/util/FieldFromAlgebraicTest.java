package com.haymel.chess.util;

import org.junit.jupiter.api.Test;

import static com.haymel.chess.perft.Field.a1;
import static com.haymel.chess.perft.Field.h8;
import static com.haymel.chess.perft.Notation.algebraic;
import static org.assertj.core.api.Assertions.assertThat;

final class FieldFromAlgebraicTest {

   @Test
   void test() {
      for (int i = a1; i <= h8; i++)
         test(i);
   }

   void test(int field) {
      String algebraic = algebraic(field);
      FieldFromAlgebraic fieldFromAlgebraic = new FieldFromAlgebraic(algebraic);
      assertThat(fieldFromAlgebraic.value()).isEqualTo(field);
   }
}