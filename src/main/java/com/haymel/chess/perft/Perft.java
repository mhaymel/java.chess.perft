package com.haymel.chess.perft;

public final class Perft {

   private final Chess chess;
   private final Update update;
   private final Generator generator;

   public Perft(String fen) {
      this(Fen.load(fen));
   }

   public Perft(Chess chess) {
      this.chess = chess;
      this.update = new Update(chess);
      this.generator = new Generator(chess);
   }

   public long execute(int depth) {
      if (depth == 0) return 0;

      generator.execute();
      return 0;

   }
}
