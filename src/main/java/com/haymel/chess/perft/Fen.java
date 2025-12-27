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
   public static void load(String fen, Chess chess) {
      load(fen.toCharArray(), chess);
   }

   public static Chess load(String fen) {
      Chess chess = new Chess();
      load(fen.toCharArray(), chess);
      return chess;
   }

   private static void load(char[] fen, Chess chess) {
      chess.emptyBoard();
      chess.ply = 0;
      chess.hply = 0;
      chess.side = -1;
      for (int x = 0; x < 64; x++) {
         chess.board[x] = empty;
         chess.color[x] = empty;
      }
      chess.gameList[chess.hply].fullMoveNumber = 0;
      chess.gameList[chess.hply].halfMoveClock = 0;

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
         }
         if (fen[c + 1] == 'b') {
            chess.side = black;
         }
      }
      c += 2;

      while (isWhitespace(fen[c]))
         c++;

      Castling castle = chess.gameList[chess.hply].castle;
      castle.reset();
      if (fen[c] == '-') {
         c++;
      } else {
         while (!isWhitespace(fen[c])) {

            switch (fen[c]) {
               case 'K':
               case 'k':
                  castle.kingside[color(fen[c])] = true;
                  break;
               case 'Q':
               case 'q':
                  castle.queenside[color(fen[c])] = true;
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
         chess.gameList[chess.hply].enPassantField = fen[c] - 'a' + (fen[c+1] - '1')*8;
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
      chess.gameList[chess.hply].halfMoveClock = count;

      while (isWhitespace(fen[c]))
         c++;

      count = 0;
      while (c < fen.length && isDigit(fen[c])) {
         count = count * 10 + fen[c] - '0';
         c++;
      }
      chess.gameList[chess.hply].fullMoveNumber = count;
   }

   private static boolean isDigit(char c) {
      return c >= '0' && c <= '9';
   }

   private static int color(char piece) {
      return isUpperCase(piece) ? white : black;
   }

   public static String toFen(Chess chess) {
      StringBuilder fen = new StringBuilder();

      // 1. Piece placement (rank 8 to rank 1)
      for (int rank = 7; rank >= 0; rank--) {
         int emptyCount = 0;

         for (int file = 0; file < 8; file++) {
            int sq = rank * 8 + file;
            int piece = chess.board[sq];

            if (piece == empty) {
               emptyCount++;
            } else {
               if (emptyCount > 0) {
                  fen.append(emptyCount);
                  emptyCount = 0;
               }
               fen.append(pieceToChar(piece, chess.color[sq]));
            }
         }

         if (emptyCount > 0) {
            fen.append(emptyCount);
         }

         if (rank > 0) {
            fen.append('/');
         }
      }

      // 2. Side to move
      fen.append(' ');
      fen.append(chess.side == white ? 'w' : 'b');

      // 3. Castling rights
      fen.append(' ');
      Castling castle = chess.gameList[chess.hply].castle;
      boolean anyCastle = false;

      if (castle.kingside[white]) {
         fen.append('K');
         anyCastle = true;
      }
      if (castle.queenside[white]) {
         fen.append('Q');
         anyCastle = true;
      }
      if (castle.kingside[black]) {
         fen.append('k');
         anyCastle = true;
      }
      if (castle.queenside[black]) {
         fen.append('q');
         anyCastle = true;
      }

      if (!anyCastle) {
         fen.append('-');
      }

      // 4. En passant
      fen.append(' ');
      if (chess.gameList[chess.hply].enPassantField == invalid) {
         fen.append('-');
      } else {
         int file = chess.gameList[chess.hply].enPassantField % 8;
         int rank = chess.gameList[chess.hply].enPassantField / 8;
         fen.append((char) ('a' + file));
         fen.append((char) ('1' + rank));
      }

      // 5. Halfmove clock
      fen.append(' ');
      fen.append(chess.gameList[chess.hply].halfMoveClock);

      // 6. Fullmove number
      fen.append(' ');
      fen.append(chess.gameList[chess.hply].fullMoveNumber);

      return fen.toString();
   }

   private static char pieceToChar(int piece, int color) {
      char c;
      switch (piece) {
         case king:   c = 'k'; break;
         case queen:  c = 'q'; break;
         case rook:   c = 'r'; break;
         case bishop: c = 'b'; break;
         case knight: c = 'n'; break;
         case pawn:   c = 'p'; break;
         default:
            throw new IllegalArgumentException("Unknown piece: " + piece);
      }
      return color == white ? Character.toUpperCase(c) : c;
   }

}
