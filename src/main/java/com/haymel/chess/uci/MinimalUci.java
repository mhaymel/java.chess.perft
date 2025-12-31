package com.haymel.chess.uci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//copilot
public class MinimalUci {

   private final Engine engine = new Engine(); // Deine Engine-Klasse

   public void run() throws IOException {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      while (true) {
         String line = in.readLine();
         if (line == null) continue;

         line = line.trim();
         if (line.isEmpty()) continue;

         if (line.equals("uci")) {
            System.out.println("id name MyEngine");
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
            parsePosition(line);
         }

         else if (line.startsWith("go")) {
            String bestMove = engine.searchBestMove();
            System.out.println("bestmove " + bestMove);
         }

         else if (line.equals("stop")) {
            // Bei minimaler Engine: sofort bestmove ausgeben
            String bestMove = engine.getCurrentBestMove();
            System.out.println("bestmove " + bestMove);
         }

         else if (line.equals("quit")) {
            return;
         }
      }
   }

   private void parsePosition(String cmd) {
      if (cmd.startsWith("position startpos")) {
         engine.setToStartpos();

         int idx = cmd.indexOf("moves");
         if (idx != -1) {
            String moves = cmd.substring(idx + 6).trim();
            for (String m : moves.split(" ")) {
               engine.makeMove(m);
            }
         }
      }

      else if (cmd.startsWith("position fen")) {
         String fen = cmd.substring("position fen".length()).trim();

         int idx = fen.indexOf("moves");
         if (idx != -1) {
            String fenPart = fen.substring(0, idx).trim();
            engine.setFromFen(fenPart);

            String moves = fen.substring(idx + 6).trim();
            for (String m : moves.split(" ")) {
               engine.makeMove(m);
            }
         } else {
            engine.setFromFen(fen);
         }
      }
   }

   public static void main(String[] args) throws Exception {
      new MinimalUci().run();
   }
}