package com.haymel.chess.perft;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.haymel.chess.perft.Field.*;
import static com.haymel.chess.perft.Gen.NewGen;
import static com.haymel.chess.perft.help.MoveList.NewMoveList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

public final class KnightMoveTests {

   public static final String a1b3 = "a1b3";
   public static final String a1c2 = "a1c2";
   public static final String a2b4 = "a2b4";
   public static final String a2c3 = "a2c3";
   public static final String a2c1 = "a2c1";
   public static final String a3b5 = "a3b5";
   public static final String a3c4 = "a3c4";
   public static final String a3c2 = "a3c2";
   public static final String a3b1 = "a3b1";
   public static final String a4b6 = "a4b6";
   public static final String a4c5 = "a4c5";
   public static final String a4c3 = "a4c3";
   public static final String a4b2 = "a4b2";

   public static final String a5b7 = "a5b7";
   public static final String a5c6 = "a5c6";
   public static final String a5c4 = "a5c4";
   public static final String a5b3 = "a5b3";
   public static final String a6b8 = "a6b8";
   public static final String a6c7 = "a6c7";
   public static final String a6c5 = "a6c5";
   public static final String a6b4 = "a6b4";
   public static final String a7c8 = "a7c8";
   public static final String a7c6 = "a7c6";
   public static final String a7b5 = "a7b5";
   public static final String a8c7 = "a8c7";
   public static final String a8b6 = "a8b6";
   public static final String b1d2 = "b1d2";
   public static final String b1c3 = "b1c3";
   public static final String b1a3 = "b1a3";
   public static final String b2d3 = "b2d3";
   public static final String b2d1 = "b2d1";
   public static final String b2c4 = "b2c4";
   public static final String b2a4 = "b2a4";
   public static final String b3d4 = "b3d4";
   public static final String b3d2 = "b3d2";
   public static final String b3c5 = "b3c5";
   public static final String b3c1 = "b3c1";
   public static final String b3a5 = "b3a5";
   public static final String b3a1 = "b3a1";

   public static final String b4d5 = "b4d5";
   public static final String b4d3 = "b4d3";
   public static final String b4c6 = "b4c6";
   public static final String b4c2 = "b4c2";
   public static final String b4a6 = "b4a6";
   public static final String b4a2 = "b4a2";
   public static final String b5d6 = "b5d6";
   public static final String b5d4 = "b5d4";
   public static final String b5c7 = "b5c7";
   public static final String b5c3 = "b5c3";
   public static final String b5a7 = "b5a7";
   public static final String b5a3 = "b5a3";
   public static final String b6d7 = "b6d7";
   public static final String b6d5 = "b6d5";
   public static final String b6c8 = "b6c8";
   public static final String b6c4 = "b6c4";
   public static final String b6a8 = "b6a8";
   public static final String b6a4 = "b6a4";
   public static final String b7d8 = "b7d8";
   public static final String b7d6 = "b7d6";
   public static final String b7c5 = "b7c5";
   public static final String b7a5 = "b7a5";
   public static final String b8d7 = "b8d7";
   public static final String b8c6 = "b8c6";
   public static final String b8a6 = "b8a6";
   public static final String c1e2 = "c1e2";
   public static final String c1d3 = "c1d3";
   public static final String c1b3 = "c1b3";
   public static final String c1a2 = "c1a2";
   public static final String c2e3 = "c2e3";
   public static final String c2e1 = "c2e1";
   public static final String c2a3 = "c2a3";
   public static final String c2a1 = "c2a1";

