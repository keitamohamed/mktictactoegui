package com.keita.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;

import java.util.List;
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
    private String turn;
    private boolean winnerFound;

    @FXML
    public void initialize() {
        drawWin.setText(drawWin.getText().toUpperCase());
        playerOne.setText(playerOne.getText().toUpperCase());
        playerTwo.setText(playerTwo.getText().toUpperCase());

        // Initialize the button
        initializeButton();
        nameInputStage();
        actionListener(dashboard);

        endGame.setOnAction(e -> System.exit(0));
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
                    }else {
                        button3.setText("E3");
                        dashboard[i][j] = button3;
                    }
                } else if (i == 1) {
                        if (j == 0) {
                            button4.setText("E4");
                            dashboard[i][j] = button4;
                        }else if (j == 1) {
                            button5.setText("E5");
                            dashboard[i][j] = button5;
                        }
                        else {
                            button6.setText("E6");
                            dashboard[i][j] = button6;
                        }
                }else{
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

    private void actionListener(Button[][] dashboard) {
        for (int c = 0; c < dashboard.length; c++) {
            final int i = c;
            for (int b = 0; b < dashboard[c].length; b++) {
                final int j = b;
                dashboard[c][j].setOnAction(e -> {
                    dashboard[i][j].setText(turn);
                    dashboard[i][j].setDisable(true);
                    if (checkForWinner(dashboard)) {
                        winnerFound = false;
                        enable();
                    }
                    else {
                        changePlayerTurn();
                        disable();
                    }

                });
            }
        }
    }

    private boolean checkForWinner(Button[][] dashboard) {

        if (winnerByHorizontalLine(dashboard) || winnerByVerticalLineTest(dashboard) ||
                winnerByNinetyDegreeAngle(dashboard)) {
            recordScore(playerOneScore, playerTwoScore, turn);
            disableDashboard(dashboard);
            Winner(getPlayerName(turn) + " win the game!");
            return winnerFound;
        }
        else if (drawGame(dashboard)) {
            this.drawScore.setText(String.valueOf(Integer.parseInt(drawScore.getText()) + 1));
            Winner("It's a draw game!");
            return true;
        }

        return winnerFound;
    }

    private boolean winnerByHorizontalLine(Button[][] dashboard) {
        for (int c = 0; c < dashboard.length; c++) {
            winnerFound = true;
            for (int r = 0; r < dashboard[c].length; r++) {
                if (!dashboard[c][r].getText().equals(turn))
                    winnerFound = false;
            }
            if (winnerFound) {
                return winnerFound;
            }
        }
        return winnerFound;
    }

    private boolean winnerByVerticalLineTest(Button[][] dashboard) {

        for (int column = 0; column < dashboard.length; column++) {
            winnerFound = true;
            for (int i = 0; i < dashboard.length; i++) {
                if (!dashboard[i][column].getText().equals(turn)) {
                    winnerFound = false;
                }
            }

            if (winnerFound)
                return winnerFound;
        }
        return winnerFound;
    }

    private boolean winnerByNinetyDegreeAngle(Button[][] dashBoard) {
        int size = 0;
        for (int column = 0; column < dashBoard.length - 1; column++) {
            winnerFound = true;
            for (int i = 0; i < dashBoard.length; i++) {
                if (!dashBoard[i][size].getText().equals(turn)) {
                    winnerFound = false;
                }

                if (column > 0) {
                    size--;
                }
                else {
                    size++;
                }
            }

            if (!winnerFound && column >= 1) {
                size = dashBoard.length - 1;
            }else  {
                size = dashBoard.length - 1;
            }

            if (winnerFound) {
                return winnerFound;
            }
        }
        return false;
    }

    private boolean drawGame(Button[][] dashboard) {
        for (int c = 0; c < dashboard.length; c++) {
            for (int r = 0; r < dashboard[c].length; r++) {
                if (!dashboard[c][r].getText().equals("X") && !dashboard[c][r].getText().equals("O"))
                    return false;
            }
        }
        return true;
    }

    private void recordScore(Label playerOneScore, Label playerTwoScore, String turn) {
        if (turn.equals("X"))
            this.playerOneScore.setText(String.valueOf((Integer.parseInt(playerOneScore.getText()) + 1)));
        else
            this.playerTwoScore.setText(String.valueOf(Integer.parseInt(playerTwoScore.getText()) + 1));
    }


    private void nameInputStage() {
        this.turn = "X";
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
            disable();
            playerTurn.setText(getPlayerName(turn) + ": make a move to an empty space");

        }
        else
            System.exit(0);
    }

    @FXML
    private void setReMatch() {
        enableDashboard();
        initializeButton();
        changePlayerTurn();
        disable();
    }

    @FXML
    private void startNewGame() {
        nameInputStage();
        enableDashboard();
        initializeButton();
        disable();
        playerOneScore.setText("0");
        playerTwoScore.setText("0");
        drawScore.setText("0");
    }

    private String getPlayerName(String playerTurn) {
        if (playerTurn.equals("X"))
            return playerOne.getText();
        return playerTwo.getText();
    }

    private void changePlayerTurn() {
        this.turn = (turn.equals("X")) ? "O" : "X";
        playerTurn.setText(getPlayerName(this.turn) + ": make a move to an empty space");
    }

    private void disable() {
        reMatch.setVisible(false);
        newGame.setVisible(false);
    }
    private void enable() {
        reMatch.setVisible(true);
        newGame.setVisible(true);
    }

    private void enableDashboard() {
        for (int c = 0; c < dashboard.length; c++)
            for (int r = 0; r < dashboard[c].length; r++)
                dashboard[c][r].setDisable(false);
    }

    private void disableDashboard(Button[][] dashboard) {
        for (int c = 0; c < dashboard.length; c++)
            for (int r = 0; r < dashboard[c].length; r++)
                dashboard[c][r].setDisable(true);
    }

    private void Winner(String message) {
        playerTurn.setText(message);
    }

}
