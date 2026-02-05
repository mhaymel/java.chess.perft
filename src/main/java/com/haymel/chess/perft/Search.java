package com.haymel.chess.perft;

import com.haymel.chess.eval.Evaluation;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.haymel.chess.eval.Evaluation.pieceValue;
import static com.haymel.chess.perft.CaptureGenerator.NewCaptureGenerator;
import static com.haymel.chess.perft.Generator.NewGenerator;
import static com.haymel.chess.perft.LowestAttacker.NewLowestAttacker;

public final class Search {

   private final Chess chess;
   private final Generator generator;
   private final CaptureGenerator captureGenerator;
   private final Update update;
   private final LowestAttacker lowestAttacker;
   private final AtomicBoolean stop;
   public long nodes;
   private Move bestMove;

   public static Search NewSearch(Chess chess) { return new Search(chess); }

   public Search(Chess chess) { this(chess, new AtomicBoolean(false)); }

   public Search(Chess chess, AtomicBoolean stop) {
      this(chess, NewGenerator(chess), NewCaptureGenerator(chess), new Update(chess), NewLowestAttacker(chess), stop);
   }

   private Search(
      Chess chess, Generator generator, CaptureGenerator captureGenerator, Update update, LowestAttacker lowestAttacker, AtomicBoolean stop) {
      this.chess = chess;
      this.generator = generator;
      this.captureGenerator = captureGenerator;
      this.update = update;
      this.lowestAttacker = lowestAttacker;
      this.stop = stop;
   }

   public int search(int depth) {
      nodes = 0;
      stop.set(false);
      bestMove = null;
      return searchImpl(depth);
   }

   private int searchImpl(int depth) {
      if (stop.get()) return 0;

      if (depth < 1)
         return captureSearch();

      nodes++;

      generateMoves();

      int localBestScore = -10001;
      int validMovesCount = 0;

      boolean check = isInCheck();

      int moveCount = chess.moveCount();
      for (int i = 0; i < moveCount; i++) {
         Move move = chess.move(i);
         if (makeMove(move)) {
            validMovesCount++;

//            int d = depth - 2;
//            if (isInCheck()) d = depth;
//            else if (check || validMovesCount == 1) d = depth - 1;
//            int score = -searchImpl(d);
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
      }
      if (validMovesCount == 0) {
         if (isInCheck()) return -10000 + chess.ply;
         return 0;
      }
      if (chess.fiftyMoveCounter >= 100) return 0;

      return localBestScore;
   }

   private boolean isInCheck() {
      return update.a.attack(chess.otherSide(), chess.kingloc[chess.side]); //TODO
   }

   private boolean makeMove(Move move) {
      return update.makeMove(move);
   }

   private void unMakeMove() {
      update.unMakeMove();
   }

   private boolean makeRecaptureMove(int from, int to) {
      return update.makeRecaptureMove(from, to);
   }

   private void unMakeRecaptureMove() {
      update.unMakeRecaptureMove();
   }

   private void generateMoves() {
      generator.execute();
   }

   private void generateCaptureMoves() {
      captureGenerator.execute();
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

   public int captureSearch() {
      if (stop.get()) return 0;

      nodes++;

      int x = evaluateStub();
      int best = 0;

      generateCaptureMoves();

      int moveCount = chess.moveCount();
      for (int i = 0; i < moveCount; i++) {
         Move move = chess.move(i);
         int score = ReCaptureSearch(move);
         if (score > best) best = score;
      }
      return (best > 0) ? best + x : x;
   }

   private int ReCaptureSearch(Move move) {
      int from = move.from;
      final int to = move.to;
      int b;
      int c = 0;
      int t = 0;

      int[] score = new int[12];
      Arrays.fill(score, 0);

      score[0] = pieceValue[chess.board[to]];
      score[1] = pieceValue[chess.board[from]];

      int total_score = 0;

      while (c < 10) {
         if (!makeRecaptureMove(from, to))
            break;
         t++;
         nodes++;
         c++;

         b = LowestAttacker(chess.side, to);

         if (b > -1) {
            score[c + 1] = pieceValue[chess.board[b]];
            if (score[c] > score[c - 1] + score[c + 1]) {
               c--;
               break;
            }
         } else {
            break;
         }
         from = b;
      }

      while (c > 1) {
         if (score[c - 1] >= score[c - 2])
            c -= 2;
         else
            break;
      }

      for (int x = 0; x < c; x++) {
         if (x % 2 == 0)
            total_score += score[x];
         else
            total_score -= score[x];
      }

      while (t != 0) {
         unMakeRecaptureMove();
         t--;
      }

      return total_score;
   }

   private int LowestAttacker(int side, int field) {
      return lowestAttacker.calculate(side, field);
   }

}
