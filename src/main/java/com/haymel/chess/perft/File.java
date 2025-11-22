package com.haymel.chess.perft;

public enum File {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);

    private final int index;

    File(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    // Reverse lookup: int → File
    public static File fromIndex(int index) {
        for (File f : File.values()) {
            if (f.index == index) {
                return f;
            }
        }
        throw new IllegalArgumentException("Invalid file index: " + index);
    }
}

