package com.comp301.a09akari.model;

import javafx.scene.control.Cell;

public class PuzzleImpl implements Puzzle {
  private int width;
  private int height;
  private int[][] board;
  //  private PuzzleImpl puzzle = new PuzzleImpl(board);

  public PuzzleImpl(int[][] board) {
    this.width = board[0].length;
    this.height = board.length;
    this.board = board;
    //    PuzzleImpl temp = new PuzzleImpl(board);
    //    this.puzzle = temp;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r > this.width || c > this.height) {
      throw new IndexOutOfBoundsException();
    }
    if (r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (this.board[r][c] == 0
        || this.board[r][c] == 1
        || this.board[r][c] == 2
        || this.board[r][c] == 3
        || this.board[r][c] == 4) {
      return CellType.CLUE;
    } else if (this.board[r][c] == 5) {
      return CellType.WALL;
    } else {
      return CellType.CORRIDOR;
    }
    // return CellType.CLUE;
  }

  @Override
  public int getClue(int r, int c) {
    if (r > this.width || c > this.height) {
      throw new IndexOutOfBoundsException();
    }
    if (r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (this.getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    return this.board[r][c];
  }
}
