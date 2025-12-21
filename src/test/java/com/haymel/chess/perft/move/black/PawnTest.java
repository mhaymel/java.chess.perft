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
         of(FieldEnum.a6, m(FieldEnum.a5)),
         of(FieldEnum.a5, m(FieldEnum.a4)),
         of(FieldEnum.a4, m(FieldEnum.a3)),
         of(FieldEnum.a3, m(FieldEnum.a2)),

         of(FieldEnum.b6, m(FieldEnum.b5)),
         of(FieldEnum.b5, m(FieldEnum.b4)),
         of(FieldEnum.b4, m(FieldEnum.b3)),
         of(FieldEnum.b3, m(FieldEnum.b2)),

         of(FieldEnum.c6, m(FieldEnum.c5)),
         of(FieldEnum.c5, m(FieldEnum.c4)),
         of(FieldEnum.c4, m(FieldEnum.c3)),
         of(FieldEnum.c3, m(FieldEnum.c2)),

         of(FieldEnum.d6, m(FieldEnum.d5)),
         of(FieldEnum.d5, m(FieldEnum.d4)),
         of(FieldEnum.d4, m(FieldEnum.d3)),
         of(FieldEnum.d3, m(FieldEnum.d2)),

         of(FieldEnum.e6, m(FieldEnum.e5)),
         of(FieldEnum.e5, m(FieldEnum.e4)),
         of(FieldEnum.e4, m(FieldEnum.e3)),
         of(FieldEnum.e3, m(FieldEnum.e2)),

         of(FieldEnum.f6, m(FieldEnum.f5)),
         of(FieldEnum.f5, m(FieldEnum.f4)),
         of(FieldEnum.f4, m(FieldEnum.f3)),
         of(FieldEnum.f3, m(FieldEnum.f2)),

         of(FieldEnum.g6, m(FieldEnum.g5)),
         of(FieldEnum.g5, m(FieldEnum.g4)),
         of(FieldEnum.g4, m(FieldEnum.g3)),
         of(FieldEnum.g3, m(FieldEnum.g2)),

         of(FieldEnum.h6, m(FieldEnum.h5)),
         of(FieldEnum.h5, m(FieldEnum.h4)),
         of(FieldEnum.h4, m(FieldEnum.h3)),
         of(FieldEnum.h3, m(FieldEnum.h2))
      );
   }

   @ParameterizedTest
   @MethodSource("pawnStep")
   void pawnStep(FieldEnum field, Set<FieldEnum> expected) {
      moveTest(field.value, black, moves(field, expected), pawn);
   }

   static Stream<Arguments> promotion() {
      return Stream.of(
         of(FieldEnum.a2, m(a2a1b, a2a1n, a2a1r, a2a1q)),
         of(FieldEnum.b2, m(b2b1b, b2b1n, b2b1r, b2b1q)),
         of(FieldEnum.c2, m(c2c1b, c2c1n, c2c1r, c2c1q)),
         of(FieldEnum.d2, m(d2d1b, d2d1n, d2d1r, d2d1q)),
         of(FieldEnum.e2, m(e2e1b, e2e1n, e2e1r, e2e1q)),
         of(FieldEnum.f2, m(f2f1b, f2f1n, f2f1r, f2f1q)),
         of(FieldEnum.g2, m(g2g1b, g2g1n, g2g1r, g2g1q)),
         of(FieldEnum.h2, m(h2h1b, h2h1n, h2h1r, h2h1q))
      );
   }

   @ParameterizedTest
   @MethodSource("promotion")
   void promotion(FieldEnum field, Set<String> expected) {
      moveTest(field.value, black, expected, pawn);
   }

   static Stream<Arguments> pawnDoubleStep() {
      return Stream.of(
         of(FieldEnum.a7, m(a7a6, a7a5)),
         of(FieldEnum.b7, m(b7b6, b7b5)),
         of(FieldEnum.c7, m(c7c6, c7c5)),
         of(FieldEnum.d7, m(d7d6, d7d5)),
         of(FieldEnum.e7, m(e7e6, e7e5)),
         of(FieldEnum.f7, m(f7f6, f7f5)),
         of(FieldEnum.g7, m(g7g6, g7g5)),
         of(FieldEnum.h7, m(h7h6, h7h5))
      );
   }

   @ParameterizedTest
   @MethodSource("pawnDoubleStep")
   void pawnDoubleStep(FieldEnum field, Set<String> expected) {
      moveTest(field.value, black, expected, pawn);
   }

}
