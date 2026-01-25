package com.haymel.chess.uci;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

final class EngineTest {

   @Test
   void test1() {
      //given
      String movesAsString =
         "g1f3 c7c6 g2g3 d7d6 d2d4 c8g4 f1g2 d8a5 b1c3 c6c5 d4c5 a5c5 f3e5 c5e5 g2b7 " +
         "e5c5 d1d5 c5b4 b7c6 b8d7 c6d7 e8d7 d5a8 g4e2 a8a7 d7c8 e1e2 b4c4 e2e1 c4c6 " +
         "h1f1 g8f6 c1e3 f6g4 c3a4 g4e3 a4b6 c6b6 a7b6 e3c2 e1e2 c2a1 b6a7 a1b3 a2b3 c8d8 f1c1";
      String[] moves = movesAsString.split("\\s+");
      Engine engine = new Engine();
      engine.newGame();
      //when
      engine.makeMoves(moves);
      String fen = engine.fen();
      //then
      assertThat(fen).isEqualTo("3k1b1r/Q3pppp/3p4/8/8/1P4P1/1P2KP1P/2R5 b - - 2 24");
   }

   @Test
   void test2() {
      //given
      String movesAsString =
         "e2e3 e7e5 g1f3 e5e4 f3d4 d7d5 b1c3 g8f6 a1b1 c7c5 f1b5 e8e7 d4b3 c5c4 b3d4 a7a6 " +
         "b5a4 b7b5 b2b3 b5a4 b3c4 e7e8 c4d5 c8g4 f2f3 e4f3 g2f3 g4h3 h1g1 f6d5 c3d5 d8d5 " +
         "b1b2 f8e7 g1g3 e7h4 c2c3 b8c6 d1a4 h4g3 h2g3 h3d7 a4b3 d5h5 d4c6 h5h1 e1f2 d7c6 " +
         "b3b8 a8b8 b2b8 e8d7 b8h8 h1h2 f2f1 c6b5 c3c4 b5c4";
      String[] moves = movesAsString.split("\\s+");
      Engine engine = new Engine();
      engine.newGame();
      //when
      engine.makeMoves(moves);
      String fen = engine.fen();
      //then
      assertThat(fen).isEqualTo("7R/3k1ppp/p7/8/2b5/4PPP1/P2P3q/2B2K2 w - - 0 30");
   }

   @Test
   void test3() {
      //given
      String movesAsString =
         "d2d3 d7d5 c1g5 b8c6 e2e3 f7f6 g5f4 e7e5 f4g3 g8h6 d1h5 g7g6 h5h4 f8b4 b1c3 h6f5 " +
         "h4g4 f5e3 g4e2 c6d4 f2e3 d4e2 g1e2 d5d4 a2a3 b4c3 b2c3 d4e3 a1b1 d8d5 e2c1 d5a5 " +
         "b1b4 c7c5 c1b3 a5a3 b4c4 a3b2 e1d1 c8e6 b3c5 e6c4 d3c4 e8c8 d1e2 b2c2 e2f3 e5e4 " +
         "c5e4 h8e8 e4d6 d8d6 g3d6 c2f2 f3g4 e8e4";
      String[] moves = movesAsString.split("\\s+");
      Engine engine = new Engine();
      engine.newGame();
      //when
      engine.makeMoves(moves);
      String fen = engine.fen();
      //then
      assertThat(fen).isEqualTo("2k5/pp5p/3B1pp1/8/2P1r1K1/2P1p3/5qPP/5B1R w - - 3 29");
   }

}