package com.haymel.chess.util;

import com.haymel.chess.perft.Chess;
import com.haymel.chess.perft.Fen;
import com.haymel.chess.perft.Move;
import com.haymel.chess.perft.Update;

import static com.haymel.chess.util.MovesFromString.NewMovesFromString;

public final class MakeFenFromMoves {

   private final Move[] moves;

   public MakeFenFromMoves(Move[] values) {
      this.moves = values;
   }

   public static MakeFenFromMoves NewMakeFenFromMoves(String moves) {
      return NewMakeFenFromMoves(NewMovesFromString(moves).values());
   }

   public static MakeFenFromMoves NewMakeFenFromMoves(Move[] values) {
      return new MakeFenFromMoves(values);
   }

   public String fen() {
      Chess chess = Fen.load(Fen.initial);
      Update update = new Update(chess);
      for (Move move : moves)
         update.makeMove(move);
      return Fen.toFen(chess);
   }
}
