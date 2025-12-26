package com.haymel.chess.perft.move;

import com.haymel.chess.perft.VerifyChess;

import java.util.ArrayList;
import java.util.List;

public class FenProvider {

   public static List<String> fens() {
      List<String> list = new ArrayList<>();

      String[] boards = {
         "8/8/8/8/8/8/8/8",
         "8/8/8/8/8/8/8/R7",
         "8/8/8/8/8/8/8/K6k",
         "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR",
         "r3k2r/8/8/8/8/8/8/R3K2R",
         "8/8/8/3pP3/8/8/8/8",
         "8/8/8/3Pp3/8/8/8/8",
         "r1bqkbnr/pppp1ppp/2n5/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R",
         "r2q1rk1/pp1nbppp/2p1bn2/3p4/3P4/2NBPN2/PPQ2PPP/R1B2RK1"
      };

      String[] sides = { "w", "b" };

      String[] castles = {
         "-", "K", "Q", "k", "q",
         "KQ", "kq", "Kk", "Qq",
         "KQkq"
      };

      String[] enPassant = {
         "-", "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
         "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6"
      };

      for (String board : boards) {
         for (String side : sides) {
            for (String castle : castles) {
               for (String ep : enPassant) {
                  for (int half = 0; half <= 10; half++) {
                     for (int full = 1; full <= 5; full++) {

                        // EP nur erlauben, wenn es auf dem Brett möglich ist
                        if (!isValidEnPassant(board, side, ep)) continue;
                        String fen = String.format(
                           "%s %s %s %s %d %d",
                           board,
                           side,
                           castle,
                           ep,
                           half,
                           full
                        );
                        if (new VerifyChess(fen).isOk())
                           list.add(fen);
                     }
                  }
               }
            }
         }
      }
      return list;
   }

   private static boolean isValidEnPassant(String board, String side, String ep) {
      if (ep.equals("-")) return true;

      int file = ep.charAt(0) - 'a';
      int rank = ep.charAt(1) - '1';

      // Seite am Zug muss EP schlagen können
      if (side.equals("w") && rank != 5) return false; // w schlägt auf Rank 6 → EP square rank=5
      if (side.equals("b") && rank != 2) return false; // b schlägt auf Rank 3 → EP square rank=2

      // Rank hinter dem EP square
      int behindRank = (rank == 2) ? 3 : (rank == 5 ? 4 : -1);
      if (behindRank == -1) return false;

      // Brett in Ranks splitten
      String[] ranks = board.split("/");
      String row = ranks[7 - behindRank];

      int col = 0;
      for (char c : row.toCharArray()) {
         if (Character.isDigit(c)) {
            col += c - '0';
         } else {
            if (col == file) {
               if (rank == 2 && c == 'P') return true; // Weißer Bauer auf Rank 4
               if (rank == 5 && c == 'p') return true; // Schwarzer Bauer auf Rank 5
               return false;
            }
            col++;
         }
      }
      return false;
   }

}
