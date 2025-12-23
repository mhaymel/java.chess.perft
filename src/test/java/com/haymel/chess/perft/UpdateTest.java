package com.haymel.chess.perft;

import org.junit.jupiter.api.Test;

import static com.haymel.chess.perft.Move.NewMove;


final class UpdateTest {

   @Test
   void test() {
      Chess c = new Chess();
      Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 ", c);
      Update update = new Update(c);
      update.MakeMove(NewMove(Field.e2, Field.e4));
      update.unMakeMove();
   }
}