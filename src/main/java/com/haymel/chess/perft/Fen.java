package com.haymel.chess.perft;

import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Color.white;
import static com.haymel.chess.perft.Piece.*;

public final class Fen {


   //   rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
   public static int LoadDiagram(char[] ts, Chess chess) {
      for (int x = 0; x < 64; x++) {
         chess.board[x] = Piece.empty;
         chess.color[x] = Piece.empty;
      }
      int c = 0;
      int i = 0;
      int j;

      while (true) {
         if (ts[c] >= '0' && ts[c] <= '8')
            i += ts[c] - 48;
         if (ts[c] == '\\')
            continue;
         j = Flip[i];

         switch (ts[c]) {
            case 'K':
               chess.board[j] = king;
               chess.color[j] = white;
               i++;
               kingloc[0] = j;
               break;
            case 'Q':
               chess.board[j] = queen;
               chess.color[j] = white;
               i++;
               break;
            case 'R':
               chess.board[j] = rook;
               chess.color[j] = white;
               i++;
               break;
            case 'B':
               chess.board[j] = bishop;
               chess.color[j] = white;
               i++;
               break;
            case 'N':
               chess.board[j] = knight;
               chess.color[j] = white;
               i++;
               break;
            case 'P':
               chess.board[j] = pawn;
               chess.color[j] = white;
               i++;
               break;
            case 'k':
               chess.board[j] = king;
               chess.color[j] = black;
               i++;

               kingloc[1] = j;
               break;
            case 'q':
               chess.board[j] = queen;
               chess.color[j] = black;
               i++;
               break;
            case 'r':
               chess.board[j] = rook;
               chess.color[j] = black;
               i++;
               break;
            case 'b':
               chess.board[j] = bishop;
               chess.color[j] = black;
               i++;
               break;
            case 'n':
               chess.board[j] = knight;
               chess.color[j] = black;
               i++;
               break;
            case 'p':
               chess.board[j] = pawn;
               chess.color[j] = black;
               i++;
               break;
         }
         c++;
         if (ts[c] == ' ')
            break;
         if (i > 63)
            break;
      }
      if (ts[c] == ' ' && ts[c + 2] == ' ') {
         if (ts[c + 1] == 'w') {
            chess.side = white;
            chess.xside = black;
         }
         if (ts[c + 1] == 'b') {
            chess.side = black;
            chess.xside = white;
         }
      }

      game_list[0].castle_q[0] = 0;
      game_list[0].castle_q[1] = 0;
      game_list[0].castle_k[0] = 0;
      game_list[0].castle_k[1] = 0;

      while (ts[c]) {
         switch (ts[c]) {
            case '-':
               break;
            case 'K':
               if (board[E1] == 5 && color[E1] == 0) game_list[0].castle_q[0] = 1;
               break;
            case 'Q':
               if (board[E1] == 5 && color[E1] == 0) game_list[0].castle_q[1] = 1;
               break;
            case 'k':
               if (board[E8] == 5 && color[E8] == 1) game_list[0].castle_k[0] = 1;
               break;
            case 'q':
               if (board[E8] == 5 && color[E8] == 1) game_list[0].castle_k[1] = 1;
               break;
            default:
               break;
         }
         c++;
      }

      CloseDiagram();
      DisplayBoard();
      NewPosition();
      Gen();
      printf(" diagram # %d \n", num + count);
      count++;
      if (side == 0)
         printf("White to move\n");
      else
         printf("Black to move\n");
      printf(" %s \n", ts);
      return 0;
   }
}