   public static final String c2d4 = "c2d4";
   public static final String c2b4 = "c2b4";
   public static final String c3e4 = "c3e4";
   public static final String c3e2 = "c3e2";
   public static final String c3a4 = "c3a4";
   public static final String c3a2 = "c3a2";
   public static final String c3d5 = "c3d5";
   public static final String c3d1 = "c3d1";
   public static final String c3b5 = "c3b5";
   public static final String c3b1 = "c3b1";
   public static final String c4e5 = "c4e5";
   public static final String c4e3 = "c4e3";
   public static final String c4a5 = "c4a5";
   public static final String c4a3 = "c4a3";
   public static final String c4d6 = "c4d6";
   public static final String c4d2 = "c4d2";
   public static final String c4b6 = "c4b6";
   public static final String c4b2 = "c4b2";
   public static final String c5e6 = "c5e6";
   public static final String c5e4 = "c5e4";
   public static final String c5a6 = "c5a6";
   public static final String c5a4 = "c5a4";
   public static final String c5d7 = "c5d7";
   public static final String c5d3 = "c5d3";
   public static final String c5b7 = "c5b7";
   public static final String c5b3 = "c5b3";
   public static final String c6e7 = "c6e7";
   public static final String c6e5 = "c6e5";
   public static final String c6a7 = "c6a7";
   public static final String c6a5 = "c6a5";
   public static final String c6d8 = "c6d8";
   public static final String c6d4 = "c6d4";
   public static final String c6b8 = "c6b8";
   public static final String c6b4 = "c6b4";

   public static final String c7e8 = "c7e8";
   public static final String c7e6 = "c7e6";
   public static final String c7a8 = "c7a8";
   public static final String c7a6 = "c7a6";
   public static final String c7d5 = "c7d5";
   public static final String c7b5 = "c7b5";
   public static final String c8e7 = "c8e7";
   public static final String c8a7 = "c8a7";
   public static final String c8d6 = "c8d6";
   public static final String c8b6 = "c8b6";
   public static final String d1f2 = "d1f2";
   public static final String d1e3 = "d1e3";
   public static final String d1c3 = "d1c3";
   public static final String d1b2 = "d1b2";
   public static final String d2f3 = "d2f3";
   public static final String d2f1 = "d2f1";
   public static final String d2b3 = "d2b3";
   public static final String d2b1 = "d2b1";
   public static final String d2e4 = "d2e4";
   public static final String d2c4 = "d2c4";
   public static final String d3f4 = "d3f4";
   public static final String d3f2 = "d3f2";
   public static final String d3b4 = "d3b4";
   public static final String d3b2 = "d3b2";
   public static final String d3e5 = "d3e5";
   public static final String d3e1 = "d3e1";
   public static final String d3c5 = "d3c5";
   public static final String d3c1 = "d3c1";
   public static final String d4f5 = "d4f5";
   public static final String d4f3 = "d4f3";
   public static final String d4b5 = "d4b5";
   public static final String d4b3 = "d4b3";
   public static final String d4e6 = "d4e6";
   public static final String d4e2 = "d4e2";
   public static final String d4c6 = "d4c6";
   public static final String d4c2 = "d4c2";

   public static final String d5f6 = "d5f6";
   public static final String d5f4 = "d5f4";
   public static final String d5b6 = "d5b6";
   public static final String d5b4 = "d5b4";
   public static final String d5e7 = "d5e7";
   public static final String d5e3 = "d5e3";
   public static final String d5c7 = "d5c7";
   public static final String d5c3 = "d5c3";
   public static final String d6f7 = "d6f7";
   public static final String d6f5 = "d6f5";
   public static final String d6b7 = "d6b7";
   public static final String d6b5 = "d6b5";
   public static final String d6e8 = "d6e8";
   public static final String d6e4 = "d6e4";
   public static final String d6c8 = "d6c8";
   public static final String d6c4 = "d6c4";
   public static final String d7f8 = "d7f8";
   public static final String d7f6 = "d7f6";
   public static final String d7b8 = "d7b8";
   public static final String d7b6 = "d7b6";
   public static final String d7e5 = "d7e5";
   public static final String d7c5 = "d7c5";
   public static final String d8f7 = "d8f7";
   public static final String d8b7 = "d8b7";
   public static final String d8e6 = "d8e6";
   public static final String d8c6 = "d8c6";
   public static final String e1g2 = "e1g2";
   public static final String e1f3 = "e1f3";
   public static final String e1d3 = "e1d3";
   public static final String e1c2 = "e1c2";
   public static final String e2g3 = "e2g3";
   public static final String e2g1 = "e2g1";
   public static final String e2c3 = "e2c3";
   public static final String e2c1 = "e2c1";
   public static final String e2f4 = "e2f4";
   public static final String e2d4 = "e2d4";

