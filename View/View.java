package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class View implements FXComponent {
  private final AlternateMvcController controller;

  public View(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    StackPane layout = new StackPane();

    PuzzleView puzzleView = new PuzzleView(controller);
    layout.getChildren().add(puzzleView.render());

    return layout;
  }
}
