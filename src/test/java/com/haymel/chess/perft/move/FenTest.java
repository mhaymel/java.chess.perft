package com.haymel.chess.perft.move;

import com.haymel.chess.perft.Castling;
import com.haymel.chess.perft.Chess;
import com.haymel.chess.perft.Fen;
import com.haymel.chess.perft.HalfFullMove;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.haymel.chess.perft.Color.*;
import static com.haymel.chess.perft.Color.empty;
import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.Piece.*;
import static org.assertj.core.api.Assertions.assertThat;

final class FenTest {

   @Test
   void test1() {
      //given
      String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
      Chess chess = new Chess();
      HalfFullMove halfFullMove = new HalfFullMove();

      //when
      Fen.load(fen, chess);

      //then
      assertThat(chess.side).isEqualTo(white);

      assertThat(chess.gameList[chess.hply].castle.kingside[white]).isTrue();
      assertThat(chess.gameList[chess.hply].castle.queenside[white]).isTrue();
      assertThat(chess.gameList[chess.hply].castle.kingside[black]).isTrue();
      assertThat(chess.gameList[chess.hply].castle.queenside[black]).isTrue();

      assertThat(chess.kingloc[white]).isEqualTo(e1);
      assertThat(chess.kingloc[black]).isEqualTo(e8);

      assertThat(chess.board[a1]).isEqualTo(rook);
      assertThat(chess.board[b1]).isEqualTo(knight);
      assertThat(chess.board[c1]).isEqualTo(bishop);
      assertThat(chess.board[d1]).isEqualTo(queen);
      assertThat(chess.board[e1]).isEqualTo(king);
      assertThat(chess.board[f1]).isEqualTo(bishop);
      assertThat(chess.board[g1]).isEqualTo(knight);
      assertThat(chess.board[h1]).isEqualTo(rook);
      assertThat(chess.board[a2]).isEqualTo(pawn);
      assertThat(chess.board[b2]).isEqualTo(pawn);
      assertThat(chess.board[c2]).isEqualTo(pawn);
      assertThat(chess.board[d2]).isEqualTo(pawn);
      assertThat(chess.board[e2]).isEqualTo(pawn);
      assertThat(chess.board[f2]).isEqualTo(pawn);
      assertThat(chess.board[g2]).isEqualTo(pawn);
      assertThat(chess.board[h2]).isEqualTo(pawn);

      assertThat(chess.color[a1]).isEqualTo(white);
      assertThat(chess.color[b1]).isEqualTo(white);
      assertThat(chess.color[c1]).isEqualTo(white);
      assertThat(chess.color[d1]).isEqualTo(white);
      assertThat(chess.color[e1]).isEqualTo(white);
      assertThat(chess.color[f1]).isEqualTo(white);
      assertThat(chess.color[g1]).isEqualTo(white);
      assertThat(chess.color[h1]).isEqualTo(white);
      assertThat(chess.color[a2]).isEqualTo(white);
      assertThat(chess.color[b2]).isEqualTo(white);
      assertThat(chess.color[c2]).isEqualTo(white);
      assertThat(chess.color[d2]).isEqualTo(white);
      assertThat(chess.color[e2]).isEqualTo(white);
      assertThat(chess.color[f2]).isEqualTo(white);
      assertThat(chess.color[g2]).isEqualTo(white);
      assertThat(chess.color[h2]).isEqualTo(white);

      assertThat(chess.board[a8]).isEqualTo(rook);
      assertThat(chess.board[b8]).isEqualTo(knight);
      assertThat(chess.board[c8]).isEqualTo(bishop);
      assertThat(chess.board[d8]).isEqualTo(queen);
      assertThat(chess.board[e8]).isEqualTo(king);
      assertThat(chess.board[f8]).isEqualTo(bishop);
      assertThat(chess.board[g8]).isEqualTo(knight);
      assertThat(chess.board[h8]).isEqualTo(rook);
      assertThat(chess.board[a7]).isEqualTo(pawn);
      assertThat(chess.board[b7]).isEqualTo(pawn);
      assertThat(chess.board[c7]).isEqualTo(pawn);
      assertThat(chess.board[d7]).isEqualTo(pawn);
      assertThat(chess.board[e7]).isEqualTo(pawn);
      assertThat(chess.board[f7]).isEqualTo(pawn);
      assertThat(chess.board[g7]).isEqualTo(pawn);
      assertThat(chess.board[h7]).isEqualTo(pawn);

      assertThat(chess.color[a8]).isEqualTo(black);
      assertThat(chess.color[b8]).isEqualTo(black);
      assertThat(chess.color[c8]).isEqualTo(black);
      assertThat(chess.color[d8]).isEqualTo(black);
      assertThat(chess.color[e8]).isEqualTo(black);
      assertThat(chess.color[f8]).isEqualTo(black);
      assertThat(chess.color[g8]).isEqualTo(black);
      assertThat(chess.color[h8]).isEqualTo(black);
      assertThat(chess.color[a7]).isEqualTo(black);
      assertThat(chess.color[b7]).isEqualTo(black);
      assertThat(chess.color[c7]).isEqualTo(black);
      assertThat(chess.color[d7]).isEqualTo(black);
      assertThat(chess.color[e7]).isEqualTo(black);
      assertThat(chess.color[f7]).isEqualTo(black);
      assertThat(chess.color[g7]).isEqualTo(black);
      assertThat(chess.color[h7]).isEqualTo(black);

      assertThat(chess.color[a3]).isEqualTo(empty);
      assertThat(chess.color[b3]).isEqualTo(empty);
      assertThat(chess.color[c3]).isEqualTo(empty);
      assertThat(chess.color[d3]).isEqualTo(empty);
      assertThat(chess.color[e3]).isEqualTo(empty);
      assertThat(chess.color[f3]).isEqualTo(empty);
      assertThat(chess.color[g3]).isEqualTo(empty);
      assertThat(chess.color[h3]).isEqualTo(empty);

      assertThat(chess.color[a4]).isEqualTo(empty);
      assertThat(chess.color[b4]).isEqualTo(empty);
      assertThat(chess.color[c4]).isEqualTo(empty);
      assertThat(chess.color[d4]).isEqualTo(empty);
      assertThat(chess.color[e4]).isEqualTo(empty);
      assertThat(chess.color[f4]).isEqualTo(empty);
      assertThat(chess.color[g4]).isEqualTo(empty);
      assertThat(chess.color[h4]).isEqualTo(empty);

      assertThat(chess.color[a5]).isEqualTo(empty);
      assertThat(chess.color[b5]).isEqualTo(empty);
      assertThat(chess.color[c5]).isEqualTo(empty);
      assertThat(chess.color[d5]).isEqualTo(empty);
      assertThat(chess.color[e5]).isEqualTo(empty);
      assertThat(chess.color[f5]).isEqualTo(empty);
      assertThat(chess.color[g5]).isEqualTo(empty);
      assertThat(chess.color[h5]).isEqualTo(empty);

      assertThat(chess.color[a6]).isEqualTo(empty);
      assertThat(chess.color[b6]).isEqualTo(empty);
      assertThat(chess.color[c6]).isEqualTo(empty);
      assertThat(chess.color[d6]).isEqualTo(empty);
      assertThat(chess.color[e6]).isEqualTo(empty);
      assertThat(chess.color[f6]).isEqualTo(empty);
      assertThat(chess.color[g6]).isEqualTo(empty);
      assertThat(chess.color[h6]).isEqualTo(empty);

      assertThat(chess.board[a3]).isEqualTo(empty);
      assertThat(chess.board[b3]).isEqualTo(empty);
      assertThat(chess.board[c3]).isEqualTo(empty);
      assertThat(chess.board[d3]).isEqualTo(empty);
      assertThat(chess.board[e3]).isEqualTo(empty);
      assertThat(chess.board[f3]).isEqualTo(empty);
      assertThat(chess.board[g3]).isEqualTo(empty);
      assertThat(chess.board[h3]).isEqualTo(empty);
      assertThat(chess.board[a4]).isEqualTo(empty);
      assertThat(chess.board[b4]).isEqualTo(empty);
      assertThat(chess.board[c4]).isEqualTo(empty);
      assertThat(chess.board[d4]).isEqualTo(empty);
      assertThat(chess.board[e4]).isEqualTo(empty);
      assertThat(chess.board[f4]).isEqualTo(empty);
      assertThat(chess.board[g4]).isEqualTo(empty);
      assertThat(chess.board[h4]).isEqualTo(empty);
      assertThat(chess.board[a5]).isEqualTo(empty);
      assertThat(chess.board[b5]).isEqualTo(empty);
      assertThat(chess.board[c5]).isEqualTo(empty);
      assertThat(chess.board[d5]).isEqualTo(empty);
      assertThat(chess.board[e5]).isEqualTo(empty);
      assertThat(chess.board[f5]).isEqualTo(empty);
      assertThat(chess.board[g5]).isEqualTo(empty);
      assertThat(chess.board[h5]).isEqualTo(empty);
      assertThat(chess.board[a6]).isEqualTo(empty);
      assertThat(chess.board[b6]).isEqualTo(empty);
      assertThat(chess.board[c6]).isEqualTo(empty);
      assertThat(chess.board[d6]).isEqualTo(empty);
      assertThat(chess.board[e6]).isEqualTo(empty);
      assertThat(chess.board[f6]).isEqualTo(empty);
      assertThat(chess.board[g6]).isEqualTo(empty);
      assertThat(chess.board[h6]).isEqualTo(empty);
   }

