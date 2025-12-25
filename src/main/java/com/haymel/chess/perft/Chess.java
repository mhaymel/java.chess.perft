package com.haymel.chess.perft;

import static com.haymel.chess.perft.Color.*;
import static com.haymel.chess.perft.Field.e1;
import static com.haymel.chess.perft.Field.e8;
import static com.haymel.chess.perft.Piece.king;
import static com.haymel.chess.perft.Piece.pawn;

public final class Chess {

   public int ply;
   public int hply;
   public int side = white;
   public int enPassantField;
   public int mc;

   public static final int maxPly = 128;
   public static final int moveStackSize = 2000;
   public static final int gameStackSize = 2000;

   public final Move[] moveList = newMove(moveStackSize);
   public final int[] firstMove = new int[maxPly];
   public final Game[] gameList = newGame(gameStackSize);
   public final int[] board = new int[64];
   public final int[] color = new int[64];
   public final int[] kingloc = new int[2];
   public final HalfFullMove halfFullMove = new HalfFullMove();

   public void emptyBoard() {
      for (int x = 0; x < 64; ++x) {
         color[x] = empty;
         board[x] = empty;
      }

      side = white;
      ply = 0;
      hply = 0;
      firstMove[0] = 0;
      kingloc[white] = Field.invalid;
      kingloc[black] = Field.invalid;
      enPassantField = Field.invalid;
      mc = 0;

      gameList[hply].castle.kingside[white] = false;
      gameList[hply].castle.queenside[white] = false;
      gameList[hply].castle.kingside[black] = false;
      gameList[hply].castle.queenside[black] = false;

      halfFullMove.halfMoveClock = 0;
      halfFullMove.fullMoveNumber = 0;
   }

   public void initBoard() {
      for (int x = 0; x < 64; ++x) {
         color[x] = Init.initColor[x];
         board[x] = Init.initBoard[x];
      }

      side = white;
      ply = 0;
      hply = 0;
      firstMove[0] = 0;
      kingloc[white] = e1;
      kingloc[black] = e8;
      enPassantField = Field.invalid;
      mc = 0;

      gameList[hply].castle.kingside[white] = true;
      gameList[hply].castle.queenside[white] = true;
      gameList[hply].castle.kingside[black] = true;
      gameList[hply].castle.queenside[black] = true;

      halfFullMove.halfMoveClock = 0;
      halfFullMove.fullMoveNumber = 0;
   }

   public boolean itsWhitesTurn() { return side == white; }

   public boolean isOpponent(int field) {
      return color[field] == other(side);
   }

   public static int other(int side) { return side^1; }

   public  int otherSide() { return other(side); }

   public boolean isEmpty(int field) { return board[field] == Piece.empty; }

   public boolean isEmptyOrOpponent(int field) { return isEmpty(field) || isOpponent(field); }

   public boolean isWhitePawn(int field) { return color[field] == white && isPawn(field); }

   public boolean isBlackPawn(int field) { return color[field] == black && isPawn(field); }

   public boolean isPawn(int field) { return board[field] == pawn; }

   public boolean queenSideCastling(int color) { return gameList[hply].castle.queenside[color]; }

   public boolean kingSideCastling(int color) { return gameList[hply].castle.kingside[color]; }

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

   public boolean isKing(int from) { return board[from] == king; }

}
