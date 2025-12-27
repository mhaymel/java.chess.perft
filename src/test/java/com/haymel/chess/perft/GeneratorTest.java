package com.haymel.chess.perft;

import com.haymel.chess.util.MoveList;
import com.haymel.chess.util.ValidMoves;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.haymel.chess.perft.move.Moves.*;
import static org.assertj.core.api.Assertions.assertThat;

final class GeneratorTest {

   @Test
   void test1() {
      //given
      String fen = "rnbqkbnr/1ppppppp/p7/8/P7/8/1PPPPPPP/RNBQKBNR w KQkq a3 0 1";
      Chess chess = Fen.load(fen);
      Generator generator = new Generator(chess);
      //when
      generator.execute();
      List<Move> moves = MoveList.NewMoveList(chess).moveList();
      assertThat(moves).hasSize(22);
   }

   @Test
   void test2() {
      //given
      String fen = "rnbqkbnr/pppppp1p/8/6p1/8/3P4/PPP1PPPP/RNBQKBNR w KQkq g6 0 2";
      Chess chess = Fen.load(fen);
      Generator generator = new Generator(chess);
      //when
      generator.execute();
      List<Move> moves = MoveList.NewMoveList(chess).moveList();
      //then
      assertThat(moves).hasSize(26);
   }

   @Test
   void test3() {
      //given
      String fen = "7k/8/8/6p1/8/8/8/2B4K w - g6 0 2";
      Chess chess = Fen.load(fen);
      Generator generator = new Generator(chess);
      //when
      generator.execute();
      List<Move> moves = MoveList.NewMoveList(chess).moveList();
      //then
      assertThat(moves).hasSize(9);
   }

   @Test
   void test4() {
      //given
      String fen = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q2/PPPBBPpP/1R2K2R w Kkq - 0 2";
      Chess chess = Fen.load(fen);
      //when
      Set<String> moves = new ValidMoves(chess).value();
      //then
      assertThat(moves).containsExactlyInAnyOrder(
         b1a1, b1c1, b1d1, e1d1, h1g1, h1f1, a2a3, a2a4, b2b3, d2c1,
         d2e3, d2f4, d2g5, d2h6, e2d3, e2c4, e2b5, e2a6, e2d1, e2f1,
         h2h3, h2h4, c3a4, c3d1, c3b5, f3f4, f3f5, f3f6, f3e3, f3d3,
         f3g3, f3h3, f3g4, f3h5, f3g2, d5d6, d5e6, e5c6, e5d3, e5g6,
         e5g4, e5d7, e5f7, e5c4);
   }

}