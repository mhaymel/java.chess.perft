package com.haymel.chess.perft;

import org.junit.jupiter.api.Test;

import static com.haymel.chess.perft.Color.*;
import static com.haymel.chess.perft.Color.empty;
import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.Piece.*;
import static com.haymel.chess.perft.Piece.king;
import static com.haymel.chess.perft.Piece.pawn;
import static com.haymel.chess.perft.Piece.queen;
import static org.assertj.core.api.Assertions.assertThat;

final class FenTest {

   @Test
   void test1() {
      //given
      String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
      Chess chess = new Chess();
      HalfFullMove halfFullMove = new HalfFullMove();

      //when
      Fen.load(fen, chess, halfFullMove);

      //then
      assertThat(chess.side).isEqualTo(white);
      assertThat(chess.xside).isEqualTo(black);

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
      Fen.load(fen, chess, halfFullMove);

      //then
   }

   @Test
   void test3() {
      //given
      String fen = "8/8/3k4/8/3K4/8/8/8 w - - 0 1";
      Chess chess = new Chess();
      HalfFullMove halfFullMove = new HalfFullMove();

      //when
      Fen.load(fen, chess, halfFullMove);

      //then
      assertThat(chess.side).isEqualTo(white);
      assertThat(chess.xside).isEqualTo(black);

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
      assertThat(halfFullMove.halfMoveClock).isEqualTo(0);
      assertThat(halfFullMove.fullMoveNumber).isEqualTo(1);
   }

   @Test
   void test4() {
      //given
      String fen = "4k3/8/8/2pP4/8/8/8/4K3 w - c6 0 1";
      Chess chess = new Chess();
      HalfFullMove halfFullMove = new HalfFullMove();

      //when
      Fen.load(fen, chess, halfFullMove);

      //then
      assertThat(chess.side).isEqualTo(white);
      assertThat(chess.xside).isEqualTo(black);

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
      assertThat(halfFullMove.halfMoveClock).isEqualTo(0);
      assertThat(halfFullMove.fullMoveNumber).isEqualTo(1);
   }
}

