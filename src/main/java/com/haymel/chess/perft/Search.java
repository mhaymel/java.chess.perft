package com.haymel.chess.perft;

import com.haymel.chess.eval.Evaluation;

import java.util.concurrent.atomic.AtomicBoolean;

public final class Search {

   private final Chess chess;
   private final Generator generator;
   private final Update update;
   private final AtomicBoolean stop;
   public long nodes;
   private Move bestMove;

   public Search(Chess chess) {
      this(chess, new AtomicBoolean(false));
   }

   public Search(Chess chess, AtomicBoolean stop) {
      this(chess, new Generator(chess), new Update(chess), stop);
   }

   private Search(Chess chess, Generator generator, Update update, AtomicBoolean stop) {
      this.chess = chess;
      this.generator = generator;
      this.update = update;
      this.stop = stop;
   }

   public Move search(int depth) {
      nodes = 0;
      stop.set(false);
      bestMove = null;
      searchImpl(depth);
      return bestMove;
   }

   private int searchImpl(int depth) {
      if (stop.get()) return 0;

      if (depth < 1)
         return captureSearch();

      nodes++;

      generateMoves();

      int localBestScore = -100_000;

//      String fen = Fen.toFen(chess);
      int moveCount = chess.moveCount();
      for (int i = 0; i < moveCount; i++) {
         Move move = chess.move(i);
         if (isMakeMove(move)) {
            int score = -searchImpl(depth - 1);
            unMakeMove();
            if (score > localBestScore) {
               localBestScore = score;
               if (chess.ply == 0) {
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

   private int captureSearch() {
      nodes++;

      int x = evaluateStub();

      //generateCaptureMoves();

      return x;
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

   private void generateCaptureMoves() {
      generator.executeCaptureMoves();
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
