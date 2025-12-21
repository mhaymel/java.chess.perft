package com.haymel.chess.perft.move.white;

import com.haymel.chess.perft.util.FieldEnum;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.haymel.chess.perft.Color.white;
import static com.haymel.chess.perft.Piece.pawn;
import static com.haymel.chess.perft.move.Moves.*;
import static com.haymel.chess.perft.move.TestUtil.*;
import static com.haymel.chess.perft.util.FieldEnum.*;
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
         of("8/8/8/8/8/4k3/4P3/8 w - - 0 1", no),
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

   static Stream<Arguments> pawnStep() {
      return Stream.of(
         of(a3, m(a4)),
         of(a4, m(a5)),
         of(a5, m(a6)),
         of(a6, m(a7)),

         of(b3, m(b4)),
         of(b4, m(b5)),
         of(b5, m(b6)),
         of(b6, m(b7)),

         of(c3, m(c4)),
         of(c4, m(c5)),
         of(c5, m(c6)),
         of(c6, m(c7)),

         of(d3, m(d4)),
         of(d4, m(d5)),
         of(d5, m(d6)),
         of(d6, m(d7)),

         of(e3, m(e4)),
         of(e4, m(e5)),
         of(e5, m(e6)),
         of(e6, m(e7)),

         of(f3, m(f4)),
         of(f4, m(f5)),
         of(f5, m(f6)),
         of(f6, m(f7)),

         of(g3, m(g4)),
         of(g4, m(g5)),
         of(g5, m(g6)),
         of(g6, m(g7)),

         of(h3, m(h4)),
         of(h4, m(h5)),
         of(h5, m(h6)),
         of(h6, m(h7))
      );
   }

   @ParameterizedTest
   @MethodSource("pawnStep")
   void pawnStep(FieldEnum field, Set<FieldEnum> expected) {
      moveTest(field.value, white, moves(field, expected), pawn);
   }

   static Stream<Arguments> promotion() {
      return Stream.of(
         of(a7, m(a7a8b, a7a8n, a7a8r, a7a8q)),
         of(b7, m(b7b8b, b7b8n, b7b8r, b7b8q)),
         of(c7, m(c7c8b, c7c8n, c7c8r, c7c8q)),
         of(d7, m(d7d8b, d7d8n, d7d8r, d7d8q)),
         of(e7, m(e7e8b, e7e8n, e7e8r, e7e8q)),
         of(f7, m(f7f8b, f7f8n, f7f8r, f7f8q)),
         of(g7, m(g7g8b, g7g8n, g7g8r, g7g8q)),
         of(h7, m(h7h8b, h7h8n, h7h8r, h7h8q))
      );
   }

   @ParameterizedTest
   @MethodSource("promotion")
   void promotion(FieldEnum field, Set<String> expected) {
      moveTest(field.value, white, expected, pawn);
   }

   static Stream<Arguments> pawnDoubleStep() {
      return Stream.of(
         of(a2, m(a2a3, a2a4)),
         of(b2, m(b2b3, b2b4)),
         of(c2, m(c2c3, c2c4)),
         of(d2, m(d2d3, d2d4)),
         of(e2, m(e2e3, e2e4)),
         of(f2, m(f2f3, f2f4)),
         of(g2, m(g2g3, g2g4)),
         of(h2, m(h2h3, h2h4))
      );
   }

   @ParameterizedTest
   @MethodSource("pawnDoubleStep")
   void pawnDoubleStep(FieldEnum field, Set<String> expected) {
      moveTest(field.value, white, expected, pawn);
   }

}












