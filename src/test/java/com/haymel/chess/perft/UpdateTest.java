package com.haymel.chess.perft;

import org.junit.jupiter.api.Test;

import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.Field.e2;
import static com.haymel.chess.perft.Field.e4;
import static com.haymel.chess.perft.Move.NewMove;
import static com.haymel.chess.perft.Piece.bishop;
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
      String fen = "r3k2r/8/8/8/8/8/8/R3K2R w K - 0 1";
      Chess c = new Chess();
      Fen.load(fen, c);
      Update update = new Update(c);
      update.MakeMove(NewMove(e1, g1));
      update.unMakeMove();
      String fenAfterMakeAndUnMakeMove = Fen.toFen(c);
      assertThat(fenAfterMakeAndUnMakeMove).isEqualTo(fen);
   }

}