package com.haymel.chess.perft.move;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.move.Moves.*;
import static com.haymel.chess.perft.move.TestUtil.m;
import static org.junit.jupiter.params.provider.Arguments.of;

public final class PossibleBishopMoves {

   public static Stream<Arguments> moves() {
      return Stream.of(
         /* [1] */ of(a1, m(a1b2, a1c3, a1d4, a1e5, a1f6, a1g7, a1h8)),
         /* [2] */ of(a2, m(a2b3, a2c4, a2d5, a2e6, a2f7, a2g8, a2b1)),
         /* [3] */ of(a3, m(a3b4, a3c5, a3d6, a3e7, a3f8, a3b2, a3c1)),
         /* [4] */ of(a4, m(a4b5, a4c6, a4d7, a4e8, a4b3, a4c2, a4d1)),
         /* [5] */ of(a5, m(a5b6, a5c7, a5d8, a5b4, a5c3, a5d2, a5e1)),
         /* [6] */ of(a6, m(a6b7, a6c8, a6b5, a6c4, a6d3, a6e2, a6f1)),
         /* [7] */ of(a7, m(a7b8, a7b6, a7c5, a7d4, a7e3, a7f2, a7g1)),
         /* [8] */ of(a8, m(a8b7, a8c6, a8d5, a8e4, a8f3, a8g2, a8h1)),
         /* [9] */ of(b1, m(b1c2, b1d3, b1e4, b1f5, b1g6, b1h7, b1a2)),
         /* [10] */ of(b2, m(b2c3, b2d4, b2e5, b2f6, b2g7, b2h8, b2a3, b2c1, b2a1)),
         /* [11] */ of(b3, m(b3c4, b3d5, b3e6, b3f7, b3g8, b3a4, b3c2, b3d1, b3a2)),
         /* [12] */ of(b4, m(b4c5, b4d6, b4e7, b4f8, b4a5, b4c3, b4d2, b4e1, b4a3)),
         /* [13] */ of(b5, m(b5c6, b5d7, b5e8, b5a6, b5c4, b5d3, b5e2, b5f1, b5a4)),
         /* [14] */ of(b6, m(b6c7, b6d8, b6a7, b6c5, b6d4, b6e3, b6f2, b6g1, b6a5)),
         /* [15] */ of(b7, m(b7c8, b7a8, b7c6, b7d5, b7e4, b7f3, b7g2, b7h1, b7a6)),
         /* [16] */ of(b8, m(b8c7, b8d6, b8e5, b8f4, b8g3, b8h2, b8a7)),
         /* [17] */ of(c1, m(c1d2, c1e3, c1f4, c1g5, c1h6, c1b2, c1a3)),
         /* [18] */ of(c2, m(c2d3, c2e4, c2f5, c2g6, c2h7, c2b3, c2a4, c2d1, c2b1)),
         /* [19] */ of(c3, m(c3d4, c3e5, c3f6, c3g7, c3h8, c3b4, c3a5, c3d2, c3e1, c3b2, c3a1)),
         /* [20] */ of(c4, m(c4d5, c4e6, c4f7, c4g8, c4b5, c4a6, c4d3, c4e2, c4f1, c4b3, c4a2)),
         /* [21] */ of(c5, m(c5d6, c5e7, c5f8, c5b6, c5a7, c5d4, c5e3, c5f2, c5g1, c5b4, c5a3)),
         /* [22] */ of(c6, m(c6d7, c6e8, c6b7, c6a8, c6d5, c6e4, c6f3, c6g2, c6h1, c6b5, c6a4)),
         /* [23] */ of(c7, m(c7d8, c7b8, c7d6, c7e5, c7f4, c7g3, c7h2, c7b6, c7a5)),
         /* [24] */ of(c8, m(c8d7, c8e6, c8f5, c8g4, c8h3, c8b7, c8a6)),
         /* [25] */ of(d1, m(d1e2, d1f3, d1g4, d1h5, d1c2, d1b3, d1a4)),
         /* [26] */ of(d2, m(d2e3, d2f4, d2g5, d2h6, d2c3, d2b4, d2a5, d2e1, d2c1)),
         /* [27] */ of(d3, m(d3e4, d3f5, d3g6, d3h7, d3c4, d3b5, d3a6, d3e2, d3f1, d3c2, d3b1)),
         /* [28] */ of(d4, m(d4e5, d4f6, d4g7, d4h8, d4c5, d4b6, d4a7, d4e3, d4f2, d4g1, d4c3, d4b2, d4a1)),
         /* [29] */ of(d5, m(d5e6, d5f7, d5g8, d5c6, d5b7, d5a8, d5e4, d5f3, d5g2, d5h1, d5c4, d5b3, d5a2)),
         /* [30] */ of(d6, m(d6e7, d6f8, d6c7, d6b8, d6e5, d6f4, d6g3, d6h2, d6c5, d6b4, d6a3)),
         /* [31] */ of(d7, m(d7e8, d7c8, d7e6, d7f5, d7g4, d7h3, d7c6, d7b5, d7a4)),
         /* [32] */ of(d8, m(d8e7, d8f6, d8g5, d8h4, d8c7, d8b6, d8a5)),
         /* [33] */ of(e1, m(e1f2, e1g3, e1h4, e1d2, e1c3, e1b4, e1a5)),
         /* [34] */ of(e2, m(e2f3, e2g4, e2h5, e2d3, e2c4, e2b5, e2a6, e2f1, e2d1)),
         /* [35] */ of(e3, m(e3f4, e3g5, e3h6, e3d4, e3c5, e3b6, e3a7, e3f2, e3g1, e3d2, e3c1)),
         /* [36] */ of(e4, m(e4f5, e4g6, e4h7, e4d5, e4c6, e4b7, e4a8, e4f3, e4g2, e4h1, e4d3, e4c2, e4b1)),
         /* [37] */ of(e5, m(e5f6, e5g7, e5h8, e5d6, e5c7, e5b8, e5f4, e5g3, e5h2, e5d4, e5c3, e5b2, e5a1)),
         /* [38] */ of(e6, m(e6f7, e6g8, e6d7, e6c8, e6f5, e6g4, e6h3, e6d5, e6c4, e6b3, e6a2)),
         /* [39] */ of(e7, m(e7f8, e7d8, e7f6, e7g5, e7h4, e7d6, e7c5, e7b4, e7a3)),
         /* [40] */ of(e8, m(e8f7, e8g6, e8h5, e8d7, e8c6, e8b5, e8a4)),
         /* [41] */ of(f1, m(f1g2, f1h3, f1e2, f1d3, f1c4, f1b5, f1a6)),
         /* [42] */ of(f2, m(f2g3, f2h4, f2e3, f2d4, f2c5, f2b6, f2a7, f2g1, f2e1)),
         /* [43] */ of(f3, m(f3g4, f3h5, f3e4, f3d5, f3c6, f3b7, f3a8, f3g2, f3h1, f3e2, f3d1)),
         /* [44] */ of(f4, m(f4g5, f4h6, f4e5, f4d6, f4c7, f4b8, f4g3, f4h2, f4e3, f4d2, f4c1)),
         /* [45] */ of(f5, m(f5g6, f5h7, f5e6, f5d7, f5c8, f5g4, f5h3, f5e4, f5d3, f5c2, f5b1)),
         /* [46] */ of(f6, m(f6g7, f6h8, f6e7, f6d8, f6g5, f6h4, f6e5, f6d4, f6c3, f6b2, f6a1)),
         /* [47] */ of(f7, m(f7g8, f7e8, f7g6, f7h5, f7e6, f7d5, f7c4, f7b3, f7a2)),
         /* [48] */ of(f8, m(f8g7, f8h6, f8e7, f8d6, f8c5, f8b4, f8a3)),
         /* [49] */ of(g1, m(g1h2, g1f2, g1e3, g1d4, g1c5, g1b6, g1a7)),
         /* [50] */ of(g2, m(g2h3, g2f3, g2e4, g2d5, g2c6, g2b7, g2a8, g2h1, g2f1)),
         /* [51] */ of(g3, m(g3h4, g3f4, g3e5, g3d6, g3c7, g3b8, g3h2, g3f2, g3e1)),
         /* [52] */ of(g4, m(g4h5, g4f5, g4e6, g4d7, g4c8, g4h3, g4f3, g4e2, g4d1)),
         /* [53] */ of(g5, m(g5h6, g5f6, g5e7, g5d8, g5h4, g5f4, g5e3, g5d2, g5c1)),
         /* [54] */ of(g6, m(g6h7, g6f7, g6e8, g6h5, g6f5, g6e4, g6d3, g6c2, g6b1)),
         /* [55] */ of(g7, m(g7h8, g7f8, g7h6, g7f6, g7e5, g7d4, g7c3, g7b2, g7a1)),
         /* [56] */ of(g8, m(g8h7, g8f7, g8e6, g8d5, g8c4, g8b3, g8a2)),
         /* [57] */ of(h1, m(h1g2, h1f3, h1e4, h1d5, h1c6, h1b7, h1a8)),
         /* [58] */ of(h2, m(h2g3, h2f4, h2e5, h2d6, h2c7, h2b8, h2g1)),
         /* [59] */ of(h3, m(h3g4, h3f5, h3e6, h3d7, h3c8, h3g2, h3f1)),
         /* [60] */ of(h4, m(h4g5, h4f6, h4e7, h4d8, h4g3, h4f2, h4e1)),
         /* [61] */ of(h5, m(h5g6, h5f7, h5e8, h5g4, h5f3, h5e2, h5d1)),
         /* [62] */ of(h6, m(h6g7, h6f8, h6g5, h6f4, h6e3, h6d2, h6c1)),
         /* [63] */ of(h7, m(h7g8, h7g6, h7f5, h7e4, h7d3, h7c2, h7b1)),
         /* [64] */ of(h8, m(h8g7, h8f6, h8e5, h8d4, h8c3, h8b2, h8a1))
      );
   }
}
