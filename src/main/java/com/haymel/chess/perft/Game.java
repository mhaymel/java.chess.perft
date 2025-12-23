package com.haymel.chess.perft;

public final class Game {

    public int from;
    public int to;
    public int promote;
    public int capturePiece;
    public final Castling castle = new Castling();
    public int enPassantField;

}
