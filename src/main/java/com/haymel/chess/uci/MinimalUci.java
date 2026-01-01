package com.haymel.chess.uci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinimalUci {

   private final Engine engine = new Engine();
   private final TimeManager timeManager = new TimeManager();

   public void run() throws IOException {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      while (true) {
         String line = in.readLine();
         if (line == null) continue;
         line = line.trim();
         if (line.isEmpty()) continue;

         if (line.equals("uci")) {
            System.out.println("id name MinimalEngine");
            System.out.println("id author Markus");
            System.out.println("uciok");
         }

         else if (line.equals("isready")) {
            System.out.println("readyok");
         }

         else if (line.equals("ucinewgame")) {
            engine.newGame();
         }

         else if (line.startsWith("position")) {
            handlePosition(line);
         }

         else if (line.startsWith("go")) {
            handleGo(line);
         }

         else if (line.equals("stop")) {
            engine.stopSearch();
         }

         else if (line.equals("quit")) {
            engine.stopSearch();
            break;
         }
      }
   }

   private void handlePosition(String cmd) {
      String rest = cmd.substring("position".length()).trim();

      if (rest.startsWith("startpos")) {
         engine.setToStartpos();
         rest = rest.substring("startpos".length()).trim();

         if (rest.startsWith("moves")) {
            String[] moves = rest.substring(5).trim().split("\\s+");
            for (String m : moves) engine.makeMoveFromUci(m);
         }
      }

      else if (rest.startsWith("fen")) {
         rest = rest.substring(3).trim();
         String fen;
         String movesPart = null;

         int idx = rest.indexOf(" moves ");
         if (idx != -1) {
            fen = rest.substring(0, idx).trim();
            movesPart = rest.substring(idx + 7).trim();
         } else {
            fen = rest.trim();
         }

         engine.setFromFen(fen);

         if (movesPart != null) {
            for (String m : movesPart.split("\\s+")) engine.makeMoveFromUci(m);
         }
      }
   }

   private void handleGo(String cmd) {
      int wtime = -1, btime = -1;
      int winc = 0, binc = 0;
      int movetime = -1;
      int movestogo = -1;
      int depth = -1;
      int nodes = -1;

      String[] parts = cmd.split("\\s+");
      for (int i = 0; i < parts.length; i++) {
         switch (parts[i]) {
            case "wtime": wtime = Integer.parseInt(parts[++i]); break;
            case "btime": btime = Integer.parseInt(parts[++i]); break;
            case "winc":  winc  = Integer.parseInt(parts[++i]); break;
            case "binc":  binc  = Integer.parseInt(parts[++i]); break;
            case "movetime": movetime = Integer.parseInt(parts[++i]); break;
            case "movestogo": movestogo = Integer.parseInt(parts[++i]); break;
            case "depth": depth = Integer.parseInt(parts[++i]); break;
            case "nodes": nodes = Integer.parseInt(parts[++i]); break;
         }
      }

      timeManager.setParameters(wtime, btime, winc, binc, movetime, movestogo, depth, nodes);
      engine.startSearch(timeManager);
   }

   public static void main(String[] args) throws Exception {
      new MinimalUci().run();
   }
}