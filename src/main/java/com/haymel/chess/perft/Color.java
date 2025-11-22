package com.haymel.chess.perft;

public enum Color {
    white(0),
    black(1),
    empty(6);

    private final int value;

    Color(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Color fromValue(int value) {
        for (Color c : Color.values()) {
            if (c.value == value) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}