   public static final String e3g4 = "e3g4";
   public static final String e3g2 = "e3g2";
   public static final String e3c4 = "e3c4";
   public static final String e3c2 = "e3c2";
   public static final String e3f5 = "e3f5";
   public static final String e3f1 = "e3f1";
   public static final String e3d5 = "e3d5";
   public static final String e3d1 = "e3d1";
   public static final String e4g5 = "e4g5";
   public static final String e4g3 = "e4g3";
   public static final String e4c5 = "e4c5";
   public static final String e4c3 = "e4c3";
   public static final String e4f6 = "e4f6";
   public static final String e4f2 = "e4f2";
   public static final String e4d6 = "e4d6";
   public static final String e4d2 = "e4d2";
   public static final String e5g6 = "e5g6";
   public static final String e5g4 = "e5g4";
   public static final String e5c6 = "e5c6";
   public static final String e5c4 = "e5c4";
   public static final String e5f7 = "e5f7";
   public static final String e5f3 = "e5f3";
   public static final String e5d7 = "e5d7";
   public static final String e5d3 = "e5d3";
   public static final String e6g7 = "e6g7";
   public static final String e6g5 = "e6g5";
   public static final String e6c7 = "e6c7";
   public static final String e6c5 = "e6c5";
   public static final String e6f8 = "e6f8";
   public static final String e6f4 = "e6f4";
   public static final String e6d8 = "e6d8";
   public static final String e6d4 = "e6d4";
   public static final String e7g8 = "e7g8";
   public static final String e7g6 = "e7g6";
   public static final String e7c8 = "e7c8";
   public static final String e7c6 = "e7c6";
   public static final String e7f5 = "e7f5";
   public static final String e7d5 = "e7d5";
   public static final String e8g7 = "e8g7";
   public static final String e8c7 = "e8c7";
   public static final String e8f6 = "e8f6";
   public static final String e8d6 = "e8d6";

   public static final String f1h2 = "f1h2";
   public static final String f1g3 = "f1g3";
   public static final String f1e3 = "f1e3";
   public static final String f1d2 = "f1d2";
   public static final String f2h3 = "f2h3";
   public static final String f2h1 = "f2h1";
   public static final String f2d3 = "f2d3";
   public static final String f2d1 = "f2d1";
   public static final String f2g4 = "f2g4";
   public static final String f2e4 = "f2e4";
   public static final String f3h4 = "f3h4";
   public static final String f3h2 = "f3h2";
   public static final String f3d4 = "f3d4";
   public static final String f3d2 = "f3d2";
   public static final String f3g5 = "f3g5";
   public static final String f3g1 = "f3g1";
   public static final String f3e5 = "f3e5";
   public static final String f3e1 = "f3e1";
   public static final String f4h5 = "f4h5";
   public static final String f4h3 = "f4h3";
   public static final String f4d5 = "f4d5";
   public static final String f4d3 = "f4d3";
   public static final String f4g6 = "f4g6";
   public static final String f4g2 = "f4g2";
   public static final String f4e6 = "f4e6";
   public static final String f4e2 = "f4e2";
   public static final String f5h6 = "f5h6";
   public static final String f5h4 = "f5h4";
   public static final String f5d6 = "f5d6";
   public static final String f5d4 = "f5d4";
   public static final String f5g7 = "f5g7";
   public static final String f5g3 = "f5g3";
   public static final String f5e7 = "f5e7";
   public static final String f5e3 = "f5e3";
   public static final String f6h7 = "f6h7";
   public static final String f6h5 = "f6h5";
   public static final String f6d7 = "f6d7";
   public static final String f6d5 = "f6d5";
   public static final String f6g8 = "f6g8";
   public static final String f6g4 = "f6g4";
   public static final String f6e8 = "f6e8";
   public static final String f6e4 = "f6e4";
   public static final String f7h8 = "f7h8";
   public static final String f7h6 = "f7h6";
   public static final String f7d8 = "f7d8";
   public static final String f7d6 = "f7d6";
   public static final String f7g5 = "f7g5";
   public static final String f7e5 = "f7e5";
   public static final String f8h7 = "f8h7";
   public static final String f8d7 = "f8d7";
   public static final String f8g6 = "f8g6";
   public static final String f8e6 = "f8e6";

