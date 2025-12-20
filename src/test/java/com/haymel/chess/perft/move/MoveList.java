package com.haymel.chess.perft.move;

import com.haymel.chess.perft.Chess;
import com.haymel.chess.perft.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public final class MoveList {

   private final Chess chess;

   public static MoveList NewMoveList(Chess chess) { return new MoveList(chess); }
   public MoveList(Chess chess) { this.chess = chess; }

   public Set<String> moveStrings() {
      return moveList().stream()
         .map(Move::toString)
         .collect(toSet());
   }

   public List<Move> moveList() {
      List<Move> list = new ArrayList<>();
      int from = chess.firstMove[chess.ply];
      int to = chess.firstMove[chess.ply + 1];
      for (int i = from; i < to; i++)
         list.add(chess.moveList[i]);
      return list;
   }
}
