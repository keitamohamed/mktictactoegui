package com.keita.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Controller {
    @FXML
    Pane pane;
    @FXML
    Label drawWin, drawScore, playerOne, playerOneScore, playerTwoScore, playerTwo;

    @FXML
    public void initialize() {
        drawWin.setText(drawWin.getText().toUpperCase());
        playerOne.setText(playerOne.getText().toUpperCase());
        playerTwo.setText(playerTwo.getText().toUpperCase());
    }
}