   public static final String g1e2 = "g1e2";
   public static final String g1f3 = "g1f3";
   public static final String g1h3 = "g1h3";
   public static final String g2e3 = "g2e3";
   public static final String g2e1 = "g2e1";
   public static final String g2f4 = "g2f4";
   public static final String g2h4 = "g2h4";
   public static final String g3e4 = "g3e4";
   public static final String g3e2 = "g3e2";
   public static final String g3f5 = "g3f5";
   public static final String g3f1 = "g3f1";
   public static final String g3h5 = "g3h5";
   public static final String g3h1 = "g3h1";
   public static final String g4e5 = "g4e5";
   public static final String g4e3 = "g4e3";
   public static final String g4f6 = "g4f6";
   public static final String g4f2 = "g4f2";
   public static final String g4h6 = "g4h6";
   public static final String g4h2 = "g4h2";
   public static final String g5e6 = "g5e6";
   public static final String g5e4 = "g5e4";
   public static final String g5f7 = "g5f7";
   public static final String g5f3 = "g5f3";
   public static final String g5h7 = "g5h7";
   public static final String g5h3 = "g5h3";
   public static final String g6e7 = "g6e7";
   public static final String g6e5 = "g6e5";
   public static final String g6f8 = "g6f8";
   public static final String g6f4 = "g6f4";
   public static final String g6h8 = "g6h8";
   public static final String g6h4 = "g6h4";
   public static final String g7e8 = "g7e8";
   public static final String g7e6 = "g7e6";
   public static final String g7f5 = "g7f5";
   public static final String g7h5 = "g7h5";
   public static final String g8e7 = "g8e7";
   public static final String g8f6 = "g8f6";
   public static final String g8h6 = "g8h6";
   public static final String h1f2 = "h1f2";
   public static final String h1g3 = "h1g3";
   public static final String h2f3 = "h2f3";
   public static final String h2f1 = "h2f1";
   public static final String h2g4 = "h2g4";
   public static final String h2g1 = "h2g1";
   public static final String h3f4 = "h3f4";
   public static final String h3f2 = "h3f2";
   public static final String h3g5 = "h3g5";
   public static final String h3g1 = "h3g1";
   public static final String h4f5 = "h4f5";
   public static final String h4f3 = "h4f3";

   public static final String h4g6 = "h4g6";
   public static final String h4g2 = "h4g2";
   public static final String h5f6 = "h5f6";
   public static final String h5f4 = "h5f4";
   public static final String h5g7 = "h5g7";
   public static final String h5g3 = "h5g3";
   public static final String h6f7 = "h6f7";
   public static final String h6f5 = "h6f5";
   public static final String h6g8 = "h6g8";
   public static final String h6g4 = "h6g4";
   public static final String h7f8 = "h7f8";
   public static final String h7f6 = "h7f6";
   public static final String h7g5 = "h7g5";
   public static final String h8f7 = "h8f7";
   public static final String h8g6 = "h8g6";

