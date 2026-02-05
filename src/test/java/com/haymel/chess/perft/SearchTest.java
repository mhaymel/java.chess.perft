package com.haymel.chess.perft;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.haymel.chess.Fens.kiwipete;
import static com.haymel.chess.util.MakeFenFromMoves.NewMakeFenFromMoves;
import static org.assertj.core.api.Assertions.assertThat;

final class SearchTest {

   @Test
   void testSearchDepth1() {
      test(1);
   }

   @Test
   void testSearchDepth2() {
      test(2);
   }

   @Test
   void testSearchDepth3() {
      test(3);
   }

   @Test
   void testSearchDepth4() {
      test(4);
   }

   @Test
   void testSearchDepth5() {
      test(5);
   }

   @Test
   void testSearch1() {
      //given
      Chess chess = Fen.load("3k1b1r/Q3pppp/3p4/8/8/1P4P1/1P2KP1P/2R5 b - - 2 24");
      Search search = new Search(chess);
      //when
      int score = search.search(5);
      Move bestMove = search.bestMove();
      //then
      System.out.println("nodes: " + search.nodes);
      System.out.println("score: " + score);
      assertThat(bestMove).isNotNull();
   }

   @Test
   void testSearch2() {
      //given
      Chess chess = Fen.load("7R/3k1ppp/p7/8/2b5/4PPP1/P2P3q/2B2K2 w - - 0 30");
      Search search = new Search(chess);
      //when
      int score = search.search(5);
      Move bestMove = search.bestMove();
      //then
      System.out.println("nodes: " + search.nodes);
      System.out.println("score: " + score);
      assertThat(bestMove).isNotNull();
   }

   @Test
   void testSearch3() {
      //given
      Chess chess = Fen.load("2k5/pp5p/3B1pp1/8/2P1r1K1/2P1p3/5qPP/5B1R w - - 3 29");
      Search search = new Search(chess);
      //when
      int score = search.search(5);
      Move bestMove = search.bestMove();
      //then
      System.out.println("nodes: " + search.nodes);
      System.out.println("score: " + score);
      assertThat(bestMove).isNotNull();
   }

   @Test
   void testCaptureSearch1() {
      //given
      String fen = NewMakeFenFromMoves("e2e4 d7d6 d2d4 e7e6 f1c4 b8c6 d4d5 c6d4").fen();
      System.out.println(fen);
      Chess chess = Fen.load(fen);
      Search search = new Search(chess);
      //when
      search.search(1);
      //then
      assertThat(search.bestMove().uci()).isEqualTo("d1d4");
   }


   void test(int depth) {
      //given
      Chess chess = Fen.load(Fen.initial);
      Search search = new Search(chess);

      //when
      search.search(depth);
      Move bestMove = search.bestMove();

      //then
      assertThat(bestMove).isNotNull();
   }

   private static Stream<Arguments> fens() {
      return Stream.of(
         Arguments.of(Fen.initial, 5),
         Arguments.of(kiwipete, 4)
      );
   }

   @ParameterizedTest
   @MethodSource("fens")
   void testFens(String fen, int dept) {
      for(int i=1; i<=dept; i++)
         test(fen, dept);
   }

   void test(String fen, int depth) {
      //given
      Chess chess = Fen.load(fen);
      Search search = new Search(chess);
      //when
      search.search(depth);
      Move bestMove = search.bestMove();
      //then
      String resultFen = Fen.toFen(chess);
      assertThat(resultFen).isEqualTo(fen);
   }

}