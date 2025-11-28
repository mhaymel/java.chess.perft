package com.haymel.chess.perft;

import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Color.white;
import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.Piece.*;
import static java.lang.Character.isUpperCase;

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
   public static int load(String fen, Chess chess, Castling castling, EnpassantHalfFullMove enpHalfFullMove) {
      return load(fen.toCharArray(), chess, castling, enpHalfFullMove);
   }

   private static int load(char[] fen, Chess chess, Castling castling, EnpassantHalfFullMove enpHalfFullMove) {
      for (int x = 0; x < 64; x++) {
         chess.board[x] = Piece.empty;
         chess.color[x] = Piece.empty;
      }
      int c = 0;
      int i = 0;
      int j;

      while (true) {
         if (fen[c] >= '0' && fen[c] <= '8')
            i += fen[c] - 48;
         if (fen[c] == '/')
            continue;
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
         }
         chess.color[j] = color(fen[c]);

         c++;
         if (fen[c] == ' ')
            break;
         if (i > 63)
            break;
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

      castling.reset();

      while (fen[c]) {
         switch (fen[c]) {
            case '-':
               break;
            case 'K':
            case 'k':
               castling.kingside[color(fen[c])] = true;
               break;
            case 'Q':
            case 'q':
               castling.queenside[color(fen[c])] = true;
               break;
            default:
               break;
         }
         c++;
      }
//
//      CloseDiagram();
//      DisplayBoard();
//      NewPosition();
//      Gen();
//      printf(" diagram # %d \n", num + count);
//      count++;
//      if (side == 0)
//         printf("White to move\n");
//      else
//         printf("Black to move\n");
//      printf(" %s \n", fen);
//      return 0;
   }

   private static int color(char piece) {
      return isUpperCase(piece) ? white : black;
   }
}
