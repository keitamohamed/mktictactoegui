package com.keita.controller;

import com.keita.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class Controller {
    @FXML
    Pane pane;
    @FXML
    Label drawWin, drawScore, playerOne, playerOneScore, playerTwoScore, playerTwo;
    @FXML
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9;

    private Button[][] buttons = new Button[3][3];

    @FXML
    public void initialize() {
        drawWin.setText(drawWin.getText().toUpperCase());
        playerOne.setText(playerOne.getText().toUpperCase());
        playerTwo.setText(playerTwo.getText().toUpperCase());

        // Initialize the button
        initializeButton();
        nameInputStage();
    }

    private void initializeButton() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (i == 0) {
                    if (j == 0) {
                        button1.setText("E1");
                        buttons[i][j] = button1;
                    }
                    else if (j == 1) {
                        button2.setText("E2");
                        buttons[i][j] = button2;
                    }
                    else {
                        button3.setText("E3");
                        buttons[i][j] = button3;
                    }
                }else if (i == 1) {
                    if (j == 0) {
                        button4.setText("E4");
                        buttons[i][j] = button4;
                    }
                    else if (j == 1) {
                        button5.setText("E5");
                        buttons[i][j] = button5;
                    }
                    else {
                        button6.setText("E6");
                        buttons[i][j] = button6;
                    }
                }else {
                    if (j == 0) {
                        button7.setText("E7");
                        buttons[i][j] = button7;
                    }
                    else if (j == 1) {
                        button8.setText("E8");
                        buttons[i][j] = button8;
                    }
                    else {
                        button9.setText("E9");
                        buttons[i][j] = button9;
                    }
                }
            }
        }
    }



    private void nameInputStage() {
        //ImageView imageView = new ImageView(new Image(Main.class.getResourceAsStream("image/addnew.png")));
        TextInputDialog inputText = new TextInputDialog("walter");
        inputText.getDialogPane().setPrefSize(500, 200);
        inputText.getDialogPane().setStyle("-fx-background-color: #81878e;");

        inputText.setTitle("Name Input Dialog");
        inputText.setHeaderText(null);
        inputText.setGraphic(null);
        inputText.setContentText("Enter player one Name: ");
        inputText.getDialogPane().setStyle("-fx-text-fill: #fff;");
        Optional<String> result = inputText.showAndWait();



        if (result.isPresent())
            playerOne.setText(result.get());
        else
            System.exit(0);


        inputText.setContentText("Enter player two Name: ");
        result = inputText.showAndWait();
        if (result.isPresent())
            playerTwo.setText(result.get());
        else
            System.exit(0);
    }
}
