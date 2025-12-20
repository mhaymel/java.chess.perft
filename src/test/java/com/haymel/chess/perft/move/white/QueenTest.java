package com.haymel.chess.perft.move.white;

import com.haymel.chess.perft.move.PossibleQueenMoves;
import com.haymel.chess.perft.util.FieldEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.haymel.chess.perft.Color.white;
import static com.haymel.chess.perft.Piece.queen;
import static com.haymel.chess.perft.move.Moves.*;
import static com.haymel.chess.perft.move.TestUtil.m;
import static com.haymel.chess.perft.move.TestUtil.moveTest;
import static com.haymel.chess.perft.util.FieldEnum.b1;

public final class QueenTest {

   public static Stream<Arguments> move() {
      return PossibleQueenMoves.moves();
   }

   @ParameterizedTest
   @MethodSource("move")
   void move(FieldEnum field, Set<String> expectedMoves) {
      moveTest(field.value, white, expectedMoves, queen);
   }

   @Test
   void queenTest1() {
      Set<String> expectedMoves = m(
         b1b2, b1b3, b1b4, b1b5, b1b6, b1b7, b1b8,
         b1a1, b1c1, b1d1, b1e1, b1f1, b1g1, b1h1,
         b1c2, b1d3, b1e4, b1f5, b1g6, b1h7, b1a2);

      moveTest(b1.value, white, expectedMoves, queen);
   }

}

