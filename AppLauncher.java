package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.model.*;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  //  Create your Model, View, and Controller instances and launch your GUI

  @Override
  public void start(Stage stage) {
    PuzzleLibrary puzzles = new PuzzleLibraryImpl();
    puzzles.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_02));
    puzzles.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_05));
    puzzles.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_01));
    puzzles.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_03));
    puzzles.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_04));

    Model model = new ModelImpl(puzzles);
    AlternateMvcController controller = new ControllerImpl(model);

    ModelObserver observer =
        (Model m) -> {
          Pane layout = new VBox();
          layout.getStyleClass().add("layout");

          Pane scoreboard = new HBox();
          scoreboard.getStyleClass().add("scoreboard");
          layout.getChildren().add(scoreboard);

          GridPane board = new GridPane();
          board.setHgap(1);
          board.setVgap(1);
          board.getStyleClass().add("board");

          for (int i = 0; i < controller.getActivePuzzle().getHeight(); i++) {
            for (int j = 0; j < controller.getActivePuzzle().getWidth(); j++) {
              int row = i;
              int col = j;
              Button cell = new Button();
              if (controller.getActivePuzzle().getCellType(row, col) == CellType.WALL) {
                cell.setStyle("-fx-border-color: black; -fx-background-color: black");
                cell.setPrefSize(50, 50);
              } else if ((controller.getActivePuzzle().getCellType(row, col) == CellType.CLUE)) {
                cell.setStyle("-fx-border-color: black; -fx-background-color: black");
                cell.setText(String.valueOf(controller.getActivePuzzle().getClue(row, col)));
                cell.setFont(new Font("Astonished", 15));
                cell.setTextFill(Color.WHITE);
                cell.setPrefSize(50, 50);
              } else if (controller.isLamp(row, col)) {
                Image img = new Image("light-bulb.png");
                ImageView view = new ImageView(img);
                view.setFitHeight(30);
                view.setPreserveRatio(true);
                Button button = new Button();
                button.setPrefSize(30, 30);
                button.setGraphic(view);
                cell = button;
                //                cell = makeLamp();
                cell.setStyle("-fx-border-color: black; -fx-background-color: yellow");
                //                cell.setText("LAMP");
                //                cell.setFont(new Font(12));
              } else if (controller.isLit(row, col)) {
                cell.setStyle("-fx-border-color: black; -fx-background-color: yellow");
              } else {
                cell.setStyle("-fx-border-color: black; -fx-background-color: white");
              }
              cell.setPrefSize(50, 50);

              board.add(cell, col, row);

              cell.setOnAction(
                  (ActionEvent) -> {
                    controller.clickCell(row, col);
                  });
            }
          }

          //    Scene scene = new Scene(layout, 250, 250);
          //    scene.getStylesheets().add("main.css");
          //    stage.setScene(scene);
          //    stage.show();

          // ------------------------------------------------------------------------------------------------------//
          // Controller View
          GridPane controlPanel = new GridPane();
          controlPanel.setHgap(7);
          controlPanel.setVgap(7);

          Button previous = new Button("Previous ");
          previous.setStyle("-fx-background-color: green");
          previous.setOnAction(
              (ActionEvent event) -> {
                controller.clickPrevPuzzle();
                //                model.resetPuzzle();
                for (int i = 0; i < model.getActivePuzzle().getHeight(); i++) {
                  for (int j = 0; j < model.getActivePuzzle().getWidth(); j++) {
                    int row = i;
                    int col = j;
                    Button cell = new Button();
                    if (model.getActivePuzzle().getCellType(row, col) == CellType.WALL) {
                      cell.setStyle("-fx-border-color: black; -fx-background-color: black");
                      cell.setPrefSize(50, 50);
                    } else if (model.getActivePuzzle().getCellType(row, col) == CellType.CLUE) {
                      cell.setStyle("-fx-border-color: black; -fx-background-color: black");
                      cell.setText(String.valueOf(model.getActivePuzzle().getClue(row, col)));
                      cell.setFont(new Font("Astonished", 15));
                      cell.setTextFill(Color.WHITE);
                      cell.setPrefSize(50, 50);
                    } else if (model.isLamp(row, col)) {
                      cell.setStyle("-fx-border-color: black; -fx-background-color: yellow");
                    } else if (model.isLit(row, col)) {
                      cell.setStyle("-fx-border-color: black; -fx-background-color: yellow");
                    } else {
                      cell.setStyle("-fx-border-color: black; -fx-background-color: white");
                    }
                    cell.setPrefSize(50, 50);

                    board.add(cell, col, row);
                    cell.setOnAction(
                        (ActionEvent) -> {
                          controller.clickCell(row, col);
                        });
                  }
                }
              });

          Button next = new Button("Next");
          next.setStyle("-fx-background-color: pink");
          board.add(next, 20, 4);
          next.setOnAction(
              (ActionEvent event) -> {
                controller.clickNextPuzzle();
                //                model.resetPuzzle();
                for (int i = 0; i < model.getActivePuzzle().getHeight(); i++) {
                  for (int j = 0; j < model.getActivePuzzle().getWidth(); j++) {
                    int row = i;
                    int col = j;
                    Button cell = new Button();
                    if (model.getActivePuzzle().getCellType(row, col) == CellType.WALL) {
                      cell.setStyle("-fx-border-color: black; -fx-background-color: black");
                      cell.setPrefSize(50, 50);
                    } else if (model.getActivePuzzle().getCellType(row, col) == CellType.CLUE) {
                      cell.setStyle("-fx-border-color: black; -fx-background-color: black");
                      cell.setText(String.valueOf(model.getActivePuzzle().getClue(row, col)));
                      cell.setFont(new Font("Astonished", 15));
                      cell.setTextFill(Color.WHITE);
                      cell.setPrefSize(50, 50);
                    } else if (model.isLamp(row, col)) {
                      cell.setStyle("-fx-border-color: black; -fx-background-color: yellow");
                    } else if (model.isLit(row, col)) {
                      cell.setStyle("-fx-border-color: black; -fx-background-color: yellow");
                    } else {
                      cell.setStyle("-fx-border-color: black; -fx-background-color: white");
                    }
                    cell.setPrefSize(50, 50);

                    board.add(cell, col, row);

                    cell.setOnAction(
                        (ActionEvent) -> {
                          controller.clickCell(row, col);
                        });
                  }
                }
              });
          Button random = new Button("Random");
          random.setStyle("-fx-background-color: purple");
          random.setTextFill(Color.WHITE);
          random.setOnAction(
              (ActionEvent event) -> {
                controller.clickRandPuzzle();
              });

          Button clear = new Button("Reset");
          clear.setStyle("-fx-background-color: orange");
          clear.setOnAction(
              (ActionEvent event) -> {
                controller.clickResetPuzzle();
              });

          controlPanel.add(previous, 12, 3);
          controlPanel.add(next, 13, 3);
          controlPanel.add(random, 14, 3);
          controlPanel.add(clear, 15, 3);

          // ---------------------------------------------------------------------------------------------//
          // Info View
          if (controller.isSolved()) {
            String text =
                "You solved puzzle "
                    + (controller.getActivePuzzleIndex() + 1)
                    + " of "
                    + controller.getActivePuzzleLibrarySize();
            layout.getChildren().add(board);
            layout.getChildren().add(controlPanel);
            Scene scene = new Scene(layout, 700, 700);
            scene.getStylesheets().add("main.css");
            stage.setScene(scene);
            stage.show();
            Label message = new Label(text);
            message.setFont(new Font("Asynchronous", 50));
            message.setTextFill(Color.SKYBLUE);
            scene.setFill(Color.SPRINGGREEN);
            layout.getChildren().add(message);
          } else {
            String text =
                "Puzzle "
                    + (controller.getActivePuzzleIndex() + 1)
                    + " of "
                    + controller.getActivePuzzleLibrarySize();
            Label message = new Label(text);
            message.setFont(new Font(25));
            layout.getChildren().add(message);
          }

          layout.getChildren().add(board);
          layout.getChildren().add(controlPanel);
          Scene scene = new Scene(layout, 700, 700);
          scene.getStylesheets().add("main.css");
          stage.setScene(scene);
          stage.show();
        };
    model.addObserver(observer);
    observer.update(model);
  }

  public Button makeLamp() {
    Button lamp = new Button();
    lamp.setPrefSize(30, 30);
    lamp.getStyleClass().add("Lamp");
    lamp.setOnAction(
        actionEvent -> {
          ImageView imageView = new ImageView("Light-bulb.png");
          lamp.setGraphic(imageView);
          imageView.getStyleClass().add("imageView");
          imageView.setFitWidth(1);
          imageView.setFitHeight(1);
        });
    return lamp;
  }
}
