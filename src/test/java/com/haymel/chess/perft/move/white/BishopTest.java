package com.haymel.chess.perft.move.white;

import com.haymel.chess.perft.move.PossibleBishopMoves;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.haymel.chess.perft.Color.white;
import static com.haymel.chess.perft.Piece.bishop;
import static com.haymel.chess.perft.move.Moves.*;
import static com.haymel.chess.perft.move.TestUtil.*;
import static com.haymel.chess.perft.move.white.KingTest.whitePawnInitialMoves;

public final class BishopTest {

   public static Stream<Arguments> move() {
      return PossibleBishopMoves.moves();
   }

   @ParameterizedTest
   @MethodSource("move")
   void move(int field, Set<String> expectedMoves) {
      moveTest(field, white, expectedMoves, bishop);
   }

   @Test
   void whiteRook1() {
      test("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K1NR w KQkq - 0 1",
         u(m(e1c1, /*other*/ e1d1, e1f1, g1f3, g1h3, a1b1, a1c1, a1d1), whitePawnInitialMoves));
   }

   @Test
   void whiteRook2() {
      test("rnbqkbnr/pppppppp/8/8/8/8/P7/R3K3 w Qkq - 0 1",
         m(a2a3, a2a4, e1c1, e1d1, e1d2, e1e2, e1f2, e1f1, a1b1, a1c1, a1d1));
   }

   @Test
   void whiteRook3() {
      test("8/8/8/8/8/8/8/R7 w - - 0 1",
         m(a1b1, a1c1, a1d1, a1e1, a1f1, a1g1, a1h1, a1a2, a1a3, a1a4, a1a5, a1a6, a1a7, a1a8));
   }

}
