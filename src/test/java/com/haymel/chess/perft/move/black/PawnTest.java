package com.haymel.chess.perft.move.black;

import com.haymel.chess.perft.util.FieldEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.collect.Sets.union;
import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Color.white;
import static com.haymel.chess.perft.Piece.pawn;
import static com.haymel.chess.perft.move.Moves.*;
import static com.haymel.chess.perft.move.TestUtil.*;
import static com.haymel.chess.perft.util.FieldEnum.*;
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

   @Test
   void blackPawnPromotion1() {
      test("7k/8/8/8/8/8/3p4/7K b - - 0 1", u(m(d2d1q, d2d1r, d2d1b, d2d1n), h8King));
   }

   static Stream<Arguments> BlackPawn() {
      return Stream.of(
         // Black en passant
         of("8/4p3/8/8/8/8/8/8 b - - 0 1", m(e7e6, e7e5)),
         of("8/4p3/4k3/8/8/8/8/8 b - - 0 1", m(e6f7, e6f6, e6f5, e6e5, e6d5, e6d6, e6d7)),
         of("8/4p3/8/4k3/8/8/8/8 b - - 0 1", m(e7e6, e5e6, e5f6, e5f5, e5f4, e5e4, e5d4, e5d5, e5d6)),
         of("8/4p3/4K3/8/8/8/8/8 b - - 0 1", no),
         of("8/4p3/8/4K3/8/8/8/8 b - - 0 1", m(e7e6)),
         of("8/4p3/3P4/8/8/8/8/8 b - - 0 1", m(e7e6, e7e5, e7d6)),
         of("8/4p3/5P2/8/8/8/8/8 b - - 0 1", m(e7e6, e7e5, e7f6)),
         of("8/4p3/3P1P2/8/8/8/8/8 b - - 0 1", m(e7e6, e7e5, e7f6, e7d6)),
         of("8/4p3/3p1p2/8/8/8/8/8 b - - 0 1", m(e7e6, e7e5, d6d5, f6f5))
      );
   }

   @ParameterizedTest
   @MethodSource("BlackPawn")
   void whitePawn(String fen, Set<String> expectedMoves) {
      test(fen, expectedMoves);
   }

   static Stream<Arguments> pawnStep() {
      return Stream.of(
         of(a6, m(a5)),
         of(a5, m(a4)),
         of(a4, m(a3)),
         of(a3, m(a2)),

         of(b6, m(b5)),
         of(b5, m(b4)),
         of(b4, m(b3)),
         of(b3, m(b2)),

         of(c6, m(c5)),
         of(c5, m(c4)),
         of(c4, m(c3)),
         of(c3, m(c2)),

         of(d6, m(d5)),
         of(d5, m(d4)),
         of(d4, m(d3)),
         of(d3, m(d2)),

         of(e6, m(e5)),
         of(e5, m(e4)),
         of(e4, m(e3)),
         of(e3, m(e2)),

         of(f6, m(f5)),
         of(f5, m(f4)),
         of(f4, m(f3)),
         of(f3, m(f2)),

         of(g6, m(g5)),
         of(g5, m(g4)),
         of(g4, m(g3)),
         of(g3, m(g2)),

         of(h6, m(h5)),
         of(h5, m(h4)),
         of(h4, m(h3)),
         of(h3, m(h2))
      );
   }

   @ParameterizedTest
   @MethodSource("pawnStep")
   void pawnStep(FieldEnum field, Set<FieldEnum> expected) {
      moveTest(field.value, black, moves(field, expected), pawn);
   }

   static Stream<Arguments> promotion() {
      return Stream.of(
         of(a2, m(a2a1b, a2a1n, a2a1r, a2a1q)),
         of(b2, m(b2b1b, b2b1n, b2b1r, b2b1q)),
         of(c2, m(c2c1b, c2c1n, c2c1r, c2c1q)),
         of(d2, m(d2d1b, d2d1n, d2d1r, d2d1q)),
         of(e2, m(e2e1b, e2e1n, e2e1r, e2e1q)),
         of(f2, m(f2f1b, f2f1n, f2f1r, f2f1q)),
         of(g2, m(g2g1b, g2g1n, g2g1r, g2g1q)),
         of(h2, m(h2h1b, h2h1n, h2h1r, h2h1q))
      );
   }

   @ParameterizedTest
   @MethodSource("promotion")
   void promotion(FieldEnum field, Set<String> expected) {
      moveTest(field.value, black, expected, pawn);
   }

   static Stream<Arguments> pawnDoubleStep() {
      return Stream.of(
         of(a7, m(a7a6, a7a5)),
         of(b7, m(b7b6, b7b5)),
         of(c7, m(c7c6, c7c5)),
         of(d7, m(d7d6, d7d5)),
         of(e7, m(e7e6, e7e5)),
         of(f7, m(f7f6, f7f5)),
         of(g7, m(g7g6, g7g5)),
         of(h7, m(h7h6, h7h5))
      );
   }

   @ParameterizedTest
   @MethodSource("pawnDoubleStep")
   void pawnDoubleStep(FieldEnum field, Set<String> expected) {
      moveTest(field.value, black, expected, pawn);
   }

}
