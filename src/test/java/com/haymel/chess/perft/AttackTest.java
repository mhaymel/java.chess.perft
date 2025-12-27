package com.haymel.chess.perft;

import com.haymel.chess.perft.util.ColorEnum;
import com.haymel.chess.perft.util.FieldEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.haymel.chess.perft.util.ColorEnum.black;
import static com.haymel.chess.perft.util.ColorEnum.white;
import static com.haymel.chess.perft.util.FieldEnum.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

final class AttackTest {

   static Stream<Arguments> whitePawnAttacks() {
      return Stream.of(
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
   void whitePawnAttacks(String fen, ColorEnum side, FieldEnum field, boolean expected) {
      test(fen, side, field, expected);
   }

   @ParameterizedTest
   @MethodSource("blackPawnAttacks")
   void blackPawnAttacks(String fen, ColorEnum side, FieldEnum field, boolean expected) {
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

   static Stream<Arguments> whiteKnightAttacks() {
      return Stream.of(
         of("8/8/8/8/8/8/8/8 b - - 0 1", white, a1, false),
         of("8/8/8/8/8/8/8/8 b - - 0 1", white, h1, false),
         of("8/8/8/8/8/8/8/8 b - - 0 1", white, a8, false),
         of("8/8/8/8/8/8/8/8 b - - 0 1", white, h8, false),

         of("8/2N2N2/1N4N1/8/8/1N4N1/2N2N2/8 b - - 0 1", white, a1, true),
         of("8/2N2N2/1N4N1/8/8/1N4N1/2N2N2/8 b - - 0 1", white, h1, true),
         of("8/2N2N2/1N4N1/8/8/1N4N1/2N2N2/8 b - - 0 1", white, a8, true),
         of("8/2N2N2/1N4N1/8/8/1N4N1/2N2N2/8 b - - 0 1", white, h8, true),

         of("8/2n2n2/1n4n1/8/8/1n4n1/2n2n2/8 b - - 0 1 ", white, a1, false),
         of("8/2n2n2/1n4n1/8/8/1n4n1/2n2n2/8 b - - 0 1 ", white, h1, false),
         of("8/2n2n2/1n4n1/8/8/1n4n1/2n2n2/8 b - - 0 1 ", white, a8, false),
         of("8/2n2n2/1n4n1/8/8/1n4n1/2n2n2/8 b - - 0 1 ", white, h8, false),

         of("8/8/8/8/4N3/8/8/8 b - - 0 1", white, f6, true),
         of("8/8/8/8/4N3/8/8/8 b - - 0 1", white, g5, true),
         of("8/8/8/8/4N3/8/8/8 b - - 0 1", white, g3, true),
         of("8/8/8/8/4N3/8/8/8 b - - 0 1", white, f2, true),
         of("8/8/8/8/4N3/8/8/8 b - - 0 1", white, d2, true),
         of("8/8/8/8/4N3/8/8/8 b - - 0 1", white, c3, true),
         of("8/8/8/8/4N3/8/8/8 b - - 0 1", white, c5, true),
         of("8/8/8/8/4N3/8/8/8 b - - 0 1", white, d6, true),

         of("8/8/8/8/4n3/8/8/8 b - - 0 1", white, f6, false),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", white, g5, false),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", white, g3, false),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", white, f2, false),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", white, d2, false),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", white, c3, false),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", white, c5, false),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", white, d6, false)
      );
   }

   @ParameterizedTest
   @MethodSource("whiteKnightAttacks")
   void whiteKnightAttacks(String fen, ColorEnum side, FieldEnum field, boolean expected) {
      test(fen, side, field, expected);
   }

