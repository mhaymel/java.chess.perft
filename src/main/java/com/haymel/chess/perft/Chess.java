package com.haymel.chess.perft;

import static com.haymel.chess.perft.Color.*;
import static com.haymel.chess.perft.Piece.king;
import static com.haymel.chess.perft.Piece.pawn;

public final class Chess {

   public static final int maxPly = 128;
   public static final int moveStackSize = 2000;
   public static final int gameStackSize = 2000;
   public final Move[] moveList = newMove(moveStackSize);
   public final int[] firstMove = new int[maxPly];
   public final Game[] gameList = newGame(gameStackSize);
   public final int[] board = new int[64];
   public final int[] color = new int[64];
   public final int[] kingloc = new int[2];
   public int hplyOffset;
   public int ply;
   public int hply;
   public int side = white;
   public int fiftyMoveCounter;
   public int mc;

   public static int other(int side) {
      return side ^ 1;
   }

   private static Move[] newMove(int size) {
      Move[] moves = new Move[size];
      for (int i = 0; i < moves.length; i++)
         moves[i] = new Move();
      return moves;
   }

   private static Game[] newGame(int size) {
      Game[] game = new Game[size];
      for (int i = 0; i < game.length; i++)
         game[i] = new Game();
      return game;
   }

   public void emptyBoard() {
      for (int x = 0; x < 64; ++x) {
         color[x] = empty;
         board[x] = empty;
      }

      side = -1;
      hplyOffset = 0;
      ply = 0;
      hply = 0;
      fiftyMoveCounter = 0;
      firstMove[0] = 0;
      kingloc[white] = Field.invalid;
      kingloc[black] = Field.invalid;
      gameList[hply].enPassantField = Field.invalid;
      mc = 0;

      gameList[hply].castle.kingside[white] = false;
      gameList[hply].castle.queenside[white] = false;
      gameList[hply].castle.kingside[black] = false;
      gameList[hply].castle.queenside[black] = false;

   }

   public boolean itsWhitesTurn() {
      return side == white;
   }

   public boolean isOpponent(int field) {
      return color[field] == other(side);
   }

   public int otherSide() {
      return other(side);
   }

   public boolean isEmpty(int field) {
      return board[field] == Piece.empty;
   }

   public boolean isEmptyOrOpponent(int field) {
      return isEmpty(field) || isOpponent(field);
   }

   public boolean isWhitePawn(int field) {
      return color[field] == white && isPawn(field);
   }

   public boolean isBlackPawn(int field) {
      return color[field] == black && isPawn(field);
   }

   public boolean isPawn(int field) {
      return board[field] == pawn;
   }

   public boolean queenSideCastling(int color) {
      return gameList[hply].castle.queenside[color];
   }

   public boolean kingSideCastling(int color) {
      return gameList[hply].castle.kingside[color];
   }

   public boolean isKing(int from) {
      return board[from] == king;
   }

   @Override
   public String toString() {
      return "Chess{ " + Fen.toFen(this) + " }";
   }

   public int fullMoveNumber() {
      return (hplyOffset + hply + ply) / 2 + 1;
   }

   public int moveCount() {
      return firstMove[ply + 1] - firstMove[ply];
   }

   public Move move(int n) {
      int index = firstMove[ply] + n;
      if (index < firstMove[ply] || index >= firstMove[ply + 1]) {
         throw new IndexOutOfBoundsException("Move index out of range: " + n);
      }
      return moveList[index];
   }
}