   @Test
   void test2() {
      //given
      String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - 0 1";
      Chess chess = new Chess();
      Castling castling = new Castling();
      HalfFullMove halfFullMove = new HalfFullMove();

      //when
      Fen.load(fen, chess);

      //then
   }

   @Test
   void test3() {
      //given
      String fen = "8/8/3k4/8/3K4/8/8/8 w - - 0 1";
      Chess chess = new Chess();

      //when
      Fen.load(fen, chess);

      //then
      assertThat(chess.side).isEqualTo(white);

      assertThat(chess.gameList[chess.hply].castle.kingside[white]).isFalse();
      assertThat(chess.gameList[chess.hply].castle.queenside[white]).isFalse();
      assertThat(chess.gameList[chess.hply].castle.kingside[black]).isFalse();
      assertThat(chess.gameList[chess.hply].castle.queenside[black]).isFalse();

      assertThat(chess.kingloc[white]).isEqualTo(d4);
      assertThat(chess.kingloc[black]).isEqualTo(d6);

      assertThat(chess.board[d4]).isEqualTo(king);
      assertThat(chess.color[d4]).isEqualTo(white);

      assertThat(chess.board[d6]).isEqualTo(king);
      assertThat(chess.color[d6]).isEqualTo(black);

      assertThat(chess.enPassantField).isEqualTo(invalid);
      assertThat(chess.halfFullMove.halfMoveClock).isEqualTo(0);
      assertThat(chess.halfFullMove.fullMoveNumber).isEqualTo(1);
   }

