package com.haymel.chess.util;

import com.haymel.chess.perft.Move;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

final class MoveFromStringTest {

   @Test
   void testA1B1() {
      test("a1b1");
   }

   static Stream<Arguments> moves() {
      return Stream.of(
         of("a1b1"),
         of("a8h8"),
         of("e7e8q"),
         of("e7e8r"),
         of("e7e8b"),
         of("e7e8n")
      );
   }

   @ParameterizedTest
   @MethodSource("moves")
   void testMoves(String move) {
      test(move);
   }

   void test(String moveAsString) {
      MoveFromString moveFromString = new MoveFromString(moveAsString);
      Move move = moveFromString.value();
      assertThat(move.toString()).isEqualTo(moveAsString);
   }
}