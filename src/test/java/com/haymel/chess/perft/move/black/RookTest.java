package com.haymel.chess.perft.move.black;

import com.haymel.chess.perft.move.PossibleRookMoves;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Field.a1;
import static com.haymel.chess.perft.Field.b2;
import static com.haymel.chess.perft.Piece.rook;
import static com.haymel.chess.perft.move.Moves.*;
import static com.haymel.chess.perft.move.Moves.a1h1;
import static com.haymel.chess.perft.move.TestUtil.m;
import static com.haymel.chess.perft.move.TestUtil.moveTest;

public final class RookTest {

   public static Stream<Arguments> move() {
      return PossibleRookMoves.moves();
   }

   @ParameterizedTest
   @MethodSource("move")
   void move(int field, Set<String> expectedMoves) {
      moveTest(field, black, expectedMoves, rook);
   }

   @Test
   void rookTest1() {
      moveTest(a1, black, m(a1a2, a1a3, a1a4, a1a5, a1a6, a1a7, a1a8, a1b1, a1c1, a1d1, a1e1, a1f1, a1g1, a1h1), rook);
   }

  @Test
   void rookTest2() {
      moveTest(b2, black, m(b2b1, b2b3, b2b4, b2b5, b2b6, b2b7, b2b8, b2a2, b2c2, b2d2, b2e2, b2f2, b2g2, b2h2), rook);
   }
}