   @Test
   void test4() {
      //given
      String fen = "4k3/8/8/2pP4/8/8/8/4K3 w - c6 0 1";
      Chess chess = new Chess();

      //when
      Fen.load(fen, chess);

      //then
      assertThat(chess.side).isEqualTo(white);

      assertThat(chess.gameList[chess.hply].castle.kingside[white]).isFalse();
      assertThat(chess.gameList[chess.hply].castle.queenside[white]).isFalse();
      assertThat(chess.gameList[chess.hply].castle.kingside[black]).isFalse();
      assertThat(chess.gameList[chess.hply].castle.queenside[black]).isFalse();

      assertThat(chess.kingloc[white]).isEqualTo(e1);
      assertThat(chess.kingloc[black]).isEqualTo(e8);

      assertThat(chess.board[e1]).isEqualTo(king);
      assertThat(chess.color[e1]).isEqualTo(white);

      assertThat(chess.board[e8]).isEqualTo(king);
      assertThat(chess.color[e8]).isEqualTo(black);

      assertThat(chess.enPassantField).isEqualTo(c6);
      assertThat(chess.halfFullMove.halfMoveClock).isEqualTo(0);
      assertThat(chess.halfFullMove.fullMoveNumber).isEqualTo(1);
   }

   @Test
   void toFenTest1() {
      //given
      String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
      Chess chess = new Chess();
      Fen.load(fen, chess);

      //when
      String convertedFen = Fen.toFen(chess);

      //then
      assertThat(convertedFen).isEqualTo(fen);
   }

   static Stream<Arguments> fens() {
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
   void testFens(String fen) {
      test(fen);
   }

   void test(String fen) {
      //given
      Chess chess = new Chess();
      Fen.load(fen, chess);

      //when
      String convertedFen = Fen.toFen(chess);

      //then
      assertThat(convertedFen).isEqualTo(fen);
   }

   private static Stream<Arguments> fenProvider() {
      return Stream.of(Arguments.of("8/8/8/8/8/8/8/R7 b - - 0 1"));
//      List<Arguments> list = new ArrayList<>();
//      String[] boards = {
//         "8/8/8/8/8/8/8/8",
//         "8/8/8/8/8/8/8/R7",
//         "8/8/8/8/8/8/8/K6k",
//         "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR",
//         "r3k2r/8/8/8/8/8/8/R3K2R",
//         "8/8/8/3pP3/8/8/8/8",
//         "8/8/8/3Pp3/8/8/8/8",
//         "r1bqkbnr/pppp1ppp/2n5/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R",
//         "r2q1rk1/pp1nbppp/2p1bn2/3p4/3P4/2NBPN2/PPQ2PPP/R1B2RK1"
//      };
//
//      String[] sides = { "w", "b" };
//
//      String[] castles = {
//         "-", "K", "Q", "k", "q",
//         "KQ", "kq", "Kk", "Qq",
//         "KQkq"
//      };
//
//      String[] enPassant = {
//         "-", "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
//         "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6"
//      };
//
//      for (String board : boards) {
//         for (String side : sides) {
//            for (String castle : castles) {
//               for (String ep : enPassant) {
//                  for (int half = 0; half <= 10; half++) {
//                     for (int full = 1; full <= 5; full++) {
//
//                        // En-passant nur erlauben, wenn sinnvoll
//                        if (!ep.equals("-")) {
//                           if (side.equals("w") && ep.endsWith("3")) continue;
//                           if (side.equals("b") && ep.endsWith("6")) continue;
//                        }
//
//                        String fen = String.format(
//                           "%s %s %s %s %d %d",
//                           board,
//                           side,
//                           castle,
//                           ep,
//                           half,
//                           full
//                        );
//
//                        list.add(Arguments.of(fen));
//                     }
//                  }
//               }
//            }
//         }
//      }
//
//      // Sicherheit: garantiert >1000
//      if (list.size() < 1000) {
//         throw new IllegalStateException("Zu wenige FENs: " + list.size());
//      }
//
//      return list.stream();
   }

   @ParameterizedTest
   @MethodSource("fenProvider")
   void testFenProvider(String fen) {
      test(fen);
   }

}

