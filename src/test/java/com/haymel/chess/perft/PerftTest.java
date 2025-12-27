package com.haymel.chess.perft;

import com.haymel.chess.util.ValidMoves;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

final class PerftTest {

   @Test
   void test1() {
      Perft perft = new Perft("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
      long count = perft.execute(1);
      assertThat(count).isEqualTo(20);
   }

   @Test
   void test2() {
      Perft perft = new Perft("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
      long count = perft.execute(2);
      assertThat(count).isEqualTo(400);
   }

   @Test
   void test3() {
      Perft perft = new Perft("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
      long count = perft.execute(3);
      assertThat(count).isEqualTo(8902);
   }

   @Test
   void test4() {
      Perft perft = new Perft("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
      long count = perft.execute(4);
      assertThat(count).isEqualTo(197281);
   }

   @Test
   void test5() {
      Perft perft = new Perft("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
      long count = perft.execute(5);
      assertThat(count).isEqualTo(4_865_609);
   }

   @Test
   void test6() {
      Perft perft = new Perft("rnbqkbnr/pppppppp/8/8/P7/8/1PPPPPPP/RNBQKBNR b KQkq - 0 1");
      long count = perft.execute(2);
      assertThat(count).isEqualTo(420);
   }

   @Test
   void test7() {
      Perft perft = new Perft("rnbqkbnr/pppppppp/8/8/8/3P4/PPP1PPPP/RNBQKBNR b KQkq - 0 1");
      long count = perft.execute(2);
      assertThat(count).isEqualTo(539L);
   }

   @Test
   void test8() {
      Perft perft = new Perft("rnbqkbnr/pppppppp/8/8/3P4/8/PPP1PPPP/RNBQKBNR b KQkq - 0 1");
      long count = perft.execute(2);
      assertThat(count).isEqualTo(560L);
   }

   @Test
   void test9() {
      Perft perft = new Perft("rnbqkbnr/pppppppp/8/8/8/4P3/PPPP1PPP/RNBQKBNR b KQkq - 0 1");
      long count = perft.execute(2);
      assertThat(count).isEqualTo(599L);
   }

   @Test
   void test10() {
      Perft perft = new Perft("rnbqkbnr/pppppppp/8/8/8/4P3/PPPP1PPP/RNBQKBNR b KQkq - 0 1");

      long count = perft.execute(2);
      assertThat(count).isEqualTo(599L);
   }

   @Test
   void test11() {
      Perft perft = new Perft("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq - 0 1");
      long count = perft.execute(2);
      assertThat(count).isEqualTo(600L);
   }

   @Test
   void testKiwipete() {
      String fen = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1";
      test(1, 48, fen);
      test(2, 2039, fen);
      test(3, 97862, fen);
   }

   @Test
   void test12() {
      test(2, 1969L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R b Kkq - 1 1");
   }

   static Stream<Arguments> perftPositions1() {
      return Stream.of(
         //kiwipete    r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1
         Arguments.of(2, 1969L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R b Kkq - 1 1"),
         Arguments.of(2, 1968L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/2R1K2R b Kkq - 1 1"),
         Arguments.of(2, 1885L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/3RK2R b Kkq - 1 1"),
         Arguments.of(2, 1894L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R2K3R b kq - 1 1"),
         Arguments.of(2, 1855L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R4K1R b kq - 1 1"),
         Arguments.of(2, 2059L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R4RK1 b kq - 1 1"),
         Arguments.of(2, 1887L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/2KR3R b kq - 1 1"),
         Arguments.of(2, 2013L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K1R1 b Qkq - 1 1"),
         Arguments.of(2, 1929L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3KR2 b Qkq - 1 1"),
         Arguments.of(2, 2186L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/P1N2Q1p/1PPBBPPP/R3K2R b KQkq - 0 1"),
         Arguments.of(2, 2149L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/Pp2P3/2N2Q1p/1PPBBPPP/R3K2R b KQkq a3 0 1"),
         Arguments.of(2, 1964L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/1PN2Q1p/P1PBBPPP/R3K2R b KQkq - 0 1"),
         Arguments.of(2, 1963L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPP1BPPP/R1B1K2R b KQkq - 1 1"),
         Arguments.of(2, 2136L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N1BQ1p/PPP1BPPP/R3K2R b KQkq - 1 1"),
         Arguments.of(2, 2000L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2PB2/2N2Q1p/PPP1BPPP/R3K2R b KQkq - 1 1"),
         Arguments.of(2, 2134L, "r3k2r/p1ppqpb1/bn2pnp1/3PN1B1/1p2P3/2N2Q1p/PPP1BPPP/R3K2R b KQkq - 1 1"),
         Arguments.of(2, 2019L, "r3k2r/p1ppqpb1/bn2pnpB/3PN3/1p2P3/2N2Q1p/PPP1BPPP/R3K2R b KQkq - 1 1"),
         Arguments.of(2, 2050L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2NB1Q1p/PPPB1PPP/R3K2R b KQkq - 1 1"),
         Arguments.of(2, 2082L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1pB1P3/2N2Q1p/PPPB1PPP/R3K2R b KQkq - 1 1"),
         Arguments.of(2, 2057L, "r3k2r/p1ppqpb1/bn2pnp1/1B1PN3/1p2P3/2N2Q1p/PPPB1PPP/R3K2R b KQkq - 1 1"),
         Arguments.of(2, 1907L, "r3k2r/p1ppqpb1/Bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPB1PPP/R3K2R b KQkq - 0 1"),
         Arguments.of(2, 1733L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPB1PPP/R2BK2R b KQkq - 1 1"),
         Arguments.of(2, 2060L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPB1PPP/R3KB1R b KQkq - 1 1"),
         Arguments.of(2, 1882L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2QPp/PPPBBP1P/R3K2R b KQkq - 0 1"),
         Arguments.of(2, 1843L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P1P1/2N2Q1p/PPPBBP1P/R3K2R b KQkq g3 0 1"),
         Arguments.of(2, 1970L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1P/PPPBBP1P/R3K2R b KQkq - 0 1"),
         Arguments.of(2, 2203L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/Np2P3/5Q1p/PPPBBPPP/R3K2R b KQkq - 1 1"),
         Arguments.of(2, 2038L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/5Q1p/PPPBBPPP/RN2K2R b KQkq - 1 1"),
         Arguments.of(2, 2040L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/5Q1p/PPPBBPPP/R2NK2R b KQkq - 1 1"),
         Arguments.of(2, 2138L, "r3k2r/p1ppqpb1/bn2pnp1/1N1PN3/1p2P3/5Q1p/PPPBBPPP/R3K2R b KQkq - 1 1"),
         Arguments.of(2, 2132L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2PQ2/2N4p/PPPBBPPP/R3K2R b KQkq - 1 1"),
         Arguments.of(2, 2396L, "r3k2r/p1ppqpb1/bn2pnp1/3PNQ2/1p2P3/2N4p/PPPBBPPP/R3K2R b KQkq - 1 1"),
         Arguments.of(2, 2111L, "r3k2r/p1ppqpb1/bn2pQp1/3PN3/1p2P3/2N4p/PPPBBPPP/R3K2R b KQkq - 0 1"),
         Arguments.of(2, 2174L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N1Q2p/PPPBBPPP/R3K2R b KQkq - 1 1"),
         Arguments.of(2, 2005L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2NQ3p/PPPBBPPP/R3K2R b KQkq - 1 1"),
         Arguments.of(2, 2214L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N3Qp/PPPBBPPP/R3K2R b KQkq - 1 1"),
         Arguments.of(2, 2360L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N4Q/PPPBBPPP/R3K2R b KQkq - 0 1"),
         Arguments.of(2, 2169L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P1Q1/2N4p/PPPBBPPP/R3K2R b KQkq - 1 1"),
         Arguments.of(2, 2267L, "r3k2r/p1ppqpb1/bn2pnp1/3PN2Q/1p2P3/2N4p/PPPBBPPP/R3K2R b KQkq - 1 1"),
         Arguments.of(2, 1991L, "r3k2r/p1ppqpb1/bn1Ppnp1/4N3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R b KQkq - 0 1"),
         Arguments.of(2, 2241L, "r3k2r/p1ppqpb1/bn2Pnp1/4N3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R b KQkq - 0 1"),
         Arguments.of(2, 2027L, "r3k2r/p1ppqpb1/bnN1pnp1/3P4/1p2P3/2N2Q1p/PPPBBPPP/R3K2R b KQkq - 1 1"),
         Arguments.of(2, 1803L, "r3k2r/p1ppqpb1/bn2pnp1/3P4/1p2P3/2NN1Q1p/PPPBBPPP/R3K2R b KQkq - 1 1"),
         Arguments.of(2, 1997L, "r3k2r/p1ppqpb1/bn2pnN1/3P4/1p2P3/2N2Q1p/PPPBBPPP/R3K2R b KQkq - 0 1"),
         Arguments.of(2, 1878L, "r3k2r/p1ppqpb1/bn2pnp1/3P4/1p2P1N1/2N2Q1p/PPPBBPPP/R3K2R b KQkq - 1 1"),
         Arguments.of(2, 2124L, "r3k2r/p1pNqpb1/bn2pnp1/3P4/1p2P3/2N2Q1p/PPPBBPPP/R3K2R b KQkq - 0 1"),
         Arguments.of(2, 2080L, "r3k2r/p1ppqNb1/bn2pnp1/3P4/1p2P3/2N2Q1p/PPPBBPPP/R3K2R b KQkq - 0 1"),
         Arguments.of(2, 1880L, "r3k2r/p1ppqpb1/bn2pnp1/3P4/1pN1P3/2N2Q1p/PPPBBPPP/R3K2R b KQkq - 1 1")
      );
   }

   @ParameterizedTest(name = "depth={0}, expected={1}, fen=\"{2}\"")
   @MethodSource("perftPositions1")
   void perftPositions1(int depth, long expectedCount, String fen) {
      test(depth, expectedCount, fen);
   }

   static Stream<Arguments> perftPositions2() {
      return Stream.of(
         Arguments.of(1, 44L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q2/PPPBBPpP/1R2K2R w Kkq - 0 2"),
         Arguments.of(1, 47L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/4P3/1pN2Q1p/PPPBBPPP/1R2K2R w Kkq - 0 2"),
         Arguments.of(1, 47L, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/4P3/2p2Q1p/PPPBBPPP/1R2K2R w Kkq - 0 2"),
         Arguments.of(1, 46L, "r3k2r/pbppqpb1/1n2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 2 2"),
         Arguments.of(1, 46L, "r1b1k2r/p1ppqpb1/1n2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 2 2"),
         Arguments.of(1, 45L, "r3k2r/p1ppqpb1/1n2pnp1/1b1PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 2 2"),
         Arguments.of(1, 44L, "r3k2r/p1ppqpb1/1n2pnp1/3PN3/1pb1P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 2 2"),
         Arguments.of(1, 44L, "r3k2r/p1ppqpb1/1n2pnp1/3PN3/1p2P3/2Nb1Q1p/PPPBBPPP/1R2K2R w Kkq - 2 2"),
         Arguments.of(1, 40L, "r3k2r/p1ppqpb1/1n2pnp1/3PN3/1p2P3/2N2Q1p/PPPBbPPP/1R2K2R w Kkq - 0 2"),
         Arguments.of(1, 45L, "r3k2r/p1ppqpb1/b3pnp1/3PN3/np2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 2 2"),
         Arguments.of(1, 46L, "r3k2r/p1ppqpb1/b3pnp1/3nN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 0 2"),
         Arguments.of(1, 44L, "r3k2r/p1ppqpb1/b3pnp1/3PN3/1pn1P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 2 2"),
         Arguments.of(1, 46L, "r1n1k2r/p1ppqpb1/b3pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 2 2"),
         Arguments.of(1, 46L, "r3k2r/p1ppqpb1/bn3np1/3pN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 0 2"),
         Arguments.of(1, 49L, "r3k2r/p1ppqpb1/bn2p1p1/3PN3/1p2n3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 0 2"),
         Arguments.of(1, 47L, "r3k2r/p1ppqpbn/bn2p1p1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 2 2"),
         Arguments.of(1, 47L, "r3k2r/p1ppqpb1/bn2p1p1/3PN2n/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 2 2"),
         Arguments.of(1, 45L, "r3k2r/p1ppqpb1/bn2p1p1/3PN3/1p2P1n1/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 2 2"),
         Arguments.of(1, 47L, "r3k1nr/p1ppqpb1/bn2p1p1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 2 2"),
         Arguments.of(1, 47L, "r3k2r/p1ppqpb1/bn2p1p1/3nN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 0 2"),
         Arguments.of(1, 45L, "r3k2r/p1ppqpb1/bn2pn2/3PN1p1/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 0 2"),
         Arguments.of(1, 47L, "r3k2r/p2pqpb1/bnp1pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 0 2"),
         Arguments.of(1, 47L, "r3k2r/p2pqpb1/bn2pnp1/2pPN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq c6 0 2"),
         Arguments.of(1, 45L, "r3k2r/p1p1qpb1/bn1ppnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 0 2"),
         Arguments.of(1, 46L, "r2qk2r/p1pp1pb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 2 2"),
         Arguments.of(1, 45L, "r3k2r/p1pp1pb1/bn1qpnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 2 2"),
         Arguments.of(1, 46L, "r3k2r/p1pp1pb1/bn2pnp1/2qPN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 2 2"),
         Arguments.of(1, 46L, "r3kq1r/p1pp1pb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 2 2"),
         Arguments.of(1, 46L, "r3kb1r/p1ppqp2/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 2 2"),
         Arguments.of(1, 46L, "r3k2r/p1ppqp2/bn2pnpb/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kkq - 2 2"),
         Arguments.of(1, 46L, "1r2k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kk - 2 2"),
         Arguments.of(1, 46L, "2r1k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kk - 2 2"),
         Arguments.of(1, 46L, "3rk2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kk - 2 2"),
         Arguments.of(1, 46L, "r2k3r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w K - 2 2"),
         Arguments.of(1, 46L, "r4k1r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w K - 2 2"),
         Arguments.of(1, 46L, "r4rk1/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w K - 2 2"),
         Arguments.of(1, 46L, "2kr3r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w K - 2 2"),
         Arguments.of(1, 46L, "r3k3/p1ppqpbr/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kq - 2 2"),
         Arguments.of(1, 46L, "r3k3/p1ppqpb1/bn2pnpr/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kq - 2 2"),
         Arguments.of(1, 46L, "r3k3/p1ppqpb1/bn2pnp1/3PN2r/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kq - 2 2"),
         Arguments.of(1, 46L, "r3k3/p1ppqpb1/bn2pnp1/3PN3/1p2P2r/2N2Q1p/PPPBBPPP/1R2K2R w Kq - 2 2"),
         Arguments.of(1, 46L, "r3k1r1/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kq - 2 2"),
         Arguments.of(1, 46L, "r3kr2/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/1R2K2R w Kq - 2 2")
      );
   }

   @ParameterizedTest(name = "depth={0}, expected={1}, fen=\"{2}\"")
   @MethodSource("perftPositions2")
   void perftPositions2(int depth, long expectedCount, String fen) {
      test(depth, expectedCount, fen);
   }

   @Test
   void perftPositions2_1() {
      String fen ="r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q2/PPPBBPpP/1R2K2R w Kkq - 0 2";
      Chess chess = Fen.load(fen);
      Set<String> moves = new ValidMoves(chess).value();
      System.out.println(moves.size() + ": " + moves);

      test(1, 44L, fen);
   }

   @Test
   void perftPositions2_2() {
      test(1, 40L, "r3k2r/p1ppqpb1/1n2pnp1/3PN3/1p2P3/2N2Q1p/PPPBbPPP/1R2K2R w Kkq - 0 2");
   }


   void test(int depth, long expectedCount, String fen) {
      Perft perft = new Perft(fen);
      long count = perft.execute(depth);
      assertThat(count).isEqualTo(expectedCount);
   }
}