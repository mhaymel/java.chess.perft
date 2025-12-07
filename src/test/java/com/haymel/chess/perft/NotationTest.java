package com.haymel.chess.perft;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.GenTest.e2e4;
import static com.haymel.chess.perft.GenTest.e7e8;
import static com.haymel.chess.perft.Notation.algebraic;
import static com.haymel.chess.perft.Piece.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

final class NotationTest {

   private static final String e7e8q = e7e8 + "q";
   private static final String e7e8r = e7e8 + "r";
   private static final String e7e8b = e7e8 + "b";
   private static final String e7e8n = e7e8 + "n";

   static Stream<Arguments> uciFromTo() {
      return Stream.of(
         of(e2, e4, e2e4));
   }

   static Stream<Arguments> uciFromToPromotion() {
      return Stream.of(
         of(e7, e8, queen, e7e8q),
         of(e7, e8, rook, e7e8r),
         of(e7, e8, bishop, e7e8b),
         of(e7, e8, knight, e7e8n)
      );
   }

   @ParameterizedTest
   @MethodSource("uciFromTo")
   void uciFromTo(int from, int to, String s) {
      assertThat(Notation.uci(from, to)).isEqualTo(s);
   }

   @ParameterizedTest
   @MethodSource("uciFromToPromotion")
   void uciFromToPromotion(int from, int to, int piece, String s) {
      assertThat(Notation.uci(from, to, piece)).isEqualTo(s);
   }

   static Stream<Arguments> uciFrom() {
      return Stream.of(
         of(a1, "a1"),
         of(h1, "h1"),
         of(a8, "a8"),
         of(h8, "h8"),
         of(e4, "e4"),
         of(d4, "d4")
      );
   }

   @ParameterizedTest
   @MethodSource("uciFrom")
   void uciFrom(int from, String s) {
      assertThat(Notation.algebraic(from)).isEqualTo(s);
   }

}