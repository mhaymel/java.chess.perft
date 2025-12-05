package com.haymel.chess.perft;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.haymel.chess.perft.Gen.NewGen;
import static com.haymel.chess.perft.HalfFullMove.NewHalfFullMove;
import static com.haymel.chess.perft.help.MoveList.NewMoveList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

final class GenTest {

   static Stream<Arguments> enPassantPositions() {
      return Stream.of(
         // White en passant
         of("4k3/8/8/pP6/8/8/8/4K3 w - a6 1 1", Set.of("b5a6",         /*king*/ "e1d1", "e1d2", "e1e2", "e1f1", "e1f2")),
         of("4k3/8/8/pP6/8/8/8/4K3 w - b6 1 1", Set.of("a5b6", "c5b6", /*king*/ "e1d1", "e1d2", "e1e2", "e1f1", "e1f2")),
         of("4k3/8/8/pP6/8/8/8/4K3 w - h6 1 1", Set.of("g5h6",         /*king*/ "e1d1", "e1d2", "e1e2", "e1f1", "e1f2")),
         of("4k3/8/8/pP6/8/8/8/4K3 w - g6 1 1", Set.of("f5g6", "h5g6", /*king*/ "e1d1", "e1d2", "e1e2", "e1f1", "e1f2")),
         // Black en passant
         of("4k3/8/8/8/Pp6/8/8/4K3 b - a3 1 1", Set.of("b4a3",         /*king*/ "e8d8", "e8d7", "e8e7", "e8f8", "e8f7")),
         of("4k3/8/8/8/Pp6/8/8/4K3 b - b3 1 1", Set.of("a4b3", "c4b3", /*king*/ "e8d8", "e8d7", "e8e7", "e8f8", "e8f7")),
         of("4k3/8/8/8/Pp6/8/8/4K3 b - h3 1 1", Set.of("g4h3",         /*king*/ "e8d8", "e8d7", "e8e7", "e8f8", "e8f7")),
         of("4k3/8/8/8/Pp6/8/8/4K3 b - g3 1 1", Set.of("f4g3", "h4g3", /*king*/ "e8d8", "e8d7", "e8e7", "e8f8", "e8f7"))
         );
   }

   static Stream<Arguments> whiteKingsMoves() {
      return Stream.of(
         // White king moves
         of("k7/8/8/8/4K3/8/8/8 w - - 1 1",  Set.of("e4e5", "e4f5", "e4f4", "e4f3", "e4e3", "e4d3", "e4d4", "e4d5")),
         of("k7/8/8/8/8/8/8/K7 w - - 1 1",   Set.of("a1a2", "a1b2", "a1b1")),
         of("k7/8/8/8/8/8/8/7K w - - 1 1",   Set.of("h1h2", "h1g1", "h1g2")),
         of("k6K/8/8/8/8/8/8/8 w - - 1 1",   Set.of("h8h7", "h8g7", "h8g8")),
         of("K7/8/8/8/8/8/8/7k w - - 1 1",   Set.of("a8b8", "a8b7", "a8a7")),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", Set.of()),
         of("4k3/8/8/3ppp2/3pKp2/3ppp2/8/8 w - - 0 1", Set.of("e4e5", "e4f5", "e4f4", "e4f3", "e4e3", "e4d3", "e4d4", "e4d5"))
      );
   }

   static Stream<Arguments> blackKingsMoves() {
      return Stream.of(
         // White king moves
         of("8/8/8/4k3/8/8/8/7K b - - 0 1",  Set.of("e5e6", "e5f6", "e5f5", "e5f4", "e5e4", "e5d4", "e5d5", "e5d6")),
         of("k7/8/8/8/8/8/8/K7 b - - 0 1",   Set.of("a8b8", "a8b7", "a8a7")),
         of("7k/8/8/8/8/8/8/K7 b - - 0 1",   Set.of("h8h7", "h8g7", "h8g8")),
         of("8/8/8/8/8/8/8/K6k b - - 0 1 ",   Set.of("h1g1", "h1g2", "h1h2")),
         of("7K/8/8/8/8/8/8/k7 b - - 0 1",   Set.of("a1a2", "a1b2", "a1b1")),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", Set.of()),
         of("7K/8/3PPP2/3PkP2/3PPP2/8/8/8 b - - 0 1", Set.of("e5e6", "e5f6", "e5f5", "e5f4", "e5e4", "e5d4", "e5d5", "e5d6"))
      );
   }

   @ParameterizedTest
   @MethodSource("enPassantPositions")
   void enPassantPositions(String fen, Set<String> expectedMoves) {
      test(fen, expectedMoves);
   }

   @ParameterizedTest
   @MethodSource("whiteKingsMoves")
   void whiteKingsMoves(String fen, Set<String> expectedMoves) {
      test(fen, expectedMoves);
   }

   @ParameterizedTest
   @MethodSource("blackKingsMoves")
   void blackKingsMoves(String fen, Set<String> expectedMoves) {
      test(fen, expectedMoves);
   }

   void test(String fen, Set<String> expectedMoves) {
      //given
      Chess chess = new Chess();
      Fen.load(fen, chess, NewHalfFullMove());

      //when
      NewGen(chess).execute();

      //then
      Set<String> moves = NewMoveList(chess).moveStrings();
      assertThat(moves).isEqualTo(expectedMoves);
   }
}
