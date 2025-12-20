package com.haymel.chess.perft.move.black;

import com.haymel.chess.perft.move.PossibleQueenMoves;
import com.haymel.chess.perft.util.FieldEnum;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Piece.queen;
import static com.haymel.chess.perft.move.TestUtil.moveTest;

public final class QueenTest {

   public static Stream<Arguments> move() {
      return PossibleQueenMoves.moves();
   }

   @ParameterizedTest
   @MethodSource("move")
   void move(FieldEnum field, Set<String> expectedMoves) {
      moveTest(field.value, black, expectedMoves, queen);
   }

}
