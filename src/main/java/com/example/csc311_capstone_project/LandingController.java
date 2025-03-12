package com.example.csc311_capstone_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LandingController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}