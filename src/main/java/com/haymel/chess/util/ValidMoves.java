package com.haymel.chess.util;

import com.haymel.chess.perft.*;

import java.util.HashSet;
import java.util.Set;

public final class ValidMoves {

   private final Chess chess;

   public static ValidMoves NewValidMoves(String fen) {
      Chess chess = new Chess();
      Fen.load(fen, chess);
      return new ValidMoves(chess);
   }

   public ValidMoves(Chess chess) {
      this.chess = chess;
   }

   public Set<String> value() {
      String fen = Fen.toFen(chess);
      Generator generator = new Generator(chess);
      generator.execute();
      Set<String> result = new HashSet<>();
      Set<String> moves = MoveList.NewMoveList(chess).moveStrings();
      Update update = new Update(chess);
      for (String moveAsString : moves) {
         Move move = new MoveFromString(moveAsString).value();
         if (update.MakeMove(move)) {
            result.add(moveAsString);
            update.unMakeMove();
         }
         String fenAfterMakeAndUnMake = Fen.toFen(chess);
         if (!fenAfterMakeAndUnMake.equals(fen)) {
            System.out.println(move);
            System.out.println("fen:              " + fen);
            System.out.println("afterMakeUnMake:  " + fenAfterMakeAndUnMake);
         }
      }
      return result;
   }
}
