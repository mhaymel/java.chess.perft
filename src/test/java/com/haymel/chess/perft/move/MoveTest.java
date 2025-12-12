package com.haymel.chess.perft.move;

import com.haymel.chess.perft.Move;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.Piece.*;
import static com.haymel.chess.perft.move.Moves.e2e4;
import static com.haymel.chess.perft.move.Moves.e7e8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

final class MoveTest {

   private static final String e7e8q = e7e8 + "q";
   private static final String e7e8r = e7e8 + "r";
   private static final String e7e8b = e7e8 + "b";
   private static final String e7e8n = e7e8 + "n";

   static Stream<Arguments> uciNotation() {
      return Stream.of(
         of(new Move(), "invalid"),
         of(new Move(e2, e4), e2e4),
         of(new Move(e7, e8, queen), e7e8q),
         of(new Move(e7, e8, rook), e7e8r),
         of(new Move(e7, e8, bishop), e7e8b),
         of(new Move(e7, e8, knight), e7e8n)
      );
   }

   @ParameterizedTest
   @MethodSource("uciNotation")
   void uciNotation(Move move, String uciMove) {
      assertThat(move.uci()).isEqualTo(uciMove);
   }

}