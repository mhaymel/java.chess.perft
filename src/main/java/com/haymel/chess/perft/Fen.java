package com.haymel.chess.perft;

import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Color.white;
import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.Piece.*;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.isWhitespace;
import static java.lang.String.format;

public final class Fen {

   private static final int[] flip = {
      a8, b8, c8, d8, e8, f8, g8, h8,
      a7, b7, c7, d7, e7, f7, g7, h7,
      a6, b6, c6, d6, e6, f6, g6, h6,
      a5, b5, c5, d5, e5, f5, g5, h5,
      a4, b4, c4, d4, e4, f4, g4, h4,
      a3, b3, c3, d3, e3, f3, g3, h3,
      a2, b2, c2, d2, e2, f2, g2, h2,
      a1, b1, c1, d1, e1, f1, g1, h1
   };

   /*
      https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation
      rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
    */
   public static void load(String fen, Chess chess, Castling castling, EnpassantHalfFullMove enpHalfFullMove) {
      load(fen.toCharArray(), chess, castling, enpHalfFullMove);
   }

   private static void load(char[] fen, Chess chess, Castling castling, EnpassantHalfFullMove enpHalfFullMove) {
      for (int x = 0; x < 64; x++) {
         chess.board[x] = empty;
         chess.color[x] = empty;
      }
      int c = 0;
      int i = 0;
      int j;

      while (!isWhitespace(fen[c])) {
         if (fen[c] >= '0' && fen[c] <= '8') {
            i += fen[c] - 48;
            c++;
            continue;
         }

         if (fen[c] == '/') {
            c++;
            continue;
         }

         j = flip[i];

         switch (fen[c]) {
            case 'K':
            case 'k':
               chess.board[j] = king;
               chess.kingloc[color(fen[c])] = j;
               break;
            case 'Q':
            case 'q':
               chess.board[j] = queen;
               break;
            case 'R':
            case 'r':
               chess.board[j] = rook;
               break;
            case 'B':
            case 'b':
               chess.board[j] = bishop;
               break;
            case 'N':
            case 'n':
               chess.board[j] = knight;
               break;
            case 'P':
            case 'p':
               chess.board[j] = pawn;
               break;
            default:
               throw new IllegalArgumentException(format("cannot handle fen '%s'", String.valueOf(fen)));
         }
         chess.color[j] = color(fen[c]);
         i++;
         c++;
      }
      if (fen[c] == ' ' && fen[c + 2] == ' ') {
         if (fen[c + 1] == 'w') {
            chess.side = white;
            chess.xside = black;
         }
         if (fen[c + 1] == 'b') {
            chess.side = black;
            chess.xside = white;
         }
      }
      c += 2;

      while (isWhitespace(fen[c]))
         c++;

      castling.reset();
      if (fen[c] == '-') {
         c++;
      } else {
         while (!isWhitespace(fen[c])) {
            switch (fen[c]) {
               case 'K':
               case 'k':
                  castling.kingside[color(fen[c])] = true;
                  break;
               case 'Q':
               case 'q':
                  castling.queenside[color(fen[c])] = true;
                  break;
               default:
                  throw new IllegalArgumentException(
                     format("cannot handle '%s' at '%s' in fen '%s'", fen[c], c, String.valueOf(fen)));
            }
            c++;
         }
      }

      while (isWhitespace(fen[c]))
         c++;

      if (fen[c] >= 'a' && fen[c] <= 'h') {
         enpHalfFullMove.enpassantField = fen[c + 1] - '1' * 8 + fen[c] - 'a';
         c = c + 2;
      }

      if (fen[c] == '-')
         c++;

      while (isWhitespace(fen[c]))
         c++;

      int count = 0;
      while (isDigit(fen[c])) {
         count = count * 10 + fen[c] - '0';
         c++;
      }
      enpHalfFullMove.halfmoveClock = count;

      while (isWhitespace(fen[c]))
         c++;

      count = 0;
      while (c < fen.length && isDigit(fen[c])) {
         count = count * 10 + fen[c] - '0';
         c++;
      }
      enpHalfFullMove.fullmoveNumber = count;
   }

   private static boolean isDigit(char c) {
      return c >= '0' && c <= '9';
   }

   private static int color(char piece) {
      return isUpperCase(piece) ? white : black;
   }
}
