package com.haymel.chess.perft.moves.white;

import com.haymel.chess.perft.Chess;
import com.haymel.chess.perft.Gen;
import com.haymel.chess.perft.Piece;
import com.haymel.chess.perft.moves.KnightMoves;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.haymel.chess.perft.Color.white;
import static com.haymel.chess.perft.Gen.NewGen;
import static com.haymel.chess.perft.help.MoveList.NewMoveList;
import static com.haymel.chess.perft.moves.Moves.*;
import static com.haymel.chess.perft.moves.TestUtil.m;
import static com.haymel.chess.perft.moves.TestUtil.test;
import static org.assertj.core.api.Assertions.assertThat;

public final class KnightTests {

   public static Stream<Arguments> knightMoves() {
      return KnightMoves.knightMoves();
   }

   static void knightMoves(int field, int color, Set<String> expectedMoves) {
      //given
      Chess chess = new Chess();
      chess.emptyBoard();
      chess.side = color;
      chess.xside = Gen.opponent[color];
      chess.color[field] = color;
      chess.board[field] = Piece.knight;
      //when
      NewGen(chess).execute();
      //then
      Set<String> moves = NewMoveList(chess).moveStrings();
      assertThat(moves).isEqualTo(expectedMoves);
   }

   @ParameterizedTest
   @MethodSource("knightMoves")
   void knightMoves(int field, Set<String> expectedMoves) {
      knightMoves(field, white, expectedMoves);
   }

   @Test
   void whiteKnightCaptureNonCaptureMoves() {
      test("8/8/3p1p2/2p3p1/4N3/2p3p1/3p1p2/8 w - - 0 1 ", m(e4d6, e4f6, e4g5, e4g3, e4f2, e4d2, e4c3, e4c5));
      test("8/8/3P1P2/2P3P1/2P1N1P1/2P3P1/3P1P2/8 w - - 0 1 ", m(c5c6, d6d7, f6f7, g5g6, f2f3, f2f4, d2d3, d2d4));
   }


}
