package com.haymel.chess.uci;

import com.haymel.chess.perft.*;
import com.haymel.chess.util.ValidMoves;

import java.util.Set;

import static com.haymel.chess.util.MoveFromString.NewMoveFromString;

//copilot
final class Engine {

   private final Chess chess;
   private final Update update;
   private final Generator generator;

   Engine() {
      this.chess = new Chess();
      this.update = new Update(chess);
      this.generator = new Generator(chess);
   }

   public void newGame() {
      Fen.loadInitial(chess);
   }

   public void setToStartpos() {
      newGame();
   }

   public void setFromFen(String fen) {
      Fen.load(fen, chess);
   }

   public void makeMove(String moveAlgebraic) {
      System.out.println("# makeMove " + moveAlgebraic);
      Move move = NewMoveFromString(moveAlgebraic).value();
      update.MakeMove(move);
   }

   public String searchBestMove() {
      generator.execute();
      Set<String> moves = new ValidMoves(chess).value();
      if (!moves.isEmpty())
         return moves.iterator().next();
      return "0000";
   }

   public String getCurrentBestMove() {
      return searchBestMove();
   }
}