   static Stream<Arguments> knightMovest() {
      return Stream.of(
         /* [1] */ of(Field.a1, Set.of( a1c2, a1b3)),
         /* [2] */ of(a2, Set.of( a2c3, a2c1, a2b4)),
         /* [3] */ of(a3, Set.of( a3c4, a3c2, a3b5, a3b1)),
         /* [4] */ of(a4, Set.of( a4c5, a4c3, a4b6, a4b2)),
         /* [5] */ of(a5, Set.of( a5c6, a5c4, a5b7, a5b3)),
         /* [6] */ of(a6, Set.of( a6c7, a6c5, a6b8, a6b4)),
         /* [7] */ of(a7, Set.of( a7c8, a7c6, a7b5)),
         /* [8] */ of(a8, Set.of( a8c7, a8b6)),
         /* [9] */ of(b1, Set.of( b1d2, b1c3, b1a3)),
         /* [10] */ of(b2, Set.of( b2d3, b2d1, b2c4, b2a4)),
         /* [11] */ of(b3, Set.of( b3d4, b3d2, b3c5, b3c1, b3a5, b3a1)),
         /* [12] */ of(b4, Set.of( b4d5, b4d3, b4c6, b4c2, b4a6, b4a2)),
         /* [13] */ of(b5, Set.of( b5d6, b5d4, b5c7, b5c3, b5a7, b5a3)),
         /* [14] */ of(b6, Set.of( b6d7, b6d5, b6c8, b6c4, b6a8, b6a4)),
         /* [15] */ of(b7, Set.of( b7d8, b7d6, b7c5, b7a5)),
         /* [16] */ of(b8, Set.of( b8d7, b8c6, b8a6)),
         /* [17] */ of(c1, Set.of( c1e2, c1a2, c1d3, c1b3)),
         /* [18] */ of(c2, Set.of( c2e3, c2e1, c2a3, c2a1, c2d4, c2b4)),
         /* [19] */ of(c3, Set.of( c3e4, c3e2, c3a4, c3a2, c3d5, c3d1, c3b5, c3b1)),
         /* [20] */ of(c4, Set.of( c4e5, c4e3, c4a5, c4a3, c4d6, c4d2, c4b6, c4b2)),
         /* [21] */ of(c5, Set.of( c5e6, c5e4, c5a6, c5a4, c5d7, c5d3, c5b7, c5b3)),
         /* [22] */ of(c6, Set.of( c6e7, c6e5, c6a7, c6a5, c6d8, c6d4, c6b8, c6b4)),
         /* [23] */ of(c7, Set.of( c7e8, c7e6, c7a8, c7a6, c7d5, c7b5)),
         /* [24] */ of(c8, Set.of( c8e7, c8a7, c8d6, c8b6)),
         /* [25] */ of(d1, Set.of( d1f2, d1b2, d1e3, d1c3)),
         /* [26] */ of(d2, Set.of( d2f3, d2f1, d2b3, d2b1, d2e4, d2c4)),
         /* [27] */ of(d3, Set.of( d3f4, d3f2, d3b4, d3b2, d3e5, d3e1, d3c5, d3c1)),
         /* [28] */ of(d4, Set.of( d4f5, d4f3, d4b5, d4b3, d4e6, d4e2, d4c6, d4c2)),
         /* [29] */ of(d5, Set.of( d5f6, d5f4, d5b6, d5b4, d5e7, d5e3, d5c7, d5c3)),
         /* [30] */ of(d6, Set.of( d6f7, d6f5, d6b7, d6b5, d6e8, d6e4, d6c8, d6c4)),
         /* [31] */ of(d7, Set.of( d7f8, d7f6, d7b8, d7b6, d7e5, d7c5)),
         /* [32] */ of(d8, Set.of( d8f7, d8b7, d8e6, d8c6)),
         /* [33] */ of(e1, Set.of( e1g2, e1c2, e1f3, e1d3)),
         /* [34] */ of(e2, Set.of( e2g3, e2g1, e2c3, e2c1, e2f4, e2d4)),
         /* [35] */ of(e3, Set.of( e3g4, e3g2, e3c4, e3c2, e3f5, e3f1, e3d5, e3d1)),
         /* [36] */ of(e4, Set.of( e4g5, e4g3, e4c5, e4c3, e4f6, e4f2, e4d6, e4d2)),
         /* [37] */ of(e5, Set.of( e5g6, e5g4, e5c6, e5c4, e5f7, e5f3, e5d7, e5d3)),
         /* [38] */ of(e6, Set.of( e6g7, e6g5, e6c7, e6c5, e6f8, e6f4, e6d8, e6d4)),
         /* [39] */ of(e7, Set.of( e7g8, e7g6, e7c8, e7c6, e7f5, e7d5)),
         /* [40] */ of(e8, Set.of( e8g7, e8c7, e8f6, e8d6)),
         /* [41] */ of(f1, Set.of( f1h2, f1d2, f1g3, f1e3)),
         /* [42] */ of(f2, Set.of( f2h3, f2h1, f2d3, f2d1, f2g4, f2e4)),
         /* [43] */ of(f3, Set.of( f3h4, f3h2, f3d4, f3d2, f3g5, f3g1, f3e5, f3e1)),
         /* [44] */ of(f4, Set.of( f4h5, f4h3, f4d5, f4d3, f4g6, f4g2, f4e6, f4e2)),
         /* [45] */ of(f5, Set.of( f5h6, f5h4, f5d6, f5d4, f5g7, f5g3, f5e7, f5e3)),
         /* [46] */ of(f6, Set.of( f6h7, f6h5, f6d7, f6d5, f6g8, f6g4, f6e8, f6e4)),
         /* [47] */ of(f7, Set.of( f7h8, f7h6, f7d8, f7d6, f7g5, f7e5)),
         /* [48] */ of(f8, Set.of( f8h7, f8d7, f8g6, f8e6)),
         /* [49] */ of(g1, Set.of( g1e2, g1h3, g1f3)),
         /* [50] */ of(g2, Set.of( g2e3, g2e1, g2h4, g2f4)),
         /* [51] */ of(g3, Set.of( g3e4, g3e2, g3h5, g3h1, g3f5, g3f1)),
         /* [52] */ of(g4, Set.of( g4e5, g4e3, g4h6, g4h2, g4f6, g4f2)),
         /* [53] */ of(g5, Set.of( g5e6, g5e4, g5h7, g5h3, g5f7, g5f3)),
         /* [54] */ of(g6, Set.of( g6e7, g6e5, g6h8, g6h4, g6f8, g6f4)),
         /* [55] */ of(g7, Set.of( g7e8, g7e6, g7h5, g7f5)),
         /* [56] */ of(g8, Set.of( g8e7, g8h6, g8f6)),
         /* [57] */ of(h1, Set.of( h1f2, h1g3)),
         /* [58] */ of(h2, Set.of( h2f3, h2f1, h2g4)),
         /* [59] */ of(h3, Set.of( h3f4, h3f2, h3g5, h3g1)),
         /* [60] */ of(h4, Set.of( h4f5, h4f3, h4g6, h4g2)),
         /* [61] */ of(h5, Set.of( h5f6, h5f4, h5g7, h5g3)),
         /* [62] */ of(h6, Set.of( h6f7, h6f5, h6g8, h6g4)),
         /* [63] */ of(h7, Set.of( h7f8, h7f6, h7g5)),
         /* [64] */ of(h8, Set.of( h8f7, h8g6))
         );
   }

   @ParameterizedTest
   @MethodSource("knightMovest")
   void whiteKnightMovest(int field, Set<String> expectedMoves) {
      knightMoves(field, Color.white, expectedMoves);
   }

   @ParameterizedTest
   @MethodSource("knightMovest")
   void blackKnightMovest(int field, Set<String> expectedMoves) {
      knightMoves(field, Color.black, expectedMoves);
   }

   void knightMoves(int field, int color, Set<String> expectedMoves) {
      //given
      Chess chess = new Chess();
      chess.emptyBoard();
      chess.side = color;
      chess.xside = Gen.opponent[color];
      chess.color[field] = color;
      chess.board[field] = Piece.knight;
      //when
      NewGen(chess).execute();
      //then
      Set<String> moves = NewMoveList(chess).moveStrings();
      assertThat(moves).isEqualTo(expectedMoves);
   }
}
