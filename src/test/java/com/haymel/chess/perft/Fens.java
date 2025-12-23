package com.haymel.chess.perft;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Fens {

   public static List<String> read() {
      InputStream is = Fens.class
         .getClassLoader()
         //https://github.com/zabuzara/Chess-Fen/blob/main/FENs.txt
         .getResourceAsStream("fens.txt");

      if (is == null) throw new RuntimeException("fens.txt not found");

      return new BufferedReader(new InputStreamReader(is))
         .lines()
         .collect(Collectors.toList());
   }
}
