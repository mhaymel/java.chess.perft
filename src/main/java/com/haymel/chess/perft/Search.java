package com.haymel.chess.perft;

import com.haymel.chess.eval.Evaluation;

public final class Search {

   private final Chess chess;
   private final Generator generator;
   private final Update update;
   private int maxDepth;
   private Move bestMove;
   private int bestMoveScore;

   public Search(Chess chess) {
      this(chess, new Generator(chess), new Update(chess));
   }

   private Search(Chess chess, Generator generator, Update update) {
      this.chess = chess;
      this.generator = generator;
      this.update = update;
   }

   public Move search(int maxDepth) {
      this.maxDepth = maxDepth;
      bestMove = null;
      bestMoveScore = -100_000;
      searchImpl(0);
//      System.out.println("# Best move: " + bestMove + " score: " + bestMoveScore);
      return bestMove;
   }

   private int searchImpl(int depth) {
      if (depth >= maxDepth)
         return evaluateStub();

      generateMoves();

      int localBestScore = -100_000;

//      String fen = Fen.toFen(chess);
      int moveCount = chess.moveCount();
      for (int i = 0; i < moveCount; i++) {
         Move move = chess.move(i);
         if (isMakeMove(move)) {
            int score = -searchImpl(depth + 1);
            unMakeMove();
            if (score > localBestScore) {
               localBestScore = score;
               if (depth == 0) {
                  bestMove = move;
                  System.out.println("# New best move: " + bestMove + " score: " + score);
               }
            }
         }
//         String fenAfterMakeAndUnMake = Fen.toFen(chess);
//         if (!fenAfterMakeAndUnMake.equals(fen)) {
//            System.out.println(move);
//            System.out.println("fen:              " + fen);
//            System.out.println("afterMakeUnMake:  " + fenAfterMakeAndUnMake);
//         }
      }
      return localBestScore;
   }

   private void unMakeMove() {
      update.unMakeMove();
   }

   private boolean isMakeMove(Move move) {
      return update.makeMove(move);
   }

   private void generateMoves() {
      generator.execute();
   }

   private int evaluateStub() {
      int value = Evaluation.evaluate(chess);
      if (chess.side == Color.black) {
         value = -value;
      }
      return value;
   }

   public Move bestMove() {
      return bestMove;
   }

}
