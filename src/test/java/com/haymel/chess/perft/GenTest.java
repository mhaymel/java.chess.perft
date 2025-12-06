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

   public static final String b5a6 = "b5a6";
   public static final String e1d1 = "e1d1";
   public static final String e1d2 = "e1d2";
   public static final String e1e2 = "e1e2";
   public static final String e1f1 = "e1f1";
   public static final String e1f2 = "e1f2";
   public static final String a5b6 = "a5b6";
   public static final String c5b6 = "c5b6";
   public static final String g5h6 = "g5h6";
   public static final String f5g6 = "f5g6";
   public static final String h5g6 = "h5g6";
   public static final String b4a3 = "b4a3";
   public static final String a4b3 = "a4b3";
   public static final String c4b3 = "c4b3";
   public static final String g4h3 = "g4h3";
   public static final String f4g3 = "f4g3";
   public static final String h4g3 = "h4g3";
   public static final String e8d8 = "e8d8";
   public static final String e8d7 = "e8d7";
   public static final String e8e7 = "e8e7";
   public static final String e8f8 = "e8f8";
   public static final String e8f7 = "e8f7";
   public static final String e4e5 = "e4e5";
   public static final String e4f5 = "e4f5";
   public static final String e4f4 = "e4f4";
   public static final String e4f3 = "e4f3";
   public static final String e4e3 = "e4e3";
   public static final String e4d3 = "e4d3";
   public static final String e4d4 = "e4d4";
   public static final String e4d5 = "e4d5";
   public static final String a1a2 = "a1a2";
   public static final String a1b2 = "a1b2";
   public static final String a1b1 = "a1b1";
   public static final String h1h2 = "h1h2";
   public static final String h1g1 = "h1g1";
   public static final String h1g2 = "h1g2";
   public static final String h8h7 = "h8h7";
   public static final String h8g7 = "h8g7";
   public static final String h8g8 = "h8g8";
   public static final String a8b8 = "a8b8";
   public static final String a8b7 = "a8b7";
   public static final String a8a7 = "a8a7";
   public static final String e5e6 = "e5e6";
   public static final String e5f6 = "e5f6";
   public static final String e5f5 = "e5f5";
   public static final String e5f4 = "e5f4";
   public static final String e5e4 = "e5e4";
   public static final String e5d4 = "e5d4";
   public static final String e5d5 = "e5d5";
   public static final String e5d6 = "e5d6";
   public static final String e1g1 = "e1g1";
   public static final String e1c1 = "e1c1";
   public static final String e8g8 = "e8g8";
   public static final String e8c8 = "e8c8";

   static Stream<Arguments> enPassantPositions() {
      return Stream.of(
         // White en passant
         of("4k3/8/8/pP6/8/8/8/4K3 w - a6 1 1", Set.of(b5a6,      /*king*/ e1d1, e1d2, e1e2, e1f1, e1f2)),
         of("4k3/8/8/pP6/8/8/8/4K3 w - b6 1 1", Set.of(a5b6, c5b6,/*king*/ e1d1, e1d2, e1e2, e1f1, e1f2)),
         of("4k3/8/8/pP6/8/8/8/4K3 w - h6 1 1", Set.of(g5h6,      /*king*/ e1d1, e1d2, e1e2, e1f1, e1f2)),
         of("4k3/8/8/pP6/8/8/8/4K3 w - g6 1 1", Set.of(f5g6, h5g6,/*king*/ e1d1, e1d2, e1e2, e1f1, e1f2)),
         // Black en passant
         of("4k3/8/8/8/Pp6/8/8/4K3 b - a3 1 1", Set.of(b4a3,      /*king*/ e8d8, e8d7, e8e7, e8f8, e8f7)),
         of("4k3/8/8/8/Pp6/8/8/4K3 b - b3 1 1", Set.of(a4b3, c4b3,/*king*/ e8d8, e8d7, e8e7, e8f8, e8f7)),
         of("4k3/8/8/8/Pp6/8/8/4K3 b - h3 1 1", Set.of(g4h3,      /*king*/ e8d8, e8d7, e8e7, e8f8, e8f7)),
         of("4k3/8/8/8/Pp6/8/8/4K3 b - g3 1 1", Set.of(f4g3, h4g3,/*king*/ e8d8, e8d7, e8e7, e8f8, e8f7))
      );
   }

   static Stream<Arguments> whiteKingsMoves() {
      return Stream.of(
         of("k7/8/8/8/4K3/8/8/8 w - - 1 1", Set.of(e4e5, e4f5, e4f4, e4f3, e4e3, e4d3, e4d4, e4d5)),
         of("k7/8/8/8/8/8/8/K7 w - - 1 1", Set.of(a1a2, a1b2, a1b1)),
         of("k7/8/8/8/8/8/8/7K w - - 1 1", Set.of(h1h2, h1g1, h1g2)),
         of("k6K/8/8/8/8/8/8/8 w - - 1 1", Set.of(h8h7, h8g7, h8g8)),
         of("K7/8/8/8/8/8/8/7k w - - 1 1", Set.of(a8b8, a8b7, a8a7)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", Set.of()),
         of("4k3/8/8/3ppp2/3pKp2/3ppp2/8/8 w - - 0 1", Set.of(e4e5, e4f5, e4f4, e4f3, e4e3, e4d3, e4d4, e4d5))
      );
   }

   static Stream<Arguments> blackKingsMoves() {
      return Stream.of(
         of("8/8/8/4k3/8/8/8/7K b - - 0 1", Set.of(e5e6, e5f6, e5f5, e5f4, e5e4, e5d4, e5d5, e5d6)),
         of("k7/8/8/8/8/8/8/K7 b - - 0 1", Set.of(a8b8, a8b7, a8a7)),
         of("7k/8/8/8/8/8/8/K7 b - - 0 1", Set.of(h8h7, h8g7, h8g8)),
         of("8/8/8/8/8/8/8/K6k b - - 0 1 ", Set.of(h1g1, h1g2, h1h2)),
         of("7K/8/8/8/8/8/8/k7 b - - 0 1", Set.of(a1a2, a1b2, a1b1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", Set.of()),
         of("7K/8/3PPP2/3PkP2/3PPP2/8/8/8 b - - 0 1", Set.of(e5e6, e5f6, e5f5, e5f4, e5e4, e5d4, e5d5, e5d6))
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

   static Stream<Arguments> whiteKingCastling() {
      return Stream.of(
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", Set.of()),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w KQkq - 0 1", Set.of(e1g1, e1c1,/*other moves*/ e1d1, e1f1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w Qkq - 0 1", Set.of(e1c1,       /*other moves*/ e1d1, e1f1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w Kkq - 0 1", Set.of(e1g1,       /*other moves*/ e1d1, e1f1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w kq - 0 1", Set.of(             /*other moves*/ e1d1, e1f1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w - - 0 1", Set.of(              /*other moves*/ e1d1, e1f1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3KB1R w KQkq - 0 1", Set.of(e1c1,     /*other moves*/ e1d1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K1NR w KQkq - 0 1", Set.of(e1c1,     /*other moves*/ e1d1, e1f1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R2QK2R w KQkq - 0 1", Set.of(e1g1,     /*other moves*/ e1f1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R1B1K2R w KQkq - 0 1", Set.of(e1g1,    /*other moves*/ e1d1, e1f1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RN2K2R w KQkq - 0 1", Set.of(e1g1,     /*other moves*/ e1d1, e1f1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R2qKb1R w KQkq - 0 1", Set.of(         /*other moves*/ e1d1, e1f1))
      );
   }

   @ParameterizedTest
   @MethodSource("whiteKingCastling")
   void whiteKingCastling(String fen, Set<String> expectedMoves) {
      test(fen, expectedMoves);
   }

   static Stream<Arguments> blackKingCastling() {
      return Stream.of(
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", Set.of()),
         of("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", Set.of(e8g8, e8c8,/*other moves*/ e8d8, e8f8)),

         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w Qkq - 0 1", Set.of(e1c1,       /*other moves*/ e1d1, e1f1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w Kkq - 0 1", Set.of(e1g1,       /*other moves*/ e1d1, e1f1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w kq - 0 1", Set.of(             /*other moves*/ e1d1, e1f1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w - - 0 1", Set.of(              /*other moves*/ e1d1, e1f1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3KB1R w KQkq - 0 1", Set.of(e1c1,     /*other moves*/ e1d1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K1NR w KQkq - 0 1", Set.of(e1c1,     /*other moves*/ e1d1, e1f1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R2QK2R w KQkq - 0 1", Set.of(e1g1,     /*other moves*/ e1f1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R1B1K2R w KQkq - 0 1", Set.of(e1g1,    /*other moves*/ e1d1, e1f1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RN2K2R w KQkq - 0 1", Set.of(e1g1,     /*other moves*/ e1d1, e1f1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R2qKb1R w KQkq - 0 1", Set.of(         /*other moves*/ e1d1, e1f1))
      );
   }

   @ParameterizedTest
   @MethodSource("blackKingCastling")
   void blackKingCastling(String fen, Set<String> expectedMoves) {
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
