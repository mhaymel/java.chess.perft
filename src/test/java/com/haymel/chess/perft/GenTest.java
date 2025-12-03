package com.haymel.chess.perft;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.haymel.chess.perft.Gen.NewGen;
import static com.haymel.chess.perft.HalfFullMove.NewHalfFullMove;
import static com.haymel.chess.perft.help.MoveList.NewMoveList;
import static org.assertj.core.api.Assertions.assertThat;

final class GenTest {

   @ParameterizedTest
   @MethodSource("enPassantPositions")
   void testEnPassant(String fen, Set<String> expectedMoves) {
      //given
      Chess chess = new Chess();
      Fen.load(fen, chess, NewHalfFullMove());

      //when
      NewGen(chess).execute();

      //then
      Set<String> moves = NewMoveList(chess).moveStrings();
      assertThat(moves).isEqualTo(expectedMoves);
   }

   static Stream<Arguments> enPassantPositions() {
      return Stream.of(
         // White en passant
         Arguments.of("4k3/8/8/pP6/8/8/8/4K3 w - a6 1 1", Set.of("b5a6")),
         Arguments.of("4k3/8/8/pP6/8/8/8/4K3 w - b6 1 1", Set.of("a5b6", "c5b6")),
         Arguments.of("4k3/8/8/pP6/8/8/8/4K3 w - h6 1 1", Set.of("g5h6")),
         Arguments.of("4k3/8/8/pP6/8/8/8/4K3 w - g6 1 1", Set.of("f5g6", "h5g6")),
         // Black en passant
         Arguments.of("4k3/8/8/8/Pp6/8/8/4K3 b - a3 1 1", Set.of("b4a3")),
         Arguments.of("4k3/8/8/8/Pp6/8/8/4K3 b - b3 1 1", Set.of("a4b3", "c4b3")),
         Arguments.of("4k3/8/8/8/Pp6/8/8/4K3 b - h3 1 1", Set.of("g4h3")),
         Arguments.of("4k3/8/8/8/Pp6/8/8/4K3 b - g3 1 1", Set.of("f4g3", "h4g3"))
      );
   }

   @Test
   void sampleTest() {
      //given
      Chess chess = new Chess();
      chess.initBoard();
      Gen gen = new Gen(chess);

      //when
      gen.execute();

      //then
   }
}
