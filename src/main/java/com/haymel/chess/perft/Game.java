package com.haymel.chess.perft;

public final class Game {

    public int start;
    public int dest;
    public Piece promote;
    public Piece capture;
    public final Castling castle = new Castling();

}
