package com.haymel.chess.perft.move.black;

import com.haymel.chess.perft.move.PossibleRookMoves;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Piece.rook;
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

}
