package com.haymel.chess.perft;

import static com.haymel.chess.perft.Color.*;
import static com.haymel.chess.perft.File.*;
import static com.haymel.chess.perft.File.D;
import static com.haymel.chess.perft.File.E;
import static com.haymel.chess.perft.File.F;
import static com.haymel.chess.perft.File.G;
import static com.haymel.chess.perft.File.H;
import static com.haymel.chess.perft.Globals.*;
import static com.haymel.chess.perft.Piece.*;

public final class Init {

    public static int ply;
    public static int hply;
    public static Color side = white;
    public static Color xside = black;

    public static final Move[] moveList = newMove(MOVE_STACK);
    public static final int[] firstMove = new int[maxPly];
    public static final Game[] gameList = newGame(GAME_STACK);

    public static final Piece[] board = new Piece[64];
    public static final Color[] color = new Color[64];

    public static final Color[] initColor =
            {
                    white, white, white, white, white, white, white, white,
                    white, white, white, white, white, white, white, white,
                    empty, empty, empty, empty, empty, empty, empty, empty,
                    empty, empty, empty, empty, empty, empty, empty, empty,
                    empty, empty, empty, empty, empty, empty, empty, empty,
                    empty, empty, empty, empty, empty, empty, empty, empty,
                    black, black, black, black, black, black, black, black,
                    black, black, black, black, black, black, black, black
            };

    public static final Piece[] initBoard =
            {
                    Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook,
                    Pawn, Pawn, Pawn, Pawn, Pawn, Pawn, Pawn, Pawn,
                    Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty,
                    Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty,
                    Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty,
                    Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty,
                    Pawn, Pawn, Pawn, Pawn, Pawn, Pawn, Pawn, Pawn,
                    Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook
            };

    public static final File[] col = {
            A, B, C, D, E, F, G, H,
            A, B, C, D, E, F, G, H,
            A, B, C, D, E, F, G, H,
            A, B, C, D, E, F, G, H,
            A, B, C, D, E, F, G, H,
            A, B, C, D, E, F, G, H,
            A, B, C, D, E, F, G, H,
            A, B, C, D, E, F, G, H
    };

    public static void initBoard() {
        for (int x = 0; x < 64; ++x) {
            color[x] = initColor[x];
            board[x] = initBoard[x];
//            rank[0][x] = row[x];
//            rank[1][x] = 7 - row[x];
        }

        side = white;
        xside = black;
//        fifty = 0;
        ply = 0;
        hply = 0;
        firstMove[0] = 0;
//        kingloc[0] = Globals.E1;
//        kingloc[1] = Globals.E8;

//        game_list[hply].castle_q[0] = 1;
//        game_list[hply].castle_q[1] = 1;
//        game_list[hply].castle_k[0] = 1;
//        game_list[hply].castle_k[1] = 1;
    }

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

}
