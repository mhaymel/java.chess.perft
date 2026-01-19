package com.haymel.chess.uci;

import com.haymel.chess.perft.*;
import com.haymel.chess.util.ValidMoves;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.haymel.chess.util.MoveFromString.NewMoveFromString;

public class Engine {

   private final AtomicBoolean stopRequested;
   private final Chess chess;
   private final Update update;
   private final ScheduledExecutorService executor;
   private Thread searchThread;
   private ScheduledFuture<?> stopFuture;

   Engine() {
      this.chess = new Chess();
      this.update = new Update(chess);
      this.executor = Executors.newSingleThreadScheduledExecutor();
      this.stopRequested = new AtomicBoolean(false);
   }

   public static Move moveFromUci(String moveAlgebraic) {
      return NewMoveFromString(moveAlgebraic).value();
   }

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

      boolean whiteToMove = chess.side == Color.white;
      int timeForMove = tm.computeTimeForMove(whiteToMove);
      System.out.println("# time for move: " + timeForMove / 1000d + "secs");

      int depthLimit = tm.getDepthLimit();
      if (depthLimit <= 0) depthLimit = 64;

      int nodeLimit = tm.getNodeLimit();
      if (nodeLimit <= 0) nodeLimit = Integer.MAX_VALUE;

      searchThread = new Thread(() -> searchLoop());
      searchThread.setDaemon(true);
      searchThread.start();

      // Schedule stopSearch after timeForMove ms using executor
      if (stopFuture != null && !stopFuture.isDone()) stopFuture.cancel(true);
      stopFuture = executor.schedule(this::setStopSearch, timeForMove, TimeUnit.MILLISECONDS);
   }

   private void setStopSearch() {
      System.out.println("# time limit reached, stopping search");
      stopRequested.set(true);
   }

   private void searchLoop() {
      long start = System.currentTimeMillis();
      String bestmove = "0000";
      Set<String> moves = generateLegalMoves();
      if (moves.isEmpty()) {
         System.out.println("bestmove " + bestmove);
         return;
      }

      Search search = new Search(chess, stopRequested);
      for (int depth = 1; !stopRequested(); depth++) {
         System.out.println("# searching at depth " + depth);
         search.search(depth);
         Move move = search.bestMove();
         if (!stopRequested()) bestmove = move.uci();
      }
      System.out.println("bestmove " + bestmove + " ");
      long elapsedMs = System.currentTimeMillis() - start;
      System.out.printf("# search time: %.3f s\n", elapsedMs / 1000.0);
   }

   private boolean stopRequested() { return stopRequested.get(); }

   public void stopSearch() {
      stopRequested.set(true);
      if (searchThread != null && searchThread.isAlive()) {
         try {
            searchThread.join(50);
         } catch (InterruptedException ignored) {
         }
      }
      if (stopFuture != null && !stopFuture.isDone()) {
         stopFuture.cancel(true);
         stopFuture = null;
      }
   }

   private Set<String> generateLegalMoves() {
      return ValidMoves.NewValidMoves(chess).value();
   }

   public void makeMoveFromUci(String move) {
      update.makeMove(moveFromUci(move));
   }

   public void shutdown() {
      executor.shutdownNow();
   }
}