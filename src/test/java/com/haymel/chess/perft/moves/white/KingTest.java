package com.haymel.chess.perft.moves.white;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.haymel.chess.perft.moves.Moves.*;
import static com.haymel.chess.perft.moves.TestUtil.*;
import static org.junit.jupiter.params.provider.Arguments.of;

public final class KingTest {

   private static final Set<String> whitePawnInitialMoves = m(a2a3, a2a4, b2b3, b2b4, c2c3, c2c4, d2d3, d2d4, e2e3, e2e4, f2f3, f2f4, g2g3, g2g4, h2h3, h2h4);
   private static final Set<String> whiteIKnightMoves = m(b1a3, b1c3, g1f3, g1h3);
   private static final Set<String> whiteInitialMoves = u(whitePawnInitialMoves, whiteIKnightMoves);

   static Stream<Arguments> whiteKingMoves() {
      return Stream.of(
         of("k7/8/8/8/4K3/8/8/8 w - - 1 1", m(e4e5, e4f5, e4f4, e4f3, e4e3, e4d3, e4d4, e4d5)),
         of("k7/8/8/8/8/8/8/K7 w - - 1 1", m(a1a2, a1b2, a1b1)),
         of("k7/8/8/8/8/8/8/7K w - - 1 1", m(h1h2, h1g1, h1g2)),
         of("k6K/8/8/8/8/8/8/8 w - - 1 1", m(h8h7, h8g7, h8g8)),
         of("K7/8/8/8/8/8/8/7k w - - 1 1", m(a8b8, a8b7, a8a7)),
         of("4k3/8/8/3ppp2/3pKp2/3ppp2/8/8 w - - 0 1", m(e4e5, e4f5, e4f4, e4f3, e4e3, e4d3, e4d4, e4d5))
      );
   }

   static Stream<Arguments> whiteCastling() {
      return Stream.of(
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w KQkq - 0 1", u(m(e1g1, e1c1, /*other*/ e1d1, e1f1), whitePawnInitialMoves)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w Qkq - 0 1", u(m(e1c1, /*other*/ e1d1, e1f1), whitePawnInitialMoves)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w Kkq - 0 1", u(m(e1g1, /*other*/ e1d1, e1f1), whitePawnInitialMoves)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w kq - 0 1", u(m(/*other*/ e1d1, e1f1), whitePawnInitialMoves)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w - - 0 1", u(m(/*other*/ e1d1, e1f1), whitePawnInitialMoves)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3KB1R w KQkq - 0 1", u(m(e1c1,/*other*/ e1d1), whitePawnInitialMoves)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R2QK2R w KQkq - 0 1", u(m(e1g1, /*other*/ e1f1), whitePawnInitialMoves)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R1B1K2R w KQkq - 0 1", u(m(e1g1, /*other*/ e1d1, e1f1), whitePawnInitialMoves)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R2qKb1R w KQkq - 0 1", u(m(/*other*/ e1d1, e1f1), whitePawnInitialMoves)));
   }

   @ParameterizedTest
   @MethodSource("whiteKingMoves")
   void whiteKingMoves(String fen, Set<String> expectedMoves) {
      test("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", whiteInitialMoves);
   }

   @ParameterizedTest
   @MethodSource("whiteCastling")
   void whiteCastling(String fen, Set<String> expectedMoves) {
      test(fen, expectedMoves);
   }

   @Test
   void whiteCastling1() {
      test("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RN2K2R w KQkq - 0 1",
         u(m(e1g1, /*other*/ e1d1, e1f1, b1a3, b1c3), whitePawnInitialMoves));
   }

   @Test
   void whiteCastling2() {
      test("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RN2K2R w KQkq - 0 1",
         u(m(e1g1, /*other*/ e1d1, e1f1, b1a3, b1c3), whitePawnInitialMoves));
   }

   @Test
   void whiteCastling3() {
      test("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K1NR w KQkq - 0 1",
         u(m(e1c1, /*other*/ e1d1, e1f1, g1f3, g1h3), whitePawnInitialMoves));
   }

}
