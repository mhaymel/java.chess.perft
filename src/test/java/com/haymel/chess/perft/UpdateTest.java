package com.haymel.chess.perft;

import com.haymel.chess.util.MoveFromString;
import com.haymel.chess.util.MoveList;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.Move.NewMove;
import static com.haymel.chess.perft.Piece.bishop;
import static com.haymel.chess.perft.move.Moves.*;
import static org.assertj.core.api.Assertions.assertThat;


final class UpdateTest {

   @Test
   void test1() {
      String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
      Chess c = new Chess();
      Fen.load(fen, c);
      Update update = new Update(c);
      update.MakeMove(NewMove(e2, e4));
      update.unMakeMove();
      String fenAfterMakeAndUnMakeMove = Fen.toFen(c);
      assertThat(fenAfterMakeAndUnMakeMove).isEqualTo(fen);
   }

   @Test
   void test4() {
      String fen = "5b2/2pk2P1/3p2N1/pP2p2P/5r2/1b4P1/2K5/b1q5 w - - 17 16";
      Chess c = new Chess();
      Fen.load(fen, c);
      Update update = new Update(c);
      if (update.MakeMove(NewMove(g7, g8, bishop))) {
         update.unMakeMove();
      }
      String fenAfterMakeAndUnMakeMove = Fen.toFen(c);
      assertThat(fenAfterMakeAndUnMakeMove).isEqualTo(fen);
   }

   @Test
   void test2() {
      Chess c = new Chess();
      Fen.load("6QR/8/3p1kN1/1P5P/3N1r2/1b4P1/3r4/2K2b2 b - - 13 10", c);
      Update update = new Update(c);
      update.MakeMove(NewMove(f6, g6));
   }

   @Test
   void test3() {
      //given
      String fen = "6QR/8/3p1kN1/1P5P/3N1r2/1b4P1/3r4/2K2b2 b - - 13 10";
      Chess c = new Chess();
      Fen.load(fen, c);
      Update update = new Update(c);
      //when
      boolean b = update.MakeMove(NewMove(h2, h4));
      assertThat(b).isTrue();
      update.unMakeMove();
      //then
      String fenAfterMakeAndUnMakeMove = Fen.toFen(c);
      assertThat(fenAfterMakeAndUnMakeMove).isEqualTo(fen);
   }

   @Test
   void test5() {
      //given
      String fen = "r3k2r/8/8/8/8/8/8/R3K2R w K - 0 1";
      Chess c = new Chess();
      Fen.load(fen, c);
      Update update = new Update(c);
      //when
      update.MakeMove(NewMove(e1, g1));
      update.unMakeMove();
      //then
      String fenAfterMakeAndUnMakeMove = Fen.toFen(c);
      assertThat(fenAfterMakeAndUnMakeMove).isEqualTo(fen);
   }

/*
   @Test
   void test6() {
      //given
      String fen = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/Pp2P3/2N2Q1p/1PPBBPPP/R3K2R b KQkq a3 0 1";
      Chess c = new Chess();
      Fen.load(fen, c);
      Update update = new Update(c);
      //when
      update.MakeMove(NewMove(e8, g8));
      //then
      String fenAfterMakingMove = Fen.toFen(c);
      assertThat(fenAfterMakingMove).isEqualTo("r4rk1/p1ppqpb1/bn2pnp1/3PN3/Pp2P3/2N2Q1p/1PPBBPPP/R3K2R w KQ - 1 1");
   }
*/
   @Test
   void test7() {
      //given
      String fen = "r4rk1/p1ppqpb1/bn2pnp1/3PN3/Pp2P3/2N2Q1p/1PPBBPPP/R3K2R w KQ - 1 1";
      Chess c = new Chess();
      Fen.load(fen, c);
      Update update = new Update(c);
      //when
      update.MakeMove(NewMove(e8, g8));
      update.unMakeMove();

      //then
      String fenAfterMakingMove = Fen.toFen(c);
      assertThat(fenAfterMakingMove).isEqualTo("r4rk1/p1ppqpb1/bn2pnp1/3PN3/Pp2P3/2N2Q1p/1PPBBPPP/R3K2R w KQ - 1 1");
   }

   @Test
   void test8() {
      //given
      String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
      Chess c = new Chess();
      Fen.load(fen, c);
      Update update = new Update(c);

      //when
      update.MakeMove(NewMove(a2, a4));
      update.unMakeMove();

      //then
      String fenAfterMakingMove = Fen.toFen(c);
      assertThat(fenAfterMakingMove).isEqualTo(fen);
   }

   @Test
   void test9() {
      //given
      String fen = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q2/PPPBBPpP/1R2K2R w Kkq - 0 2";
      Chess c = new Chess();
      Fen.load(fen, c);
      Update update = new Update(c);

      //when
      boolean valid = update.MakeMove(NewMove(e1, g1));

      //then
      System.out.println(Fen.toFen(c));
      assertThat(valid).isFalse();
   }

   @Test
   void test10() {
      String fen = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q2/PPPBBPpP/1R2K2R w Kkq - 0 2";
      Chess chess = new Chess();
      Fen.load(fen, chess);
      Generator generator = new Generator(chess);
      generator.execute();
      Set<String> result = new HashSet<>();
      Set<String> moves = MoveList.NewMoveList(chess).moveStrings();
      Update update = new Update(chess);
      for (String moveAsString : moves) {
         Move move = new MoveFromString(moveAsString).value();
         if (update.MakeMove(move)) {
            result.add(moveAsString);
            update.unMakeMove();
         }
         String fenAfterMakeAndUnMake = Fen.toFen(chess);
         if (!fenAfterMakeAndUnMake.equals(fen)) {
            System.out.println(move);
            System.out.println("fen:              " + fen);
            System.out.println("afterMakeUnMake:  " + fenAfterMakeAndUnMake);
         }
      }

      assertThat(result).containsExactlyInAnyOrder(
         b1a1, b1c1, b1d1, e1d1, h1g1, h1f1, a2a3, a2a4, b2b3, d2c1,
         d2e3, d2f4, d2g5, d2h6, e2d3, e2c4, e2b5, e2a6, e2d1, e2f1,
         h2h3, h2h4, c3a4, c3d1, c3b5, f3f4, f3f5, f3f6, f3e3, f3d3,
         f3g3, f3h3, f3g4, f3h5, f3g2, d5d6, d5e6, e5c6, e5d3, e5g6,
         e5g4, e5d7, e5f7, e5c4);
   }

   @Test
   void test11() {
      //given
      String fen = "rnbqkbnr/ppp1pppp/3p4/8/Q7/2P5/PP1PPPPP/RNB1KBNR b KQkq - 1 2";
      Chess c = new Chess();
      Fen.load(fen, c);
      Update update = new Update(c);

      //when
      boolean valid = update.MakeMove(NewMove(e8, c8));

      //then
      assertThat(valid).isFalse();
      assertThat(c.kingloc[black]).isEqualTo(Field.e8);
   }

}