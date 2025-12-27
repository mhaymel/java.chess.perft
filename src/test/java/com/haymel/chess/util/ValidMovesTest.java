package com.haymel.chess.util;

import com.haymel.chess.perft.Chess;
import com.haymel.chess.perft.Fen;
import com.haymel.chess.perft.Fens;
import com.haymel.chess.perft.move.FenProvider;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.haymel.chess.util.ValidMoves.NewValidMoves;
import static org.assertj.core.api.Assertions.assertThat;

final class ValidMovesTest {

   @Test
   void test() {
      String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 ";
      NewValidMoves(fen).value();
   }

   @Test
   void test2() {
      List<String> fens = Fens.read();
      Chess chess = new Chess();
      for (String fen : fens) {
         Fen.load(fen, chess);
         new ValidMoves(chess).value();
         assertThat(Fen.toFen(chess)).isEqualTo(fen);
      }
   }

   @Test
   void testFenProvider() {
      List<String> fens = FenProvider.fens();
      Chess chess = new Chess();
      for (String fen : fens) {
         Fen.load(fen, chess);
         new ValidMoves(chess).value();
         assertThat(Fen.toFen(chess)).isEqualTo(fen);
      }
   }

   @Test
   void test3() {
      String fen = "6QR/8/3p1kN1/1P5P/3N1r2/1b4P1/3r4/2K2b2 b - - 13 10";
      NewValidMoves(fen).value();
   }

}