package com.haymel.chess.perft.move.black;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.haymel.chess.perft.move.Moves.*;
import static com.haymel.chess.perft.move.TestUtil.*;
import static org.junit.jupiter.params.provider.Arguments.of;

public final class KingTest {

   private static final Set<String> blackPawnInitialMoves = m(a7a6, a7a5, b7b6, b7b5, c7c6, c7c5, d7d6, d7d5, e7e6, e7e5, f7f6, f7f5, g7g6, g7g5, h7h6, h7h5);
   private static final Set<String> blackKnightInitialMoves = m(b8a6, b8c6, g8f6, g8h6);
   private static final Set<String> blackInitialMoves = u(blackPawnInitialMoves, blackKnightInitialMoves);

   static Stream<Arguments> blackKingMoves() {
      return Stream.of(
         of("8/8/8/4k3/8/8/8/7K b - - 0 1", m(e5e6, e5f6, e5f5, e5f4, e5e4, e5d4, e5d5, e5d6)),
         of("k7/8/8/8/8/8/8/K7 b - - 0 1", m(a8b8, a8b7, a8a7)),
         of("7k/8/8/8/8/8/8/K7 b - - 0 1", m(h8h7, h8g7, h8g8)),
         of("8/8/8/8/8/8/8/K6k b - - 0 1 ", m(h1g1, h1g2, h1h2)),
         of("7K/8/8/8/8/8/8/k7 b - - 0 1", m(a1a2, a1b2, a1b1)),
         of("7K/8/3PPP2/3PkP2/3PPP2/8/8/8 b - - 0 1", m(e5e6, e5f6, e5f5, e5f4, e5e4, e5d4, e5d5, e5d6))
      );
   }

   static Stream<Arguments> blackCastling() {
      return Stream.of(
         of("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", u(m(e8g8, e8c8,/*other*/e8d8, e8f8), blackPawnInitialMoves)),
         of("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQq - 0 1", u(m(e8c8, /*other*/e8d8, e8f8), blackPawnInitialMoves)),
         of("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQk - 0 1", u(m(e8g8, /*other*/e8d8, e8f8), blackPawnInitialMoves)),
         of("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQ - 0 1", u(m(/*other*/e8d8, e8f8), blackPawnInitialMoves)),
         of("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b - - 0 1", u(m(/*other*/e8d8, e8f8), blackPawnInitialMoves)),
         of("r3kb2/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", u(m(e8c8, /*other*/ e8d8), blackPawnInitialMoves)),
         of("r2qk2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", u(m(e8g8, /*other*/ e8f8), blackPawnInitialMoves)),
         of("r1b1k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", u(m(e8g8, /*other*/ e8d8, e8f8), blackPawnInitialMoves)),
         of("r2QkB1r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", u(m(/*other*/ e8d8, e8f8), blackPawnInitialMoves)));
   }

   @ParameterizedTest
   @MethodSource("blackKingMoves")
   void blackKingMoves(String fen, Set<String> expectedMoves) {
      test(fen, expectedMoves);
   }

   @Test
   void blackKingMove1() {
      test("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", blackInitialMoves);
   }

   @ParameterizedTest
   @MethodSource("blackCastling")
   void blackCastling(String fen, Set<String> expectedMoves) {
      test(fen, expectedMoves);
   }

   @Test
   void blackCastling1() {
      test("r3k1nr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1",
         u(m(e8c8, /*other*/ e8d8, e8f8, g8f6, g8h6), blackPawnInitialMoves));
   }

   @Test
   void blackCastling2() {
      test("rn2k2r/pppppppp/8/8/8/8/PPPPPPPP/RNB1KBNR b KQkq - 0 1",
         u(m(e8g8, /*other*/ e8d8, e8f8, b8a6, b8c6), blackPawnInitialMoves));
   }

}
