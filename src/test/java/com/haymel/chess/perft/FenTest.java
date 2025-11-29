package com.haymel.chess.perft;

import org.junit.jupiter.api.Test;

final class FenTest {

   @Test
   void test1() {
      //given
      String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
      Chess chess = new Chess();
      Castling castling = new Castling();
      EnpassantHalfFullMove enpassantHalfFullMove = new EnpassantHalfFullMove();

      //when
      Fen.load(fen, chess, castling, enpassantHalfFullMove);

      //then
   }


   @Test
   void test2() {
      //given
      String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - 0 1";
      Chess chess = new Chess();
      Castling castling = new Castling();
      EnpassantHalfFullMove enpassantHalfFullMove = new EnpassantHalfFullMove();

      //when
      Fen.load(fen, chess, castling, enpassantHalfFullMove);

      //then
   }
}
