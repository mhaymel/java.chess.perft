package com.haymel.chess.perft;

import org.junit.jupiter.params.provider.Arguments;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

//chatgpt
public final class PerfsuiteEpdLoader {

   private PerfsuiteEpdLoader() {
      // Utility class
   }

   /**
    * Lädt perftsuite.epd aus dem Resource-Pfad und erzeugt:
    * Arguments.of(depth, nodes, fen)
    */
   public static Stream<Arguments> perftSuite() {

      InputStream is = PerfsuiteEpdLoader.class
         .getClassLoader()
         .getResourceAsStream("perftsuite.epd");

      if (is == null) {
         throw new IllegalStateException("Resource perftsuite.epd nicht gefunden");
      }

      BufferedReader reader = new BufferedReader(new InputStreamReader(is));

      return reader.lines()
         .map(String::trim)
         .filter(line -> !line.isEmpty() && !line.startsWith("#"))
         .flatMap(PerfsuiteEpdLoader::parseEpdLine);
   }

   /**
    * Parst eine EPD-Zeile wie:
    * rnbqkbnr/... w KQkq - ;D1 20 ;D2 400 ;D3 8902 ...
    * <p>
    * und erzeugt:
    * Arguments.of(1, 20L, fen)
    * Arguments.of(2, 400L, fen)
    * ...
    */
   private static Stream<Arguments> parseEpdLine(String line) {

      String[] parts = line.split(";");
      if (parts.length < 2) {
         System.err.println("Ungültige EPD-Zeile ignoriert: " + line);
         return Stream.empty();
      }

      String fen = parts[0].trim();

      List<Arguments> list = new ArrayList<>();

      for (int i = 1; i < parts.length; i++) {
         String entry = parts[i].trim();

         if (!entry.startsWith("D")) {
            continue;
         }

         String[] kv = entry.split("\\s+");
         if (kv.length != 2) {
            continue;
         }

         int depth = Integer.parseInt(kv[0].substring(1)); // "D3" → 3
         long nodes = Long.parseLong(kv[1]);

         list.add(Arguments.of(depth, nodes, fen));
      }

      return list.stream();
   }
}