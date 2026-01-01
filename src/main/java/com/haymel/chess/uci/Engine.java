package com.haymel.chess.uci;

import com.haymel.chess.perft.*;
import com.haymel.chess.util.ValidMoves;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.haymel.chess.util.MoveFromString.NewMoveFromString;

public class Engine {

   private Thread searchThread;
   private final AtomicBoolean stopRequested = new AtomicBoolean(false);

   private final Chess chess;
   private final Update update;
   private final Generator generator;

   Engine() {
      this.chess = new Chess();
      this.update = new Update(chess);
      this.generator = new Generator(chess);
   }

   private String bestMoveUci = "0000";

   public void newGame() {
      Fen.loadInitial(chess);
   }

   public void setToStartpos() {
      newGame();
   }

   public void setFromFen(String fen) {
      setToStartpos(); // Dummy
   }

   public void startSearch(TimeManager tm) {
      stopSearch();

      stopRequested.set(false);
      bestMoveUci = "0000";

      boolean whiteToMove = chess.side == Color.white;
      int timeForMove = tm.computeTimeForMove(whiteToMove);
      long endTime = System.currentTimeMillis() + timeForMove;

      int depthLimit = tm.getDepthLimit();
      if (depthLimit <= 0) depthLimit = 64;

      int nodeLimit = tm.getNodeLimit();
      if (nodeLimit <= 0) nodeLimit = Integer.MAX_VALUE;

      final int finalDepthLimit = depthLimit;
      final int finalNodeLimit = nodeLimit;

      searchThread = new Thread(() -> searchLoop(finalDepthLimit, finalNodeLimit, endTime));
      searchThread.setDaemon(true);
      searchThread.start();
   }

   private void searchLoop(int depthLimit, int nodeLimit, long endTime) {
      int nodes = 0;

      for (int depth = 1; depth <= depthLimit; depth++) {

         if (stopRequested.get() || System.currentTimeMillis() >= endTime) break;

         Set<String> moves = generateLegalMoves();
         if (moves.isEmpty()) {
            bestMoveUci = "0000";
            break;
         }

         String localBestMove = moves.iterator().next();
         int localBestScore = Integer.MIN_VALUE;

         for (String move : moves) {
            if (stopRequested.get() || System.currentTimeMillis() >= endTime || nodes >= nodeLimit)
               break;

            nodes++;

            int score = evaluateStub(move, depth);

            if (score > localBestScore) {
               localBestScore = score;
               localBestMove = move;
            }
         }

         bestMoveUci = localBestMove;

         System.out.println("info depth " + depth +
            " score cp " + localBestScore +
            " nodes " + nodes +
            " pv " + bestMoveUci);

         if (System.currentTimeMillis() >= endTime || stopRequested.get() || nodes >= nodeLimit)
            break;
      }

      System.out.println("bestmove " + bestMoveUci);
   }

   public void stopSearch() {
      stopRequested.set(true);
      if (searchThread != null && searchThread.isAlive()) {
         try {
            searchThread.join(50);
         } catch (InterruptedException ignored) {
         }
      }
   }

   private Set<String> generateLegalMoves() {
      return ValidMoves.NewValidMoves(chess).value();
   }

   private int evaluateStub(String move, int depth) {
      return 0;
   }

   public void makeMoveFromUci(String moveAlgebraic) {
      System.out.println("# makeMove " + moveAlgebraic);
      Move move = NewMoveFromString(moveAlgebraic).value();
      update.MakeMove(move);

   }
}