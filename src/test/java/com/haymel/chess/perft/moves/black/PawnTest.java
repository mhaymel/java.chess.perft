package com.haymel.chess.perft.moves.black;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.collect.Sets.union;
import static com.haymel.chess.perft.moves.Moves.*;
import static com.haymel.chess.perft.moves.TestUtil.*;
import static org.junit.jupiter.params.provider.Arguments.of;

public final class PawnTest {

   static Stream<Arguments> blackEnPassant() {
      return Stream.of(
         of("7k/8/8/8/P7/8/8/7K b - a3 1 1", h8King),
         of("7k/8/8/8/2P5/8/8/7K b - c3 1 1", h8King),
         of("7k/8/8/8/1PPP4/8/8/7K b - c3 1 1", h8King),
         of("7k/8/8/8/Pp6/8/8/4K3 b - a3 1 1", union(m(b4a3,/*other*/ b4b3), h8King)),
         of("7k/8/8/8/pPp5/8/8/7K b - b3 1 1 ", u(m(a4b3, c4b3,/*other*/a4a3, c4c3), h8King)),
         of("7k/8/8/8/6pP/8/8/7K b - h3 1 1", u(m(g4h3, /*other*/ g4g3), h8King)),
         of("7k/8/8/8/5pPp/8/8/7K b - g3 1 1 ", u(m(f4g3, h4g3, /*other*/f4f3, h4h3), h8King))
      );
   }

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

   static Stream<Arguments> blackPawnPromotion() {
      return Stream.of(
         // Black en passant
         of("7k/8/8/8/8/8/3p4/7K b - - 0 1   ", u(m(d2d1q, d2d1r, d2d1b, d2d1n), h8King)),
         of("7k/8/8/8/8/8/3p4/3K4 b - - 0 1", h8King),
         of("8/8/8/8/8/8/3p4/3k3K b - - 0 1", m(d1e2, d1e1, d1c1, d1c2)),
         of("2bbb2k/3P4/8/8/8/8/8/7K w - - 0 1", u(m(d7c8q, d7c8r, d7c8b, d7c8n, d7e8q, d7e8r, d7e8b, d7e8n), h1King)),
         of("1b5k/P7/8/8/8/8/8/7K w - - 0 1", u(m(a7a8q, a7a8r, a7a8b, a7a8n, a7b8q, a7b8r, a7b8b, a7b8n), h1King)),
         of("6b1/7P/8/8/8/8/8/k6K w - - 0 1 ", u(m(h7g8q, h7g8r, h7g8b, h7g8n, h7h8q, h7h8r, h7h8b, h7h8n), h1King))
      );
   }

   @ParameterizedTest
   @MethodSource("blackEnPassant")
   void blackEnPassant(String fen, Set<String> expectedMoves) {
      test(fen, expectedMoves);
   }

   @ParameterizedTest
   @MethodSource("blackPawnPromotion")
   void blackPawnPromotion(String fen, Set<String> expectedMoves) {
      test(fen, expectedMoves);
   }
}
