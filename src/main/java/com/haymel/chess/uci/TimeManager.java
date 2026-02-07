package com.haymel.chess.uci;

//copilot
public class TimeManager {

   private int wtime, btime;
   private int winc, binc;
   private int movetime;
   private int movestogo;
   private int depthLimit;
   private int nodeLimit;

   public void setParameters(int wtime, int btime, int winc, int binc,
                             int movetime, int movestogo,
                             int depthLimit, int nodeLimit) {

      this.wtime = wtime;
      this.btime = btime;
      this.winc = winc;
      this.binc = binc;
      this.movetime = movetime;
      this.movestogo = movestogo;
      this.depthLimit = depthLimit;
      this.nodeLimit = nodeLimit;
   }

   public int computeTimeForMove(boolean whiteToMove) {

      if (movetime > 0) {
         return movetime;
      }

      int timeLeft = whiteToMove ? wtime : btime;
      int inc = whiteToMove ? winc : binc;

      if (timeLeft <= 0) {
         return 50;
      }

      if (movestogo > 0) {
         return Math.max(10, timeLeft / movestogo + inc);
      }

      int timeForMove = timeLeft / 30 + inc;

      if (timeLeft < 2000) {
         timeForMove = Math.max(50, timeLeft / 2);
      }

      return timeForMove;
   }

   public int getDepthLimit() {
      return depthLimit;
   }

   public int getNodeLimit() {
      return nodeLimit;
   }
}