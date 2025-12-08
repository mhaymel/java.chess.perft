package com.haymel.chess.perft;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.collect.Sets.union;
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
   public static final String e2e4 = "e2e4";
   public static final String e7e8 = "e7e8";
   public static final String b5b6 = "b5b6";
   public static final String a5a6 = "a5a6";
   public static final String c5c6 = "c5c6";
   public static final String h5h6 = "h5h6";
   public static final String g5g6 = "g5g6";
   public static final String f5f6 = "f5f6";
   public static final String h2h3 = "h2h3";
   public static final String a2a3 = "a2a3";
   public static final String a2a4 = "a2a4";
   public static final String h2h4 = "h2h4";
   public static final String b2b3 = "b2b3";
   public static final String c2c4 = "c2c4";
   public static final String b2b4 = "b2b4";
   public static final String c2c3 = "c2c3";
   public static final String d2d3 = "d2d3";
   public static final String d2d4 = "d2d4";
   public static final String e2e3 = "e2e3";
   public static final String f2f3 = "f2f3";
   public static final String f2f4 = "f2f4";
   public static final String g2g3 = "g2g3";
   public static final String g2g4 = "g2g4";
   public static final String a7a6 = "a7a6";
   public static final String a7a5 = "a7a5";
   public static final String b7b6 = "b7b6";
   public static final String b7b5 = "b7b5";
   public static final String c7c6 = "c7c6";
   public static final String c7c5 = "c7c5";
   public static final String d7d6 = "d7d6";
   public static final String d7d5 = "d7d5";
   public static final String e7e6 = "e7e6";
   public static final String e7e5 = "e7e5";
   public static final String f7f6 = "f7f6";
   public static final String f7f5 = "f7f5";
   public static final String g7g6 = "g7g6";
   public static final String g7g5 = "g7g5";
   public static final String h7h6 = "h7h6";
   public static final String h7h5 = "h7h5";
   public static final String b4b3 = "b4b3";
   public static final String c4c3 = "c4c3";
   public static final String a4a3 = "a4a3";
   public static final String g4g3 = "g4g3";
   public static final String f4f3 = "f4f3";
   public static final String h4h3 = "h4h3";
   public static final String d7d8q = "d7d8q";
   public static final String d7d8r = "d7d8r";
   public static final String d7d8b = "d7d8b";
   public static final String d7d8n = "d7d8n";
   public static final String d8e8 = "d8e8";
   public static final String d8e7 = "d8e7";
   public static final String d8c7 = "d8c7";
   public static final String d8c8 = "d8c8";
   public static final String d7c8q = "d7c8q";
   public static final String d7c8r = "d7c8r";
   public static final String d7c8b = "d7c8b";
   public static final String d7c8n = "d7c8n";
   public static final String d7e8q = "d7e8q";
   public static final String d7e8r = "d7e8r";
   public static final String d7e8b = "d7e8b";
   public static final String d7e8n = "d7e8n";
   public static final String a7a8q = "a7a8q";
   public static final String a7a8r = "a7a8r";
   public static final String a7a8b = "a7a8b";
   public static final String a7a8n = "a7a8n";
   public static final String a7b8q = "a7b8q";
   public static final String a7b8r = "a7b8r";
   public static final String a7b8b = "a7b8b";
   public static final String a7b8n = "a7b8n";
   public static final String h7g8q = "h7g8q";
   public static final String h7g8r = "h7g8r";
   public static final String h7g8b = "h7g8b";
   public static final String h7g8n = "h7g8n";
   public static final String h7h8q = "h7h8q";
   public static final String h7h8r = "h7h8r";
   public static final String h7h8b = "h7h8b";
   public static final String h7h8n = "h7h8n";

   private static final Set<String> whitePawnInitialMoves = Set.of(a2a3, a2a4, b2b3, b2b4, c2c3, c2c4, d2d3, d2d4, e2e3, e2e4, f2f3, f2f4, g2g3, g2g4, h2h3, h2h4);

   private static final Set<String> blackPawnInitialMoves = Set.of(a7a6, a7a5, b7b6, b7b5, c7c6, c7c5, d7d6, d7d5, e7e6, e7e5, f7f6, f7f5, g7g6, g7g5, h7h6, h7h5);
   private static final Set<String> h1King = Set.of(h1h2, h1g1, h1g2);
   private static final Set<String> a1King = Set.of(a1b1, a1a2, a1b2);
   private static final Set<String> h8King = Set.of(h8h7, h8g7, h8g8);

   static Stream<Arguments> whiteKingMoves() {
      return Stream.of(
         of("k7/8/8/8/4K3/8/8/8 w - - 1 1", Set.of(e4e5, e4f5, e4f4, e4f3, e4e3, e4d3, e4d4, e4d5)),
         of("k7/8/8/8/8/8/8/K7 w - - 1 1", Set.of(a1a2, a1b2, a1b1)),
         of("k7/8/8/8/8/8/8/7K w - - 1 1", Set.of(h1h2, h1g1, h1g2)),
         of("k6K/8/8/8/8/8/8/8 w - - 1 1", Set.of(h8h7, h8g7, h8g8)),
         of("K7/8/8/8/8/8/8/7k w - - 1 1", Set.of(a8b8, a8b7, a8a7)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", whitePawnInitialMoves),
         of("4k3/8/8/3ppp2/3pKp2/3ppp2/8/8 w - - 0 1", Set.of(e4e5, e4f5, e4f4, e4f3, e4e3, e4d3, e4d4, e4d5))
      );
   }

   static Stream<Arguments> whiteEnPassant() {
      return Stream.of(
         of("4k3/8/8/p7/8/8/8/4K3 w - a6 1 1", Set.of(       /*other*/ e1d1, e1d2, e1e2, e1f1, e1f2)),
         of("4k3/8/8/2p5/8/8/8/4K3 w - c6 1 1", Set.of(              /*other*/ e1d1, e1d2, e1e2, e1f1, e1f2)),
         of("4k3/8/8/1ppp4/8/8/8/4K3 w - c6 1 1", Set.of(           /*other*/ e1d1, e1d2, e1e2, e1f1, e1f2)),
         of("4k3/8/8/pP6/8/8/8/4K3 w - a6 1 1", Set.of(b5a6,         /*other*/ b5b6, e1d1, e1d2, e1e2, e1f1, e1f2)),
         of("4k3/8/8/PpP5/8/8/8/4K3 w - b6 1 1", Set.of(a5b6, c5b6,  /*other*/ a5a6, c5c6, e1d1, e1d2, e1e2, e1f1, e1f2)),
         of("4k3/8/8/6Pp/8/8/8/4K3 w - h6 1 1", Set.of(g5h6,         /*other*/ g5g6, e1d1, e1d2, e1e2, e1f1, e1f2)),
         of("4k3/8/8/5PpP/8/8/8/4K3 w - g6 1 1", Set.of(f5g6, h5g6,  /*other*/ f5f6, h5h6, e1d1, e1d2, e1e2, e1f1, e1f2))
      );
   }

   @ParameterizedTest
   @MethodSource("whiteEnPassant")
   void whiteEnPassant(String fen, Set<String> expectedMoves) {
      test(fen, expectedMoves);
   }

   static Stream<Arguments> whitePawnPromotion() {
      return Stream.of(
         // Black en passant
         of("7k/3P4/8/8/8/8/8/7K w - - 0 1", union(Set.of(d7d8q, d7d8r, d7d8b, d7d8n), h1King)),
         of("3k4/3P4/8/8/8/8/8/7K w - - 0 1", h1King),
         of("3K3k/3P4/8/8/8/8/8/8 w - - 0 1", Set.of(/*other*/d8e8, d8e7, d8c7, d8c8)),
         of("2bbb2k/3P4/8/8/8/8/8/7K w - - 0 1", union(Set.of(d7c8q, d7c8r, d7c8b, d7c8n, d7e8q, d7e8r, d7e8b, d7e8n), h1King)),
         of("1b5k/P7/8/8/8/8/8/7K w - - 0 1", union(Set.of(a7a8q, a7a8r, a7a8b, a7a8n, a7b8q, a7b8r, a7b8b, a7b8n), h1King)),
         of("6b1/7P/8/8/8/8/8/k6K w - - 0 1 ", union(Set.of(h7g8q, h7g8r, h7g8b, h7g8n, h7h8q, h7h8r, h7h8b, h7h8n), h1King))
      );
   }

   @ParameterizedTest
   @MethodSource("whitePawnPromotion")
   void whitePawnPromotion(String fen, Set<String> expectedMoves) {
      test(fen, expectedMoves);
   }

   static Stream<Arguments> blackEnPassant() {
      return Stream.of(
         of("7k/8/8/8/P7/8/8/7K b - a3 1 1", h8King),
         of("7k/8/8/8/2P5/8/8/7K b - c3 1 1", h8King),
         of("7k/8/8/8/1PPP4/8/8/7K b - c3 1 1", h8King),
         of("7k/8/8/8/Pp6/8/8/4K3 b - a3 1 1", union(Set.of(b4a3,/*other*/ b4b3), h8King)),
         of("7k/8/8/8/pPp5/8/8/7K b - b3 1 1 ", union(Set.of(a4b3, c4b3,/*other*/a4a3, c4c3), h8King)),
         of("7k/8/8/8/6pP/8/8/7K b - h3 1 1", union(Set.of(g4h3, /*other*/ g4g3), h8King)),
         of("7k/8/8/8/5pPp/8/8/7K b - g3 1 1 ", union(Set.of(f4g3, h4g3, /*other*/f4f3, h4h3), h8King))
      );
   }

   @ParameterizedTest
   @MethodSource("blackEnPassant")
   void blackEnPassant(String fen, Set<String> expectedMoves) {
      test(fen, expectedMoves);
   }

   @ParameterizedTest
   @MethodSource("whiteKingMoves")
   void whiteKingMoves(String fen, Set<String> expectedMoves) {
      test(fen, expectedMoves);
   }

   static Stream<Arguments> blackKingMoves() {
      return Stream.of(
         of("8/8/8/4k3/8/8/8/7K b - - 0 1", Set.of(e5e6, e5f6, e5f5, e5f4, e5e4, e5d4, e5d5, e5d6)),
         of("k7/8/8/8/8/8/8/K7 b - - 0 1", Set.of(a8b8, a8b7, a8a7)),
         of("7k/8/8/8/8/8/8/K7 b - - 0 1", Set.of(h8h7, h8g7, h8g8)),
         of("8/8/8/8/8/8/8/K6k b - - 0 1 ", Set.of(h1g1, h1g2, h1h2)),
         of("7K/8/8/8/8/8/8/k7 b - - 0 1", Set.of(a1a2, a1b2, a1b1)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", blackPawnInitialMoves),
         of("7K/8/3PPP2/3PkP2/3PPP2/8/8/8 b - - 0 1", Set.of(e5e6, e5f6, e5f5, e5f4, e5e4, e5d4, e5d5, e5d6))
      );
   }

   @ParameterizedTest
   @MethodSource("blackKingMoves")
   void blackKingMoves(String fen, Set<String> expectedMoves) {
      test(fen, expectedMoves);
   }

   static Stream<Arguments> whiteCastling() {
      return Stream.of(
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", whitePawnInitialMoves),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w KQkq - 0 1", union(Set.of(e1g1, e1c1, /*other*/ e1d1, e1f1), whitePawnInitialMoves)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w Qkq - 0 1", union(Set.of(e1c1, /*other*/ e1d1, e1f1), whitePawnInitialMoves)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w Kkq - 0 1", union(Set.of(e1g1, /*other*/ e1d1, e1f1), whitePawnInitialMoves)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w kq - 0 1", union(Set.of(/*other*/ e1d1, e1f1), whitePawnInitialMoves)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w - - 0 1", union(Set.of(/*other*/ e1d1, e1f1), whitePawnInitialMoves)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3KB1R w KQkq - 0 1", union(Set.of(e1c1,/*other*/ e1d1), whitePawnInitialMoves)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K1NR w KQkq - 0 1", union(Set.of(e1c1, /*other*/ e1d1, e1f1), whitePawnInitialMoves)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R2QK2R w KQkq - 0 1", union(Set.of(e1g1, /*other*/ e1f1), whitePawnInitialMoves)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R1B1K2R w KQkq - 0 1", union(Set.of(e1g1, /*other*/ e1d1, e1f1), whitePawnInitialMoves)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RN2K2R w KQkq - 0 1", union(Set.of(e1g1, /*other*/ e1d1, e1f1), whitePawnInitialMoves)),
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R2qKb1R w KQkq - 0 1", union(Set.of(/*other*/ e1d1, e1f1), whitePawnInitialMoves)));
   }

   @ParameterizedTest
   @MethodSource("whiteCastling")
   void whiteCastling(String fen, Set<String> expectedMoves) {
      test(fen, expectedMoves);
   }

   static Stream<Arguments> blackCastling() {
      return Stream.of(
         of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", blackPawnInitialMoves),
         of("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", union(Set.of(e8g8, e8c8,/*other*/e8d8, e8f8), blackPawnInitialMoves)),
         of("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQq - 0 1", union(Set.of(e8c8, /*other*/e8d8, e8f8), blackPawnInitialMoves)),
         of("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQk - 0 1", union(Set.of(e8g8, /*other*/e8d8, e8f8), blackPawnInitialMoves)),
         of("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQ - 0 1", union(Set.of(/*other*/e8d8, e8f8), blackPawnInitialMoves)),
         of("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b - - 0 1", union(Set.of(/*other*/e8d8, e8f8), blackPawnInitialMoves)),
         of("r3kb2/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", union(Set.of(e8c8, /*other*/ e8d8), blackPawnInitialMoves)),
         of("r3k1nr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", union(Set.of(e8c8, /*other*/ e8d8, e8f8), blackPawnInitialMoves)),
         of("r2qk2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", union(Set.of(e8g8, /*other*/ e8f8), blackPawnInitialMoves)),
         of("r1b1k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", union(Set.of(e8g8, /*other*/ e8d8, e8f8), blackPawnInitialMoves)),
         of("rn2k2r/pppppppp/8/8/8/8/PPPPPPPP/RNB1KBNR b KQkq - 0 1", union(Set.of(e8g8, /*other*/ e8d8, e8f8), blackPawnInitialMoves)),
         of("r2QkB1r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", union(Set.of(/*other*/ e8d8, e8f8), blackPawnInitialMoves)));
   }

   @ParameterizedTest
   @MethodSource("blackCastling")
   void blackCastling(String fen, Set<String> expectedMoves) {
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
