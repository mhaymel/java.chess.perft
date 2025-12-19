package com.haymel.chess.perft.util;

import com.haymel.chess.perft.Field;

public enum FieldEnum {
   a1(Field.a1), b1(Field.b1), c1(Field.c1), d1(Field.d1), e1(Field.e1), f1(Field.f1), g1(Field.g1), h1(Field.h1),
   a2(Field.a2), b2(Field.b2), c2(Field.c2), d2(Field.d2), e2(Field.e2), f2(Field.f2), g2(Field.g2), h2(Field.h2),
   a3(Field.a3), b3(Field.b3), c3(Field.c3), d3(Field.d3), e3(Field.e3), f3(Field.f3), g3(Field.g3), h3(Field.h3),
   a4(Field.a4), b4(Field.b4), c4(Field.c4), d4(Field.d4), e4(Field.e4), f4(Field.f4), g4(Field.g4), h4(Field.h4),
   a5(Field.a5), b5(Field.b5), c5(Field.c5), d5(Field.d5), e5(Field.e5), f5(Field.f5), g5(Field.g5), h5(Field.h5),
   a6(Field.a6), b6(Field.b6), c6(Field.c6), d6(Field.d6), e6(Field.e6), f6(Field.f6), g6(Field.g6), h6(Field.h6),
   a7(Field.a7), b7(Field.b7), c7(Field.c7), d7(Field.d7), e7(Field.e7), f7(Field.f7), g7(Field.g7), h7(Field.h7),
   a8(Field.a8), b8(Field.b8), c8(Field.c8), d8(Field.d8), e8(Field.e8), f8(Field.f8), g8(Field.g8), h8(Field.h8);

   public final int value;

   FieldEnum(int value) {
      this.value = value;
   }
}
