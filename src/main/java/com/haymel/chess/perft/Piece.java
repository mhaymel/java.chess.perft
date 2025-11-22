package com.haymel.chess.perft;

public enum Piece {
    Pawn(0),
    Knight(1),
    Bishop(2),
    Rook(3),
    Queen(4),
    King(5),
    Empty(6);

    private final int value;

    Piece(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Piece fromValue(int value) {
        for (Piece p : Piece.values()) {
            if (p.value == value) {
                return p;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}