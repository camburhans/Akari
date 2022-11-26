package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.model.*;
import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class PuzzleView implements FXComponent {
  private AlternateMvcController controller;
  private Model model;

  public PuzzleView(AlternateMvcController controller) {
    PuzzleLibrary puzzles = new PuzzleLibraryImpl();
    puzzles.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_02));
    puzzles.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_01));
    puzzles.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_03));
    puzzles.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_04));
    puzzles.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_05));
    this.controller = controller;
  }

  @Override
  public Parent render() {
    //    GridPane grid = new GridPane();
    //    grid.setVgap(5);
    //    grid.setHgap(5);
    //
    //    for (int i = 0; i < this.model.getActivePuzzle().getHeight(); i++) {
    //      for (int j = 0; j < this.model.getActivePuzzle().getWidth(); j++) {
    //        final int row = i;
    //        final int col = j;
    //        String words = ""; //+ this.controller.getActivePuzzle().getClue(i, j);
    //        Button button = new Button(words);
    ////        Label label;
    ////        if (this.model.getActivePuzzle().getClue(i, j) != 5
    ////            && this.model.getActivePuzzle().getClue(i, j) != 6) {
    ////          label = new Label(String.valueOf(this.model.getActivePuzzle().getClue(i, j)));
    ////        } else {
    ////          label = new Label();
    ////        }
    ////
    ////        label.setMinHeight(25);
    ////        label.setMinWidth(25);
    //
    //        grid.add(button, row, col);
    //      }
    //    }
    return null;
  }
}
