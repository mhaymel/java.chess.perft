package com.haymel.chess.perft;

import static com.haymel.chess.perft.Move.NewMove;

public final class Game {

    public int from;
    public int to;
    public int promote;
    public int capturePiece;
    public final Castling castle = new Castling();
    public int enPassantField;

   @Override
   public String toString() {
      return "Game{ move =" + NewMove(from, to, promote) +
         ", capturePiece=" + capturePiece +
         ", castle=" + castle +
         ", enPassantField=" + enPassantField +
         '}';
   }
}
