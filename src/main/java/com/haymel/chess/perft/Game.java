package com.haymel.chess.perft;

public final class Game {

    public int from;
    public int to;
    public Piece promote;
    public int capturePiece;
    public final Castling castle = new Castling();

}
