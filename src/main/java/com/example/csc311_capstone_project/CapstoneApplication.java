package com.example.csc311_capstone_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class CapstoneApplication extends Application {

    /**
     * The initial start method for the program. Launches ands calls the setup for the splash screen.
     * @param stage The stage in which the splash screen is held
     * @throws IOException Throws an exception
     * @author Nathaniel Rivera
     * @since 3/12/2025
     */
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(CapstoneApplication.class.getResource("splash-view.fxml"));

        AnchorPane root = new AnchorPane();
        root.getChildren().add(fxmlLoader.load());
        splashSetup(root, stage);

        Scene scene = new Scene(root, 1200, 700);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("splashscreen.css")).toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.show();
    }

    /**
     * Main method of the program. Launches.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Sets up the interactable parts of the Splash page.
     * These elements include the system tray replacements, the login button and the launcher.
     * @param root The AnchorPane for the splash screen.
     * @param stage The stage the splash scene is set in.
     * @author Nathaniel Rivera
     * @since 3/12/2025
     */
    public static void splashSetup(AnchorPane root, Stage stage) {
        Button launcher = new Button();
        launcher.setPrefWidth(300); launcher.setPrefHeight(100); launcher.setLayoutX(850); launcher.setLayoutY(550);
        root.getChildren().add(launcher);
        launcher.setOnAction(e -> {
            Stage landingStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(CapstoneApplication.class.getResource("landing-view.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
                landingStage.setScene(scene);
                landingStage.setResizable(true);

                stage.close();
                landingStage.show();
            } catch(IOException _) {

            }
        });

        /*------------------------------------------System Tray Replacement Buttons------------------------------------------*/

        Button close = new Button();
        //Image closeImage = new Image("");
        //ImageView closeView = new ImageView(closeImage);
        close.setPrefWidth(25); close.setPrefHeight(25); close.setLayoutX(1160); close.setLayoutY(15);
        //close.setGraphic(closeView);
        root.getChildren().add(close);

        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });

        Button minimize = new Button();
        minimize.setPrefWidth(25); minimize.setPrefHeight(25); minimize.setLayoutX(1120); minimize.setLayoutY(15);
        root.getChildren().add(minimize);

        minimize.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ((Stage)((Button)actionEvent.getSource()).getScene().getWindow()).setIconified(true);
            }
        });

        Button login = new Button();
        login.setPrefWidth(25); login.setPrefHeight(25); login.setLayoutX(1080); login.setLayoutY(15);
        root.getChildren().add(login);

        /*------------------------------------------Login Handler------------------------------------------*/

        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader fxmlLoader = new FXMLLoader(CapstoneApplication.class.getResource("login-view.fxml"));
                try {
                    Stage loginStage = new Stage();
                    AnchorPane loginRoot = new AnchorPane();
                    loginRoot.getChildren().add(fxmlLoader.load());
                    loginSetup(loginRoot, loginStage);

                    Scene scene = new Scene(loginRoot, 500, 375);
                    loginStage.setScene(scene);
                    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("loginscreen.css")).toExternalForm());
                    loginStage.setResizable(false);

                    loginStage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * Sets up the interactable parts of the Login page.
     * These elements include the input fields for username and password, and the buttons for login and register.
     * @param loginRoot The AnchorPane for the login screen.
     * @param stage The stage the login scene is set in.
     * @author Nathaniel Rivera
     * @since 3/12/2025
     */
    public static void loginSetup(AnchorPane loginRoot, Stage stage) {
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(400); usernameField.setPrefHeight(30); usernameField.setLayoutX(50); usernameField.setLayoutY(100);
        loginRoot.getChildren().add(usernameField);

        TextField passwordField = new TextField();
        passwordField.setPrefWidth(400); passwordField.setPrefHeight(30); passwordField.setLayoutX(50); passwordField.setLayoutY(200);
        loginRoot.getChildren().add(passwordField);

        Button loginButton = new Button();
        loginButton.setPrefWidth(200); loginButton.setPrefHeight(75); loginButton.setLayoutX(25); loginButton.setLayoutY(285);
        loginRoot.getChildren().add(loginButton);
        loginButton.setOnAction(e -> {
            stage.close();
        });

        Button registerButton = new Button();
        registerButton.setPrefWidth(200); registerButton.setPrefHeight(75); registerButton.setLayoutX(275); registerButton.setLayoutY(285);
        loginRoot.getChildren().add(registerButton);
        registerButton.setOnAction(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(CapstoneApplication.class.getResource("register-view.fxml"));
            AnchorPane registerRoot = new AnchorPane();
            try {
                registerRoot.getChildren().add(fxmlLoader.load());

                registerSetup(registerRoot, stage);

                Scene scene = new Scene(registerRoot, 500, 575);
                stage.setScene(scene);
                stage.setResizable(false);

                stage.show();
            }  catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Sets up the interactable parts of the Login page.
     * These elements include the input fields for username, password, firstName, lastName and email, and the button to register.
     * @param root The AnchorPane for the register screen.
     * @param stage The stage the resister scene is set in.
     * @author Nathaniel Rivera
     * @since 3/13/2025
     */
    public static void registerSetup(AnchorPane root, Stage stage) {
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(400); usernameField.setPrefHeight(30); usernameField.setLayoutX(50); usernameField.setLayoutY(100);
        root.getChildren().add(usernameField);

        TextField passwordField = new TextField();
        passwordField.setPrefWidth(400); passwordField.setPrefHeight(30); passwordField.setLayoutX(50); passwordField.setLayoutY(200);
        root.getChildren().add(passwordField);

        TextField firstNameField = new TextField();
        firstNameField.setPrefWidth(190); firstNameField.setPrefHeight(30); firstNameField.setLayoutX(50); firstNameField.setLayoutY(300);
        root.getChildren().add(firstNameField);

        TextField lastNameField = new TextField();
        lastNameField.setPrefWidth(190); lastNameField.setPrefHeight(30); lastNameField.setLayoutX(260); lastNameField.setLayoutY(300);
        root.getChildren().add(lastNameField);

        TextField emailField = new TextField();
        emailField.setPrefWidth(400); emailField.setPrefHeight(30); emailField.setLayoutX(50); emailField.setLayoutY(400);
        root.getChildren().add(emailField);

        Button register = new Button();
        register.setPrefWidth(200); register.setPrefHeight(75); register.setLayoutX(150); register.setLayoutY(490);
        root.getChildren().add(register);
        register.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();

            if(username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                System.out.println("One or more fields do not have inputs");
            } else {
                stage.close();
            }
        });
    }
}