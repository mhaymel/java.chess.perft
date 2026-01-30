package com.haymel.chess.perft;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static com.haymel.chess.perft.Notation.uci;
import static org.assertj.core.api.Assertions.assertThat;

final class CaptureGeneratorTest {
   private static Stream<Arguments> fens() {
      return Stream.of(
         Arguments.of("8/8/8/8/8/8/8/R7 b - - 0 1"),
         Arguments.of("8/8/8/N7/8/8/8/R2N4 b - - 0 1"),
         Arguments.of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"),
         Arguments.of("8/8/8/4k3/8/8/8/7K b - - 0 1"),
         Arguments.of("k7/8/8/8/8/8/8/K7 b - - 0 1"),
         Arguments.of("7k/8/8/8/8/8/8/K7 b - - 0 1"),
         Arguments.of("8/8/8/8/8/8/8/K6k b - - 0 1"),
         Arguments.of("7K/8/8/8/8/8/8/k7 b - - 0 1"),
         Arguments.of("7K/8/3PPP2/3PkP2/3PPP2/8/8/8 b - - 0 1"),
         Arguments.of("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1"),
         Arguments.of("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQq - 0 1"),
         Arguments.of("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQk - 0 1"),
         Arguments.of("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQ - 0 1"),
         Arguments.of("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b - - 0 1"),
         Arguments.of("r3kb2/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1"),
         Arguments.of("r2qk2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1"),
         Arguments.of("r1b1k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1"),
         Arguments.of("r2QkB1r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1"),
         Arguments.of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1"),

         //chatgpt
         // ===== Leeres / Minimal =====
         Arguments.of("8/8/8/8/8/8/8/8 w - - 0 1"),
         Arguments.of("8/8/8/8/8/8/8/8 b - - 0 1"),
         Arguments.of("8/8/8/8/8/8/8/R7 w - - 0 1"),
         Arguments.of("8/8/8/8/8/8/8/R7 b - - 0 1"),

         // ===== Einzelne Figuren =====
         Arguments.of("8/8/8/8/8/8/8/K7 w - - 0 1"),
         Arguments.of("8/8/8/8/8/8/8/k7 b - - 0 1"),
         Arguments.of("8/8/8/8/8/8/8/Q7 w - - 0 1"),
         Arguments.of("8/8/8/8/8/8/8/q7 b - - 0 1"),
         Arguments.of("8/8/8/8/8/8/8/N7 w - - 0 1"),
         Arguments.of("8/8/8/8/8/8/8/n7 b - - 0 1"),

         // ===== Mehrere Figuren =====
         Arguments.of("8/8/8/N7/8/8/8/R2N4 b - - 0 1"),
         Arguments.of("8/8/8/2B5/8/8/8/R2N4 w - - 0 1"),
         Arguments.of("8/8/8/2B5/3Q4/8/8/4K3 w - - 0 1"),

         // ===== Bauern =====
         Arguments.of("8/8/8/8/8/8/PPPPPPPP/8 w - - 0 1"),
         Arguments.of("8/pppppppp/8/8/8/8/8/8 b - - 0 1"),
         Arguments.of("8/8/8/8/8/8/P7/8 w - - 0 1"),
         Arguments.of("8/8/8/8/8/8/7p/8 b - - 0 1"),

         // ===== En Passant =====
         Arguments.of("8/8/8/3pP3/8/8/8/8 w - d6 0 1"),
         Arguments.of("8/8/8/3Pp3/8/8/8/8 b - e3 0 1"),
         Arguments.of("8/8/8/1pP5/8/8/8/8 w - b6 0 1"),
         Arguments.of("8/8/8/5Pp1/8/8/8/8 b - g3 0 1"),
         Arguments.of("rnbqkbnr/pppp1ppp/8/4pP2/8/8/PPPP2PP/RNBQKBNR w KQkq e6 0 3"),

         // ===== Rochade =====
         Arguments.of("r3k2r/8/8/8/8/8/8/R3K2R w KQkq - 0 1"),
         Arguments.of("r3k2r/8/8/8/8/8/8/R3K2R b KQkq - 0 1"),
         Arguments.of("r3k2r/8/8/8/8/8/8/R3K2R w KQ - 0 1"),
         Arguments.of("r3k2r/8/8/8/8/8/8/R3K2R w kq - 0 1"),
         Arguments.of("r3k2r/8/8/8/8/8/8/R3K2R w Qq - 0 1"),
         Arguments.of("4k3/8/8/8/8/8/8/4K3 w - - 0 1"),

         // ===== Startpositionen =====
         Arguments.of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"),
         Arguments.of("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1"),

         // ===== Ungewöhnliche, aber gültige =====
         Arguments.of("8/8/8/8/8/8/8/K6k w - - 50 100"),
         Arguments.of("8/8/8/8/8/8/8/K6k b - - 75 200"),
         Arguments.of("1k6/8/8/8/8/8/8/6K1 w - - 0 1"),
         Arguments.of("8/8/8/8/8/2n5/8/4K2k w - - 0 1"),

         // ===== Gemischte Mittelspielstellungen =====
         Arguments.of("r1bqkbnr/pppp1ppp/2n5/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R w KQkq - 2 3"),
         Arguments.of("r2q1rk1/pp1nbppp/2p1bn2/3p4/3P4/2NBPN2/PPQ2PPP/R1B2RK1 w - - 6 9"),
         Arguments.of("4rrk1/ppp2ppp/2n1bn2/3p4/3P4/2NBPN2/PPP2PPP/R2R2K1 b - - 8 12"),

         // ===== Viele Bauern + EP =====
         Arguments.of("8/8/8/ppppPPPP/8/8/8/8 w - a6 0 1"),
         Arguments.of("8/8/8/PPPPpppp/8/8/8/8 b - h3 0 1")
      );
   }

   @ParameterizedTest
   @MethodSource("fens")
   void testFen(String fen) {
      Chess chessAllMoves = Fen.load(fen);
      new Generator(chessAllMoves).execute();
      Set<String> chessAllMovesCapture = allWithoutEnPassantAndPromotionPiecesRemoved(chessAllMoves);

      Chess chessOnlyCaptureMoves = Fen.load(fen);
      new CaptureGenerator(chessOnlyCaptureMoves).execute();
      Set<String> chessOnlyCaptureMoveAsString = allWithoutEnPassantAndPromotionPiecesRemoved(chessOnlyCaptureMoves);

      assertThat(chessAllMovesCapture).isEqualTo(chessOnlyCaptureMoveAsString);
   }

   @Test
   void test1() {
      testFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
   }

   @Test
   void test2() {
      testFen("K2r4/4P3/8/8/8/8/8/k7 w - - 0 1");
   }

   private Set<String> allWithoutEnPassantAndPromotionPiecesRemoved(Chess c) {
      Set<String> moves = new HashSet<>();
      int from = c.firstMove[c.ply];
      int to = c.firstMove[c.ply + 1];
      for (int i = from; i < to; i++) {
         Move m = c.moveList[i];
         if (!c.isEnPassant(m) && !c.isEmpty(m.to))
            moves.add(uci(m.from, m.to));
      }
      return moves;
   }

}