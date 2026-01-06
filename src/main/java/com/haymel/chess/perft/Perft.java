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
      if (depth == 0) return 1;

      long count = 0;
      generator.execute();
      int from = chess.firstMove[chess.ply];
      int to = chess.firstMove[chess.ply + 1];
      String fen = Fen.toFen(chess);
      for (int i = from; i < to; i++) {
         Move move = chess.moveList[i];
         if (update.makeMove(move)) {
//            System.out.println(move);
            count += execute(depth - 1);
            update.unMakeMove();
         }
//         String fen2 = Fen.toFen(chess);
//         if (!fen2.equals(fen)) {
//            System.out.println(move);
//            System.out.println("fen:   " + fen);
//            System.out.println("fen2:  " + fen2);
//            System.exit(0);
//         }
      }
     return count;

   }
}
