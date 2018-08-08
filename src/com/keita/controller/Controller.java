package com.keita.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;

import java.util.Optional;

public class Controller {
    @FXML
    Pane pane;
    @FXML
    Label drawWin, drawScore, playerOne, playerOneScore, playerTwoScore, playerTwo, playerTurn;
    @FXML
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    @FXML
    Button reMatch, newGame, endGame;

    private Button[][] dashboard = new Button[3][3];
    private String turn = "X";

    @FXML
    public void initialize() {
        drawWin.setText(drawWin.getText().toUpperCase());
        playerOne.setText(playerOne.getText().toUpperCase());
        playerTwo.setText(playerTwo.getText().toUpperCase());

        // Initialize the button
        initializeButton();
        nameInputStage();
        actionListener();
    }

    private void initializeButton() {

        for (int i = 0; i < dashboard.length; i++) {
            for (int j = 0; j < dashboard[i].length; j++) {
                if (i == 0) {
                    if (j == 0) {
                        button1.setText("E1");
                        dashboard[i][j] = button1;
                    }
                    else if (j == 1) {
                        button2.setText("E2");
                        dashboard[i][j] = button2;
                    }
                    else {
                        button3.setText("E3");
                        dashboard[i][j] = button3;
                    }
                }else if (i == 1) {
                    if (j == 0) {
                        button4.setText("E4");
                        dashboard[i][j] = button4;
                    }
                    else if (j == 1) {
                        button5.setText("E5");
                        dashboard[i][j] = button5;
                    }
                    else {
                        button6.setText("E6");
                        dashboard[i][j] = button6;
                    }
                }else {
                    if (j == 0) {
                        button7.setText("E7");
                        dashboard[i][j] = button7;
                    }
                    else if (j == 1) {
                        button8.setText("E8");
                        dashboard[i][j] = button8;
                    }
                    else {
                        button9.setText("E9");
                        dashboard[i][j] = button9;
                    }
                }
            }
        }
    }

    private void actionListener() {
        button1.setOnAction(event -> {
            button1.setText(turn);
            winnerFound(dashboard);
            button1.setDisable(true);
        });
        button2.setOnAction(event -> {
            winnerFound(dashboard);
            button2.setText(turn);
            button2.setDisable(true);

        });
        button3.setOnAction(event -> {
            button3.setText(turn);
            winnerFound(dashboard);
            button3.setDisable(true);
        });
        button4.setOnAction(event -> {
            button4.setText(turn);
            winnerFound(dashboard);
            button4.setDisable(true);
        });
        button5.setOnAction(event -> {
            button5.setText(turn);
            winnerFound(dashboard);
            button5.setDisable(true);
        });
        button6.setOnAction(event -> {
            button6.setText(turn);
            winnerFound(dashboard);
            button6.setDisable(true);
        });
        button7.setOnAction(event -> {
            button7.setText(turn);
            winnerFound(dashboard);
            button7.setDisable(true);
        });

        button8.setOnAction(event -> {
            button8.setText(turn);
            winnerFound(dashboard);
            button8.setDisable(true);
        });
        button9.setOnAction(event -> {
            button9.setText(turn);
            winnerFound(dashboard);
            button9.setDisable(true);
        });

    }

    private boolean winnerFound(Button[][] dashboard) {

        this.turn = (turn.equals("X")) ? "O" : "X";
        playerTurn.setText(getPlayerName(this.turn) + ": make a move to an empty space");

        return false;
    }

    private void nameInputStage() {
        TextInputDialog inputText = new TextInputDialog("Enter name");
        inputText.getDialogPane().setPrefSize(500, 150);
        inputText.getDialogPane().setStyle("-fx-background-color: #2d394c;");
        inputText.getDialogPane().getContent().lookup(".content.label").setStyle("-fx-font-size: 18px;" +
                "-fx-text-fill: #fff");

        inputText.setTitle("Name Input Dialog");
        inputText.setHeaderText(null);
        inputText.setGraphic(null);
        inputText.setContentText("Enter player one Name: ");
        Optional<String> result = inputText.showAndWait();

        if (result.isPresent())
            playerOne.setText(result.get());
        else
            System.exit(0);


        inputText.setContentText("Enter player two Name: ");
        result = inputText.showAndWait();
        if (result.isPresent()) {
            playerTwo.setText(result.get());
            playerTurn.setText(getPlayerName(turn) + ": make a move to an empty space");

        }
        else
            System.exit(0);
    }

    private String getPlayerName(String playerTurn) {
        if (playerTurn.equals("X"))
            return playerOne.getText();
        return playerTwo.getText();
    }
}
