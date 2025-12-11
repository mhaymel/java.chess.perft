package com.haymel.chess.perft.moves;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.moves.Moves.*;
import static com.haymel.chess.perft.moves.TestUtil.m;
import static org.junit.jupiter.params.provider.Arguments.of;

public final class KnightMoves {


   public static Stream<Arguments> knightMoves() {
      return Stream.of(
         /* [1] */ of(a1, m(a1c2, a1b3)),
         /* [2] */ of(a2, m(a2c3, a2c1, a2b4)),
         /* [3] */ of(a3, m(a3c4, a3c2, a3b5, a3b1)),
         /* [4] */ of(a4, m(a4c5, a4c3, a4b6, a4b2)),
         /* [5] */ of(a5, m(a5c6, a5c4, a5b7, a5b3)),
         /* [6] */ of(a6, m(a6c7, a6c5, a6b8, a6b4)),
         /* [7] */ of(a7, m(a7c8, a7c6, a7b5)),
         /* [8] */ of(a8, m(a8c7, a8b6)),
         /* [9] */ of(b1, m(b1d2, b1c3, b1a3)),
         /* [10] */ of(b2, m(b2d3, b2d1, b2c4, b2a4)),
         /* [11] */ of(b3, m(b3d4, b3d2, b3c5, b3c1, b3a5, b3a1)),
         /* [12] */ of(b4, m(b4d5, b4d3, b4c6, b4c2, b4a6, b4a2)),
         /* [13] */ of(b5, m(b5d6, b5d4, b5c7, b5c3, b5a7, b5a3)),
         /* [14] */ of(b6, m(b6d7, b6d5, b6c8, b6c4, b6a8, b6a4)),
         /* [15] */ of(b7, m(b7d8, b7d6, b7c5, b7a5)),
         /* [16] */ of(b8, m(b8d7, b8c6, b8a6)),
         /* [17] */ of(c1, m(c1e2, c1a2, c1d3, c1b3)),
         /* [18] */ of(c2, m(c2e3, c2e1, c2a3, c2a1, c2d4, c2b4)),
         /* [19] */ of(c3, m(c3e4, c3e2, c3a4, c3a2, c3d5, c3d1, c3b5, c3b1)),
         /* [20] */ of(c4, m(c4e5, c4e3, c4a5, c4a3, c4d6, c4d2, c4b6, c4b2)),
         /* [21] */ of(c5, m(c5e6, c5e4, c5a6, c5a4, c5d7, c5d3, c5b7, c5b3)),
         /* [22] */ of(c6, m(c6e7, c6e5, c6a7, c6a5, c6d8, c6d4, c6b8, c6b4)),
         /* [23] */ of(c7, m(c7e8, c7e6, c7a8, c7a6, c7d5, c7b5)),
         /* [24] */ of(c8, m(c8e7, c8a7, c8d6, c8b6)),
         /* [25] */ of(d1, m(d1f2, d1b2, d1e3, d1c3)),
         /* [26] */ of(d2, m(d2f3, d2f1, d2b3, d2b1, d2e4, d2c4)),
         /* [27] */ of(d3, m(d3f4, d3f2, d3b4, d3b2, d3e5, d3e1, d3c5, d3c1)),
         /* [28] */ of(d4, m(d4f5, d4f3, d4b5, d4b3, d4e6, d4e2, d4c6, d4c2)),
         /* [29] */ of(d5, m(d5f6, d5f4, d5b6, d5b4, d5e7, d5e3, d5c7, d5c3)),
         /* [30] */ of(d6, m(d6f7, d6f5, d6b7, d6b5, d6e8, d6e4, d6c8, d6c4)),
         /* [31] */ of(d7, m(d7f8, d7f6, d7b8, d7b6, d7e5, d7c5)),
         /* [32] */ of(d8, m(d8f7, d8b7, d8e6, d8c6)),
         /* [33] */ of(e1, m(e1g2, e1c2, e1f3, e1d3)),
         /* [34] */ of(e2, m(e2g3, e2g1, e2c3, e2c1, e2f4, e2d4)),
         /* [35] */ of(e3, m(e3g4, e3g2, e3c4, e3c2, e3f5, e3f1, e3d5, e3d1)),
         /* [36] */ of(e4, m(e4g5, e4g3, e4c5, e4c3, e4f6, e4f2, e4d6, e4d2)),
         /* [37] */ of(e5, m(e5g6, e5g4, e5c6, e5c4, e5f7, e5f3, e5d7, e5d3)),
         /* [38] */ of(e6, m(e6g7, e6g5, e6c7, e6c5, e6f8, e6f4, e6d8, e6d4)),
         /* [39] */ of(e7, m(e7g8, e7g6, e7c8, e7c6, e7f5, e7d5)),
         /* [40] */ of(e8, m(e8g7, e8c7, e8f6, e8d6)),
         /* [41] */ of(f1, m(f1h2, f1d2, f1g3, f1e3)),
         /* [42] */ of(f2, m(f2h3, f2h1, f2d3, f2d1, f2g4, f2e4)),
         /* [43] */ of(f3, m(f3h4, f3h2, f3d4, f3d2, f3g5, f3g1, f3e5, f3e1)),
         /* [44] */ of(f4, m(f4h5, f4h3, f4d5, f4d3, f4g6, f4g2, f4e6, f4e2)),
         /* [45] */ of(f5, m(f5h6, f5h4, f5d6, f5d4, f5g7, f5g3, f5e7, f5e3)),
         /* [46] */ of(f6, m(f6h7, f6h5, f6d7, f6d5, f6g8, f6g4, f6e8, f6e4)),
         /* [47] */ of(f7, m(f7h8, f7h6, f7d8, f7d6, f7g5, f7e5)),
         /* [48] */ of(f8, m(f8h7, f8d7, f8g6, f8e6)),
         /* [49] */ of(g1, m(g1e2, g1h3, g1f3)),
         /* [50] */ of(g2, m(g2e3, g2e1, g2h4, g2f4)),
         /* [51] */ of(g3, m(g3e4, g3e2, g3h5, g3h1, g3f5, g3f1)),
         /* [52] */ of(g4, m(g4e5, g4e3, g4h6, g4h2, g4f6, g4f2)),
         /* [53] */ of(g5, m(g5e6, g5e4, g5h7, g5h3, g5f7, g5f3)),
         /* [54] */ of(g6, m(g6e7, g6e5, g6h8, g6h4, g6f8, g6f4)),
         /* [55] */ of(g7, m(g7e8, g7e6, g7h5, g7f5)),
         /* [56] */ of(g8, m(g8e7, g8h6, g8f6)),
         /* [57] */ of(h1, m(h1f2, h1g3)),
         /* [58] */ of(h2, m(h2f3, h2f1, h2g4)),
         /* [59] */ of(h3, m(h3f4, h3f2, h3g5, h3g1)),
         /* [60] */ of(h4, m(h4f5, h4f3, h4g6, h4g2)),
         /* [61] */ of(h5, m(h5f6, h5f4, h5g7, h5g3)),
         /* [62] */ of(h6, m(h6f7, h6f5, h6g8, h6g4)),
         /* [63] */ of(h7, m(h7f8, h7f6, h7g5)),
         /* [64] */ of(h8, m(h8f7, h8g6))
      );
   }
}
