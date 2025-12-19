package com.haymel.chess.perft;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Color.white;
import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.HalfFullMove.NewHalfFullMove;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

final class AttackTest {

   static Stream<Arguments> whitePawnAttacks() {
      return Stream.of(
         //pwan attacks
         of("8/8/8/8/3P4/8/8/8 w - - 0 1", white, c5, true),
         of("8/8/8/8/3P4/8/8/8 w - - 0 1", white, e5, true),
         of("8/8/8/8/3P4/8/8/8 w - - 0 1", white, d5, false),

         of("8/8/8/8/8/8/8/8 b - - 0 1", white, a1, false),
         of("8/8/8/8/8/8/8/8 b - - 0 1", white, h1, false),
         of("8/8/8/8/8/8/8/8 b - - 0 1", white, a8, false),
         of("8/8/8/8/8/8/8/8 b - - 0 1", white, h8, false),

         of("8/7P/8/8/8/8/8/8 b - - 0 1", white, g8, true),
         of("8/7P/8/8/8/8/8/8 b - - 0 1", white, h8, false),
         of("8/P7/8/8/8/8/8/8 b - - 0 1", white, b8, true),
         of("8/P7/8/8/8/8/8/8 b - - 0 1", white, a8, false),

         of("8/6P1/8/8/8/8/8/8 b - - 0 1", white, f8, true),
         of("8/6P1/8/8/8/8/8/8 b - - 0 1", white, g8, false),
         of("8/6P1/8/8/8/8/8/8 b - - 0 1", white, h8, true),

         of("8/1P6/8/8/8/8/8/8 b - - 0 1 ", white, a8, true),
         of("8/1P6/8/8/8/8/8/8 b - - 0 1", white, b8, false),
         of("8/1P6/8/8/8/8/8/8 b - - 0 1", white, c8, true)
      );
   }

   static Stream<Arguments> blackPawnAttacks() {
      return Stream.of(
         //pwan attacks
         of("8/8/8/4p3/8/8/8/8 b - - 0 1", black, d4, true),
         of("8/8/8/4p3/8/8/8/8 b - - 0 1", black, f4, true),
         of("8/8/8/4p3/8/8/8/8 b - - 0 1", black, e4, false),

         of("8/8/8/8/8/8/8/8 b - - 0 1", black, a1, false),
         of("8/8/8/8/8/8/8/8 b - - 0 1", black, h1, false),
         of("8/8/8/8/8/8/8/8 b - - 0 1", black, a8, false),
         of("8/8/8/8/8/8/8/8 b - - 0 1", black, h8, false),

         of("8/8/8/8/8/8/6p1/8 b - - 0 1 ", black, h1, true),
         of("8/8/8/8/8/8/6p1/8 b - - 0 1 ", black, g1, false),
         of("8/8/8/8/8/8/6p1/8 b - - 0 1 ", black, f1, true),

         of("8/8/8/8/8/8/1p6/8 b - - 0 1  ", black, a1, true),
         of("8/8/8/8/8/8/1p6/8 b - - 0 1  ", black, b1, false),
         of("8/8/8/8/8/8/1p6/8 b - - 0 1  ", black, c1, true)
      );
   }

   @ParameterizedTest
   @MethodSource("whitePawnAttacks")
   void whitePawnAttacks(String fen, int side, int field, boolean expected) {
      test(fen, side, field, expected);
   }

   @ParameterizedTest
   @MethodSource("blackPawnAttacks")
   void blackPawnAttacks(String fen, int side, int field, boolean expected) {
      test(fen, side, field, expected);
   }

   @Test
   void whitePawn1() {
      test("8/8/8/8/3P4/8/8/8 w - - 0 1 ", white, c5, true);
   }

   @Test
   void whitePawn2() {
      test("8/6P1/8/8/8/8/8/8 b - - 0 1", white, h8, true);
   }

   void test(String fen, int side, int field, boolean expected) {
      //given
      Chess chess = new Chess();
      Fen.load(fen, chess, NewHalfFullMove());
      Attack attack = Attack.NewAttack(chess);

      //when
      boolean attacked = attack.attack(side, field);

      //then
      assertThat(attacked).isEqualTo(expected);
   }

}