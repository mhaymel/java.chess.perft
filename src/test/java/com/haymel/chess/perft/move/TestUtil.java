package com.haymel.chess.perft.move;

import com.google.common.collect.Sets;
import com.haymel.chess.perft.Chess;
import com.haymel.chess.perft.Fen;

import java.util.Set;

import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Color.white;
import static com.haymel.chess.perft.Generator.NewGenerator;
import static com.haymel.chess.perft.HalfFullMove.NewHalfFullMove;
import static com.haymel.chess.perft.move.MoveList.NewMoveList;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestUtil {

   public static Set<String> no = m();

   public static Set<String> m(String... moves) {
      return Set.of(moves);
   }

   public static Set<String> u(Set<String> a, Set<String> b) {
      return Sets.union(a, b);
   }

   public static void test(String fen, Set<String> expectedMoves) {
      //given
      Chess chess = new Chess();
      Fen.load(fen, chess, NewHalfFullMove());

      //when
      NewGenerator(chess).execute();

      //then
      Set<String> moves = NewMoveList(chess).moveStrings();
      assertThat(moves).isEqualTo(expectedMoves);
   }

   public static void moveTest(int field, int color, Set<String> expectedMoves, int piece) {
      //given
      Chess chess = new Chess();
      chess.emptyBoard();
      chess.side = color;
      chess.xside = color == white ? black : white;
      chess.color[field] = color;
      chess.board[field] = piece;
      //when
      NewGenerator(chess).execute();
      //then
      Set<String> moves = NewMoveList(chess).moveStrings();
      assertThat(moves).isEqualTo(expectedMoves);
   }

}
