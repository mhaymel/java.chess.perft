package com.haymel.chess.eval;

import com.haymel.chess.perft.Chess;
import com.haymel.chess.perft.Fen;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.haymel.chess.eval.Evaluation.evaluate;
import static org.assertj.core.api.Assertions.assertThat;

final class EvaluationTest {

   static Stream<Arguments> fens() {
      return Stream.of(
         Arguments.of(Fen.initial, 0),
         Arguments.of("8/8/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 ", 4065),
         Arguments.of("rnbqkbnr/pppppppp/8/8/8/8/8/8 b KQkq - 0 1 ", -4065),
         Arguments.of("rnbqkbnr/p1pppppp/8/1p6/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 0 1", 8),
         Arguments.of("8/8/8/8/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 0 1", 4065),
         Arguments.of("rnbqkbnr/p1pppppp/8/1p6/8/8/8/8 w KQkq - 0 1 ", -4057)
      );
   }

   @ParameterizedTest
   @MethodSource("fens")
   void testFens(String fen, int eval) {
      test(fen, eval);
   }

   void test(String fen, int expectedEvaluation) {
      //given
      Chess chess = Fen.load(fen);
      //when
      int evaluate = evaluate(chess.board, chess.color);
      //then
      assertThat(evaluate).isEqualTo(expectedEvaluation);
   }
}