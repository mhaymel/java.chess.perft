package com.haymel.chess.perft;

import org.junit.jupiter.params.provider.Arguments;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

//chatgpt
public class PerftFileLoader {

   static Stream<Arguments> positions(String fileName) {
      InputStream is = PerftFileLoader.class
         .getClassLoader()
         .getResourceAsStream(fileName);

      if (is == null) {
         throw new RuntimeException("Resource perft01.txt nicht gefunden");
      }

      BufferedReader reader = new BufferedReader(new InputStreamReader(is));

      return reader.lines()
         .map(String::trim)
         .filter(line -> !line.isEmpty() && !line.startsWith("#"))
         .map(PerftFileLoader::parseLine)
         .filter(a -> a != null);
   }

   private static Arguments parseLine(String line) {
      // Format: depth;count;fen
      String[] parts = line.split(";", 3);
      if (parts.length != 3) {
         System.err.println("Ungültige Zeile ignoriert: " + line);
         return null;
      }

      int depth = Integer.parseInt(parts[0].trim());
      long count = Long.parseLong(parts[1].trim());
      String fen = parts[2].trim();

      return Arguments.of(depth, count, fen);
   }
}