package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModelImpl implements Model {
  private PuzzleLibrary puzzleLibrary;
  private int index;
  private boolean[][] board;
  private int[][] printBoard;
  private List<ModelObserver> observers;

  public ModelImpl(PuzzleLibrary library) {
    this.observers = new ArrayList<>();
    this.puzzleLibrary = library;
    this.board = new boolean[this.getActivePuzzle().getHeight()][this.getActivePuzzle().getWidth()];
    this.printBoard =
        new int[this.getActivePuzzle().getHeight()][this.getActivePuzzle().getWidth()];
  }

  @Override
  public void addLamp(int r, int c) {
    if (r < 0
        || c < 0
        || r >= this.getActivePuzzle().getHeight()
        || c >= this.getActivePuzzle().getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (this.puzzleLibrary.getPuzzle(index).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    this.board[r][c] = true;

    notifyObservers();
  }

  @Override
  public void removeLamp(int r, int c) {
    if (r < 0
        || c < 0
        || r >= this.getActivePuzzle().getHeight()
        || c >= this.getActivePuzzle().getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (this.puzzleLibrary.getPuzzle(index).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    this.board[r][c] = false;

    notifyObservers();
  }

  @Override
  public boolean isLit(int r, int c) {
    int r1 = r;
    int c1 = c;
    int r2 = r;
    int c2 = c;

    if (r < 0
        || c < 0
        || r >= this.getActivePuzzle().getHeight()
        || c >= this.getActivePuzzle().getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (this.puzzleLibrary.getPuzzle(index).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }

    while (r1 >= 0
        && this.getActivePuzzle().getCellType(r1, c) != CellType.WALL
        && this.getActivePuzzle().getCellType(r1, c) != CellType.CLUE) {
      if (this.board[r1][c]) {
        return true;
      }
      r1--;
    }
    //    System.out.println(this.index);
    while (r2 < this.getActivePuzzle().getHeight()
        && this.getActivePuzzle().getCellType(r2, c) != CellType.WALL
        && this.getActivePuzzle().getCellType(r2, c) != CellType.CLUE) {
      if (this.board[r2][c]) {
        return true;
      }
      r2++;
    }
    while (c1 >= 0
        && this.getActivePuzzle().getCellType(r, c1) != CellType.WALL
        && this.getActivePuzzle().getCellType(r, c1) != CellType.CLUE) {
      if (this.board[r][c1]) {
        return true;
      }
      c1--;
    }
    while (c2 < this.getActivePuzzle().getWidth()
        && this.getActivePuzzle().getCellType(r, c2) != CellType.WALL
        && this.getActivePuzzle().getCellType(r, c2) != CellType.CLUE) {
      if (this.board[r][c2]) {
        return true;
      }
      c2++;
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (r < 0
        || c < 0
        || r >= this.getActivePuzzle().getHeight()
        || c >= this.getActivePuzzle().getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (this.puzzleLibrary.getPuzzle(index).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    return this.board[r][c];
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    int r1 = r;
    int r2 = r;
    int c1 = c;
    int c2 = c;
    if (r < 0
        || c < 0
        || r >= this.getActivePuzzle().getHeight()
        || c >= this.getActivePuzzle().getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (!this.board[r][c]) {
      throw new IllegalArgumentException();
    }
    while (r1 > 0
        && this.getActivePuzzle().getCellType(r1, c) != CellType.WALL
        && this.getActivePuzzle().getCellType(r1, c) != CellType.CLUE) {
      if (this.board[r1 - 1][c]) {
        return true;
      }
      r1--;
    }
    // System.out.println(this.index);
    while (r2 < (this.getActivePuzzle().getHeight() - 1)
        && this.getActivePuzzle().getCellType(r2, c) != CellType.WALL
        && this.getActivePuzzle().getCellType(r2, c) != CellType.CLUE) {
      if (this.board[r2 + 1][c]) {
        return true;
      }
      r2++;
    }
    while (c1 > 0
        && this.getActivePuzzle().getCellType(r, c1) != CellType.WALL
        && this.getActivePuzzle().getCellType(r, c1) != CellType.CLUE) {
      if (this.board[r][c1 - 1]) {
        return true;
      }
      c1--;
    }
    while (c2 < (this.getActivePuzzle().getWidth() - 1)
        && this.getActivePuzzle().getCellType(r, c2) != CellType.WALL
        && this.getActivePuzzle().getCellType(r, c2) != CellType.CLUE) {
      if (this.board[r][c2 + 1]) {
        return true;
      }
      c2++;
    }
    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return this.puzzleLibrary.getPuzzle(index);
  }

  @Override
  public int getActivePuzzleIndex() {
    if (this.index < 0) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return this.index;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index < 0 || index >= this.puzzleLibrary.size()) {
      throw new IndexOutOfBoundsException();
    }
    this.index = index;

    notifyObservers();
  }

  @Override
  public int getPuzzleLibrarySize() {
    return this.puzzleLibrary.size();
  }

  @Override
  public void resetPuzzle() {
    for (int i = 0; i < this.getActivePuzzle().getHeight(); i++) {
      for (int j = 0; j < this.getActivePuzzle().getWidth(); j++) {
        this.board[i][j] = false;
      }
    }

    notifyObservers();
  }

  @Override
  public boolean isSolved() {
    System.out.println(
        Arrays.deepToString(currPuzzle())
            .replace("], ", "]\n")
            .replace("[[", "[")
            .replace("]]", "]"));

    // boolean clueCells = true;
    // Go through every cell and check if is a clue cell. If so, is it satisfied?
    for (int i = 0; i < this.getActivePuzzle().getHeight(); i++) {
      for (int j = 0; j < this.getActivePuzzle().getWidth(); j++) {
        if (this.getActivePuzzle().getCellType(i, j) == CellType.CLUE) {
          if (!isClueSatisfied(i, j)) {
            System.out.println("clue not satisfied");
            return false;
          }
        }
      }
    }
    System.out.println("passed1");
    // go through every cell and check if is a corridor. if so, is it lit?
    for (int i = 0; i < this.getActivePuzzle().getHeight(); i++) {
      for (int j = 0; j < this.getActivePuzzle().getWidth(); j++) {
        if (this.getActivePuzzle().getCellType(i, j) == CellType.CORRIDOR) {
          if (!isLit(i, j)) {
            System.out.println("not litty");
            return false;
          }
        }
      }
    }
    System.out.println("passed2");
    for (int i = 0; i < this.getActivePuzzle().getHeight(); i++) {
      for (int j = 0; j < this.getActivePuzzle().getWidth(); j++) {
        if (this.getActivePuzzle().getCellType(i, j) == CellType.CORRIDOR) {
          if (this.isLamp(i, j)) {
            if (isLampIllegal(i, j)) {
              System.out.println("lamp not legal");
              return false;
            }
          }
        }
      }
    }
    System.out.println("passed3");
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    int num = 0;
    if (r < 0
        || c < 0
        || r >= this.getActivePuzzle().getHeight()
        || c >= this.getActivePuzzle().getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (this.puzzleLibrary.getPuzzle(index).getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    if (r >= 1 && this.board[r - 1][c]) {
      num++;
    }
    if (r < this.getActivePuzzle().getHeight() && this.board[r + 1][c]) {
      num++;
    }
    if (c >= 1 && this.board[r][c - 1]) {
      num++;
    }
    if (c < this.getActivePuzzle().getWidth() && this.board[r][c + 1]) {
      num++;
    }
    return this.getActivePuzzle().getClue(r, c) == num;
  }

  @Override
  public void addObserver(ModelObserver observer) {
    this.observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    this.observers.remove(observer);
  }

  private void notifyObservers() {
    for (ModelObserver observer : this.observers) {
      observer.update(this);
    }
  }

  private int[][] currPuzzle() {
    for (int i = 0; i < this.getActivePuzzle().getHeight(); i++) {
      for (int j = 0; j < this.getActivePuzzle().getWidth(); j++) {
        if (this.getActivePuzzle().getCellType(i, j) == CellType.CLUE) {
          this.printBoard[i][j] = this.getActivePuzzle().getClue(i, j);
        } else if (this.getActivePuzzle().getCellType(i, j) == CellType.CORRIDOR) {
          if (this.isLamp(i, j)) {
            this.printBoard[i][j] = 7;
          } else if (this.isLit(i, j)) {
            this.printBoard[i][j] = 8;
          } else {
            this.printBoard[i][j] = 6;
          }
        } else {
          this.printBoard[i][j] = 5;
        }
      }
    }
    return this.printBoard;
  }
}
