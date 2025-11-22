package com.haymel.chess.perft;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    NORTH(0),
    NE(1),
    EAST(2),
    SE(3),
    SOUTH(4),
    SW(5),
    WEST(6),
    NW(7);

    private final int code;

    Direction(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    // Optional: reverse lookup by int
    private static final Map<Integer, Direction> LOOKUP = new HashMap<>();
    static {
        for (Direction d : Direction.values()) {
            LOOKUP.put(d.getCode(), d);
        }
    }

    public static Direction fromCode(int code) {
        return LOOKUP.get(code);
    }
}