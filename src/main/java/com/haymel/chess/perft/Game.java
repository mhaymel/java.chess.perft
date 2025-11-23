package com.haymel.chess.perft;

public final class Game {

    public int start;
    public int dest;
    public Piece promote;
    public Piece capture;
    public final int[] castle_q = new int[2];
    public final int[] castle_k = new int[2];

}
