package com.haymel.chess.perft.move.white;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.haymel.chess.perft.move.Moves.*;
import static com.haymel.chess.perft.move.TestUtil.*;
import static org.junit.jupiter.params.provider.Arguments.of;

public final class PawnTest {

   static Stream<Arguments> whiteEnPassant() {
      return Stream.of(
         of("8/8/8/p7/8/8/8/8 w - a6 1 1", no),
         of("8/8/8/2p5/8/8/8/8 w - c6 1 1", no),
         of("8/8/8/1ppp4/8/8/8/8 w - c6 1 1 ", no),
         of("8/8/8/pP6/8/8/8/8 w - a6 1 1", m(b5a6, b5b6)),
         of("8/8/8/PpP5/8/8/8/8 w - b6 1 1", m(a5b6, c5b6, a5a6, c5c6)),
         of("8/8/8/6Pp/8/8/8/8 w - h6 1 1", m(g5h6, g5g6)),
         of("8/8/8/5PpP/8/8/8/8 w - g6 1 1", m(f5g6, h5g6, f5f6, h5h6))
      );
   }

   @ParameterizedTest
   @MethodSource("whiteEnPassant")
   void whiteEnPassant(String fen, Set<String> expectedMoves) {
      test(fen, expectedMoves);
   }

   static Stream<Arguments> whitePawnPromotion() {
      return Stream.of(
         of("8/3P4/8/8/8/8/8/8 w - - 0 1", m(d7d8q, d7d8r, d7d8b, d7d8n)),
         of("3k4/3P4/8/8/8/8/8/8 w - - 0 1", no),
         of("3K3k/3P4/8/8/8/8/8/8 w - - 0 1", m(/*other*/d8e8, d8e7, d8c7, d8c8)),
         of("2bbb2k/3P4/8/8/8/8/8/7K w - - 0 1", u(m(d7c8q, d7c8r, d7c8b, d7c8n, d7e8q, d7e8r, d7e8b, d7e8n), h1King)),
         of("1b5k/P7/8/8/8/8/8/7K w - - 0 1", u(m(a7a8q, a7a8r, a7a8b, a7a8n, a7b8q, a7b8r, a7b8b, a7b8n), h1King)),
         of("6b1/7P/8/8/8/8/8/k6K w - - 0 1 ", u(m(h7g8q, h7g8r, h7g8b, h7g8n, h7h8q, h7h8r, h7h8b, h7h8n), h1King))
      );
   }

   @ParameterizedTest
   @MethodSource("whitePawnPromotion")
   void whitePawnPromotion(String fen, Set<String> expectedMoves) {
      test(fen, expectedMoves);
   }

   static Stream<Arguments> whitePawn() {
      return Stream.of(
         of("8/8/8/8/8/8/4P3/8 w - - 0 1", m(e2e3, e2e4)),
         of("8/8/8/8/4K3/8/4P3/8 w - - 0 1", m(e2e3, e4e5, e4f5, e4f4, e4f3, e4e3, e4d3, e4d4, e4d5)),
         of("8/8/8/8/8/4k3/4P3/8 w - - 0 1", m()),
         of("8/8/8/8/4k3/8/4P3/8 w - - 0 1", m(e2e3)),
         of("8/8/8/8/8/3p4/4P3/8 w - - 0 1", m(e2e4,e2e3,e2d3)),
         of("8/8/8/8/8/5p2/4P3/8 w - - 0 1", m(e2e4,e2e3, e2f3)),
         of("8/8/8/8/8/3p1p2/4P3/8 w - - 0 1", m(e2e4,e2e3, e2f3, e2d3)),
         of("8/8/8/8/8/3P1P2/4P3/8 w - - 0 1", m(e2e3, e2e4,d3d4, f3f4))
      );
   }

   @ParameterizedTest
   @MethodSource("whitePawn")
   void whitePawn(String fen, Set<String> expectedMoves) {
      test(fen, expectedMoves);
   }

}
