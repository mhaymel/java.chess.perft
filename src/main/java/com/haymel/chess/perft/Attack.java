package com.haymel.chess.perft;

import static com.haymel.chess.perft.Field.isValid;
import static com.haymel.chess.perft.File.A;
import static com.haymel.chess.perft.File.H;
import static com.haymel.chess.perft.Init.file;
import static com.haymel.chess.perft.Init.rank;
import static com.haymel.chess.perft.Piece.*;
import static com.haymel.chess.perft.Generator.*;
import static com.haymel.chess.perft.MoveTables.kingMoves;
import static com.haymel.chess.perft.MoveTables.knightMoves;
import static com.haymel.chess.perft.MoveTables.bishopMoves;
import static com.haymel.chess.perft.MoveTables.rookMoves;

public final class Attack {

   private static final int[] left = {leftDown, leftUp};
   private static final int[] right = {rightDown, rightUp};
   private final Chess c;

   public Attack(Chess c) { this.c = c; }

   public static Attack NewAttack(Chess chess) { return new Attack(chess); }

   public boolean attack(int side, int field) {
      if (isPawnAttack(side, field)) return true;
      if (isKnightAttack(side, field)) return true;
      if (isRookAttack(side, field)) return true;
      if (isBishopAttack(side, field)) return true;
      if (isQueenAttack(side, field)) return true;
      if (isKingAttack(side, field)) return true;
      return false;
   }

   private boolean isPawnAttack(int side, int field) {
      if (rank[side][field] == 0) return false;
      if (file[field] > A && isAttackingPawn(side, field + left[side])) return true;
      if (file[field] < H && isAttackingPawn(side, field + right[side])) return true;
      return false;
   }

   private boolean isKnightAttack(int side, int field) { return isAttack(side, field, knightMoves, knight); }

   private boolean isKingAttack(int side, int field) { return isAttack(side, field, kingMoves, king); }

   private boolean isAttack(int side, int field, int[][] moves, int piece) {
      int to = moves[field][0];
      for (int direction = 1; isValid(to); direction++) {
         if (isAttackingPiece(side, to, piece)) return true;
         to = moves[field][direction];
      }
      return false;
   }

   private boolean isRookAttack(int side, int field) { return qrbAttack(side, field, rookMoves, rook); }

   private boolean isBishopAttack(int side, int field) { return qrbAttack(side, field, bishopMoves, bishop); }

   private boolean isQueenAttack(int side, int field) {
      return isRookAttack(side, field) || isBishopAttack(side, field);
   }

   private boolean qrbAttack(int side, int field, int[][] moves, int piece) {
      for (int i = 0; i < 4; i++)
         if (qrbAttack(side, field, i, moves, piece)) return true;
      return false;
   }

   private boolean qrbAttack(int side, int from, int direction, int[][] moves, int piece) {
      int to = moves[from][direction];
      while (isValid(to)) {
         if (!c.isEmpty(to)) return c.color[to] == side && c.board[to] == piece;
         to = moves[to][direction];
      }
      return false;
   }

   private boolean isAttackingPawn(int side, int field) { return isAttackingPiece(side, field, pawn); }

   private boolean isAttackingPiece(int side, int field, int piece) { return c.board[field] == piece && c.color[field] == side; }

}
