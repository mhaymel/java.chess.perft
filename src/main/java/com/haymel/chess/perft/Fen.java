package com.haymel.chess.perft;

public final class Fen {


//   public static int LoadDiagram(String ts, Chess chess) {
//      for (int x = 0; x < 64; x++) {
//         chess.board[x] = Piece.empty;
//         chess.color[x] = Piece.empty;
//      }
//      int c = 0, i = 0, j;
//
//      while (ts) {
//         if (ts[c] >= '0' && ts[c] <= '8')
//            i += ts[c] - 48;
//         if (ts[c] == '\\')
//            continue;
//         j = Flip[i];
//
//         switch (ts[c]) {
//            case 'K':
//               board[j] = K;
//               color[j] = 0;
//               i++;
//               kingloc[0] = j;
//               break;
//            case 'Q':
//               board[j] = Q;
//               color[j] = 0;
//               i++;
//               break;
//            case 'R':
//               board[j] = R;
//               color[j] = 0;
//               i++;
//               break;
//            case 'B':
//               board[j] = B;
//               color[j] = 0;
//               i++;
//               break;
//            case 'N':
//               board[j] = N;
//               color[j] = 0;
//               i++;
//               break;
//            case 'P':
//               board[j] = P;
//               color[j] = 0;
//               i++;
//               break;
//            case 'k':
//               board[j] = K;
//               color[j] = 1;
//               i++;
//
//               kingloc[1] = j;
//               break;
//            case 'q':
//               board[j] = Q;
//               color[j] = 1;
//               i++;
//               break;
//            case 'r':
//               board[j] = R;
//               color[j] = 1;
//               i++;
//               break;
//            case 'b':
//               board[j] = B;
//               color[j] = 1;
//               i++;
//               break;
//            case 'n':
//               board[j] = N;
//               color[j] = 1;
//               i++;
//               break;
//            case 'p':
//               board[j] = P;
//               color[j] = 1;
//               i++;
//               break;
//         }
//         c++;
//         if (ts[c] == ' ')
//            break;
//         if (i > 63)
//            break;
//      }
//      if (ts[c] == ' ' && ts[c + 2] == ' ') {
//         if (ts[c + 1] == 'w') {
//            side = 0;
//            xside = 1;
//         }
//         if (ts[c + 1] == 'b') {
//            side = 1;
//            xside = 0;
//         }
//      }
//
//      game_list[0].castle_q[0] = 0;
//      game_list[0].castle_q[1] = 0;
//      game_list[0].castle_k[0] = 0;
//      game_list[0].castle_k[1] = 0;
//
//      while (ts[c]) {
//         switch (ts[c]) {
//            case '-':
//               break;
//            case 'K':
//               if (board[E1] == 5 && color[E1] == 0) game_list[0].castle_q[0] = 1;
//               break;
//            case 'Q':
//               if (board[E1] == 5 && color[E1] == 0) game_list[0].castle_q[1] = 1;
//               break;
//            case 'k':
//               if (board[E8] == 5 && color[E8] == 1) game_list[0].castle_k[0] = 1;
//               break;
//            case 'q':
//               if (board[E8] == 5 && color[E8] == 1) game_list[0].castle_k[1] = 1;
//               break;
//            default:
//               break;
//         }
//         c++;
//      }
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
//      printf(" %s \n", ts);
//      return 0;
//   }
}