   static Stream<Arguments> blackKnightAttacks() {
      return Stream.of(
         of("8/8/8/8/8/8/8/8 b - - 0 1", black, a1, false),
         of("8/8/8/8/8/8/8/8 b - - 0 1", black, h1, false),
         of("8/8/8/8/8/8/8/8 b - - 0 1", black, a8, false),
         of("8/8/8/8/8/8/8/8 b - - 0 1", black, h8, false),

         of("8/2n2n2/1n4n1/8/8/1n4n1/2n2n2/8 b - - 0 1", black, a1, true),
         of("8/2n2n2/1n4n1/8/8/1n4n1/2n2n2/8 b - - 0 1", black, h1, true),
         of("8/2n2n2/1n4n1/8/8/1n4n1/2n2n2/8 b - - 0 1", black, a8, true),
         of("8/2n2n2/1n4n1/8/8/1n4n1/2n2n2/8 b - - 0 1", black, h8, true),

         of("8/2N2N2/1N4N1/8/8/1N4N1/2N2N2/8 b - - 0 1", black, a1, false),
         of("8/2N2N2/1N4N1/8/8/1N4N1/2N2N2/8 b - - 0 1", black, h1, false),
         of("8/2N2N2/1N4N1/8/8/1N4N1/2N2N2/8 b - - 0 1", black, a8, false),
         of("8/2N2N2/1N4N1/8/8/1N4N1/2N2N2/8 b - - 0 1", black, h8, false),

         of("8/8/8/8/4n3/8/8/8 b - - 0 1", black, f6, true),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", black, g5, true),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", black, g3, true),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", black, f2, true),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", black, d2, true),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", black, c3, true),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", black, c5, true),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", black, d6, true),

         of("8/8/8/8/4n3/8/8/8 b - - 0 1", white, f6, false),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", white, g5, false),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", white, g3, false),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", white, f2, false),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", white, d2, false),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", white, c3, false),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", white, c5, false),
         of("8/8/8/8/4n3/8/8/8 b - - 0 1", white, d6, false)
      );
   }

   static Stream<Arguments> whiteKingAttacks() {
      return Stream.of(
         of("8/8/8/8/8/8/8/K7 b - - 0 1", white, a2, true),
         of("8/8/8/8/8/8/8/K7 b - - 0 1", white, b2, true),
         of("8/8/8/8/8/8/8/K7 b - - 0 1", white, b1, true),

         of("8/8/8/8/8/8/8/7K b - - 0 1", white, g1, true),
         of("8/8/8/8/8/8/8/7K b - - 0 1", white, g2, true),
         of("8/8/8/8/8/8/8/7K b - - 0 1", white, h2, true),

         of("7K/8/8/8/8/8/8/8 b - - 0 1", white, h7, true),
         of("7K/8/8/8/8/8/8/8 b - - 0 1", white, g7, true),
         of("7K/8/8/8/8/8/8/8 b - - 0 1", white, g8, true),

         of("K7/8/8/8/8/8/8/8 b - - 0 1", white, a7, true),
         of("K7/8/8/8/8/8/8/8 b - - 0 1", white, b7, true),
         of("K7/8/8/8/8/8/8/8 b - - 0 1", white, b8, true),

         of("8/8/8/8/4K3/8/8/8 b - - 0 1", white, e5, true),
         of("8/8/8/8/4K3/8/8/8 b - - 0 1", white, f5, true),
         of("8/8/8/8/4K3/8/8/8 b - - 0 1", white, f4, true),
         of("8/8/8/8/4K3/8/8/8 b - - 0 1", white, f3, true),
         of("8/8/8/8/4K3/8/8/8 b - - 0 1", white, e3, true),
         of("8/8/8/8/4K3/8/8/8 b - - 0 1", white, d3, true),
         of("8/8/8/8/4K3/8/8/8 b - - 0 1", white, d4, true),
         of("8/8/8/8/4K3/8/8/8 b - - 0 1", white, d5, true)
      );
   }

   @ParameterizedTest
   @MethodSource("whiteKingAttacks")
   void whiteKingAttacks(String fen, ColorEnum side, FieldEnum field, boolean expected) {
      test(fen, side, field, expected);
   }

   static Stream<Arguments> blackKingAttacks() {
      return Stream.of(
         of("8/8/8/8/8/8/8/k7 b - - 0 1", black, a2, true),
         of("8/8/8/8/8/8/8/k7 b - - 0 1", black, b2, true),
         of("8/8/8/8/8/8/8/k7 b - - 0 1", black, b1, true),

         of("8/8/8/8/8/8/8/7k b - - 0 1", black, h2, true),
         of("8/8/8/8/8/8/8/7k b - - 0 1", black, g2, true),
         of("8/8/8/8/8/8/8/7k b - - 0 1", black, g1, true),

         of("7k/8/8/8/8/8/8/8 b - - 0 1", black, h7, true),
         of("7k/8/8/8/8/8/8/8 b - - 0 1", black, g7, true),
         of("7k/8/8/8/8/8/8/8 b - - 0 1", black, g8, true),

         of("k7/8/8/8/8/8/8/8 b - - 0 1", black, a7, true),
         of("k7/8/8/8/8/8/8/8 b - - 0 1", black, b7, true),
         of("k7/8/8/8/8/8/8/8 b - - 0 1", black, b8, true),

         of("8/8/8/8/4k3/8/8/8 b - - 0 1 ", black, e5, true),
         of("8/8/8/8/4k3/8/8/8 b - - 0 1", black, f5, true),
         of("8/8/8/8/4k3/8/8/8 b - - 0 1", black, f4, true),
         of("8/8/8/8/4k3/8/8/8 b - - 0 1", black, f3, true),
         of("8/8/8/8/4k3/8/8/8 b - - 0 1", black, e3, true),
         of("8/8/8/8/4k3/8/8/8 b - - 0 1", black, d3, true),
         of("8/8/8/8/4k3/8/8/8 b - - 0 1", black, d4, true),
         of("8/8/8/8/4k3/8/8/8 b - - 0 1", black, d5, true)
      );
   }

   @ParameterizedTest
   @MethodSource("blackKingAttacks")
   void blackKingAttacks(String fen, ColorEnum side, FieldEnum field, boolean expected) {
      test(fen, side, field, expected);
   }

   @ParameterizedTest
   @MethodSource("blackKnightAttacks")
   void blackKnightAttacks(String fen, ColorEnum side, FieldEnum field, boolean expected) {
      test(fen, side, field, expected);
   }

   static Stream<Arguments> whiteRookAttacks() {
      return Stream.of(
         of("8/8/8/8/8/8/8/R7 b - - 0 1", white, Set.of(a2,a3,a4,a5,a6,a7,a8,b1,c1,d1,e1,f1,g1,h1), true),
         of("8/8/8/8/8/8/8/R7 b - - 0 1", black, Set.of(a2,a3,a4,a5,a6,a7,a8,b1,c1,d1,e1,f1,g1,h1), false),
         of("8/8/8/N7/8/8/8/R2N4 b - - 0 1 ", white, Set.of(a2,a3,a4,a5,b1,c1,d1), true),
         of("8/8/8/N7/8/8/8/R2N4 b - - 0 1", white, Set.of(a6,a7,a8,e1,f1,g1,h1), false)
         );
   }

   @ParameterizedTest
   @MethodSource("whiteRookAttacks")
   void whiteRookAttacks(String fen, ColorEnum side, Set<FieldEnum> fields, boolean expected) {
         test(fen, side, fields, expected);
   }

   @Test
   void whiteRookAttack1() {
      test("8/8/8/8/8/8/8/R7 b - - 0 1", black, Set.of(a2,a3,a4,a5,a6,a7,a8,b1,c1,d1,e1,f1,g1,h1), false);
   }

   @Test
   void whiteRookAttack2() {
      test("8/8/8/8/8/8/8/R7 b - - 0 1", black, c1, false);
   }

   static Stream<Arguments> blackRookAttacks() {
      return Stream.of(
         of("8/8/8/8/8/8/8/r7 b - - 0 1", black, Set.of(a2,a3,a4,a5,a6,a7,a8,b1,c1,d1,e1,f1,g1,h1), true),
         of("8/8/8/8/8/8/8/r7 b - - 0 1", white, Set.of(a2,a3,a4,a5,a6,a7,a8,b1,c1,d1,e1,f1,g1,h1), false),
         of("8/8/8/N7/8/8/8/r2N4 b - - 0 1 ", black, Set.of(a2,a3,a4,a5,b1,c1,d1), true),
         of("8/8/8/N7/8/8/8/r2N4 b - - 0 1", black, Set.of(a6,a7,a8,e1,f1,g1,h1), false)
      );
   }

   @ParameterizedTest
   @MethodSource("blackRookAttacks")
   void blackRookAttacks(String fen, ColorEnum side, Set<FieldEnum> fields, boolean expected) {
      test(fen, side, fields, expected);
   }

   void test(String fen, ColorEnum side, Set<FieldEnum> fields, boolean expected) {
      for (FieldEnum field : fields) {
         test(fen, side, field, expected);
      }
   }

   void test(String fen, ColorEnum side, FieldEnum field, boolean expected) {
      //given
      Chess chess = new Chess();
      Fen.load(fen, chess);
      Attack attack = Attack.NewAttack(chess);

      //when
      boolean attacked = attack.attack(side.color(), field.value);

      //then
      assertThat(attacked).isEqualTo(expected);
   }

   @Test
   void blackPawnAttacksF1() {
      test("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q2/PPPBBPpP/1R2K2R w Kkq - 0 2", black, f1, true);
   }

}