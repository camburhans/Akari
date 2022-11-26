package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Puzzle;
import com.comp301.a09akari.model.Model;

import java.util.Random;

public class ControllerImpl implements AlternateMvcController {
  private Model model;

  public ControllerImpl(Model model) {
    this.model = model;
    // Constructor code goes here
  }

  @Override
  public void clickNextPuzzle() {
    if (getActivePuzzleIndex() > model.getPuzzleLibrarySize()) {
      model.setActivePuzzleIndex(0);
    } else {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
    }
    model.getActivePuzzle();
    this.model.resetPuzzle();
  }

  @Override
  public void clickPrevPuzzle() {
    if (this.model.getPuzzleLibrarySize() > 0) {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
    } else {
      model.setActivePuzzleIndex(model.getPuzzleLibrarySize() - 1);
    }
    this.model.resetPuzzle();
  }

  @Override
  public void clickRandPuzzle() {
    Random rand = new Random();
    int randIndex = rand.nextInt(model.getPuzzleLibrarySize());

    while (randIndex == model.getActivePuzzleIndex()) {
      randIndex = rand.nextInt(model.getPuzzleLibrarySize());
    }

    model.setActivePuzzleIndex(randIndex);
    this.model.resetPuzzle();
  }

  @Override
  public void clickResetPuzzle() {
    this.model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (this.model.isLamp(r, c)) {
      this.model.removeLamp(r, c);
    } else {
      this.model.addLamp(r, c);
    }
  }

  @Override
  public boolean isLit(int r, int c) {
    return this.model.isLit(r, c);
  }

  @Override
  public boolean isLamp(int r, int c) {
    return this.model.isLamp(r, c);
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    return this.model.isClueSatisfied(r, c);
  }

  @Override
  public boolean isSolved() {
    return this.model.isSolved();
  }

  @Override
  public Puzzle getActivePuzzle() {
    return this.model.getActivePuzzle();
  }

  public int getActivePuzzleIndex() {
    return this.model.getActivePuzzleIndex();
  }

  public int getActivePuzzleLibrarySize() {
    return this.model.getPuzzleLibrarySize();
  }
}
