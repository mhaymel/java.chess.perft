package com.haymel.chess.perft.move;

import com.haymel.chess.perft.Chess;
import com.haymel.chess.perft.Piece;

import static com.haymel.chess.perft.Color.black;
import static com.haymel.chess.perft.Color.white;
import static com.haymel.chess.perft.Direction.*;
import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.Init.file;
import static com.haymel.chess.perft.Init.rank;
import static com.haymel.chess.perft.move.BishopMoves.bishopMoves;
import static com.haymel.chess.perft.move.KingMoves.kingMoves;
import static com.haymel.chess.perft.move.KnightMoves.knightMoves;
import static com.haymel.chess.perft.Piece.*;

public final class Generator {

   private final Chess c;

   private static final int up = 8;
   public static final int[] singleStep = { up, -up};
   public static final int[] doubleStep = {2*up, -2*up};
   public static final int[] left = {7, -9};
   public static final int[] right = {9, -7};

   public Generator(Chess c) {
      this.c = c;
   }

   public static Generator NewGen(Chess chess) {
      return new Generator(chess);
   }

   public void execute() {
      c.mc = c.firstMove[c.ply];
      enPassant();
      castling();

      for (int from = 0; from < 64; from++) {
         if (c.color[from] == c.side) {
            switch (c.board[from]) {
               case pawn:     pawn(from);    break;
               case knight:   knight(from);  break;
               case bishop:   bishop(from);  break;
               case rook:     rook(from);    break;
               case queen:    queen(from);   break;
               case king:     king(from);    break;
               default:                      break;
            }
         }
      }
      c.firstMove[c.ply + 1] = c.mc;
   }

   private void bishop(int from) {
      GenBishop(from, NE);
      GenBishop(from, SE);
      GenBishop(from, SW);
      GenBishop(from, NW);
   }

   private void rook(int from) {
      GenRook(from, NORTH);
      GenRook(from, EAST);
      GenRook(from, SOUTH);
      GenRook(from, WEST);
   }

   private void queen(int from) {
      GenQueen(from, NE);
      GenQueen(from, SE);
      GenQueen(from, SW);
      GenQueen(from, NW);
      GenQueen(from, NORTH);
      GenQueen(from, EAST);
      GenQueen(from, SOUTH);
      GenQueen(from, WEST);
   }

   private void enPassant() {
      if (isInvalid(c.enPassantField)) return;
      if (c.side == white) {
         if (c.enPassantField > a6 && isWhitePawn(c.enPassantField - 9)) addMove(c.enPassantField - 9, c.enPassantField);
         if (c.enPassantField < h6 && isWhitePawn(c.enPassantField - 7)) addMove(c.enPassantField - 7, c.enPassantField);
      } else {
         if (c.enPassantField > a3 && isBlackPawn(c.enPassantField + 7)) addMove(c.enPassantField + 7, c.enPassantField);
         if (c.enPassantField < h3 && isBlackPawn(c.enPassantField + 9)) addMove(c.enPassantField + 9, c.enPassantField);
      }
   }

   private void knight(int from) {
      knightOrKing(from, knightMoves);
   }

   private void king(int from) {
      knightOrKing(from, kingMoves);
   }

   private void castling() {
      if (itsWhitesTurn()) {
         if (kingSideCastling(white) && isEmpty(f1) && isEmpty(g1)) addMove(e1, g1);
         if (queenSideCastling(white) && isEmpty(d1) && isEmpty(c1) && isEmpty(b1)) addMove(e1, c1);
      } else {
         if (kingSideCastling(black) && isEmpty(f8) && isEmpty(g8)) addMove(e8, g8);
         if (queenSideCastling(black) && isEmpty(d8) && isEmpty(c8) && isEmpty(b8)) addMove(e8, c8);
      }
   }

   private void pawn(int from) {
      if (isEmpty(from + singleStep[c.side])) {
         addPawnMove(from, from + singleStep[c.side]);
         if (rank[c.side][from] == 1 && isEmpty(from + doubleStep[c.side])) addMove(from, from + doubleStep[c.side]);
      }
      if (file[from] > 0 && isOpponent(from + left[c.side])) addPawnMove(from, from + left[c.side]);
      if (file[from] < 7 && isOpponent(from + right[c.side])) addPawnMove(from, from + right[c.side]);
   }

   public void GenBishop(int from, int direction) {
//      int to = bishopMoves[from][direction];
//      while (to > -1) {
//         if (c.color[to] != empty) {
//            if (c.color[to] == c.xside)
//               addMove(from, to);
//            break;
//         }
//         addMove(from, to);
//         to = bishopMoves[to][direction];
//      }
   }

   /*
   GenRook generates straight moves and captures
   for each rank and file.
   */
   public static void GenRook(int x, int dir) {
//      int sq = qrb_moves[x][dir];
//      while (sq > -1) {
//         if (color[sq] != EMPTY) {
//            if (color[sq] == xside) {
//               AddCapture(x, sq, rx[board[sq]]);
//            }
//            break;
//         }
//         AddMove(x, sq);
//         sq = qrb_moves[sq][dir];
//      }
   }

   /*
   GenQueen generates queen moves and captures
   for line.
   */
   public static void GenQueen(int x, int dir) {
//      int sq = qrb_moves[x][dir];
//      while (sq > -1) {
//         if (color[sq] != EMPTY) {
//            if (color[sq] == xside) {
//               AddCapture(x, sq, qx[board[sq]]);
//            }
//            break;
//         }
//         AddMove(x, sq);
//         sq = qrb_moves[sq][dir];
//      }
   }

   private boolean queenSideCastling(int color) {
      return c.gameList[c.hply].castle.queenside[color];
   }

   private boolean kingSideCastling(int color) {
      return c.gameList[c.hply].castle.kingside[color];
   }

   private void addMove(int from, int to) {
      c.moveList[c.mc].start = from;
      c.moveList[c.mc].dest = to;
      c.moveList[c.mc].promotion = empty;
      c.mc++;
   }

   private void addPawnMove(int from, int to) {
      if (to >= a8 || to <= h1) {
         addPromotion(from, to, queen);
         addPromotion(from, to, rook);
         addPromotion(from, to, bishop);
         addPromotion(from, to, knight);
      }
      else
         addMove(from, to);
   }

   private void addPromotion(int from, int to, int piece) {
      c.moveList[c.mc].start = from;
      c.moveList[c.mc].dest = to;
      c.moveList[c.mc].promotion = piece;
      c.mc++;
   }

   private void knightOrKing(int from, int[][] moves) {
      int to = moves[from][NORTH];
      for(int direction = NORTH + 1; isValid(to); direction++) {
         if (isEmptyOrOpponent(to)) addMove(from, to);
         to = moves[from][direction];
      }
   }

   private boolean itsWhitesTurn() { return c.side == white; }

   private boolean isOpponent(int field) { return c.color[field] == c.xside; }

   private boolean isEmpty(int field) {
      return c.board[field] == Piece.empty;
   }

   private boolean isEmptyOrOpponent(int field) {
      return c.color[field] == c.xside || c.board[field] == Piece.empty;
   }

   private boolean isWhitePawn(int field) {
      return c.color[field] == white && c.board[field] == pawn;
   }

   private boolean isBlackPawn(int field) {
      return c.color[field] == black && c.board[field] == pawn;
   }

}
