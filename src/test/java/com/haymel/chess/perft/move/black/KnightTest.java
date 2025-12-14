package com.haymel.chess.perft.move.black;

import com.haymel.chess.perft.move.PossibleKnightMoves;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Piece.knight;
import static com.haymel.chess.perft.move.Moves.*;
import static com.haymel.chess.perft.move.TestUtil.*;

public final class KnightTest {

   public static Stream<Arguments> knightMoves() {
      return PossibleKnightMoves.knightMoves();
   }

   @ParameterizedTest
   @MethodSource("knightMoves")
   void blackKnightMovest(int field, Set<String> expectedMoves) {
      moveTest(field, black, expectedMoves, knight);
   }

   @Test
   void blackKnightCaptureNonCaptureMoves() {
      test("8/8/3P1P2/2P3P1/2P1n1P1/2P3P1/3P1P2/8 b - - 0 1", m(e4d6, e4f6, e4g5, e4g3, e4f2, e4d2, e4c3, e4c5));
      test("8/3p1p2/2p3p1/2p1n1p1/2p3p1/3p1p2/8/8 b - - 0 1", m(d7d6, d7d5, f7f6, f7f5, g4g3, f3f2, d3d2, c4c3));
   }

}
