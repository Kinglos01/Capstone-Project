package com.example.csc311_capstone_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
        stage.initStyle(StageStyle.TRANSPARENT);

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
        launcher.setText("Launch");
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
        close.setPrefWidth(25); close.setPrefHeight(25); close.setLayoutX(1160); close.setLayoutY(15);
        close.setOpacity(0);
        root.getChildren().add(close);

        close.setOnAction(e-> { stage.close(); });

        Button minimize = new Button();
        minimize.setPrefWidth(25); minimize.setPrefHeight(25); minimize.setLayoutX(1120); minimize.setLayoutY(15);
        minimize.setOpacity(0);
        root.getChildren().add(minimize);

        minimize.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ((Stage)((Button)actionEvent.getSource()).getScene().getWindow()).setIconified(true);
            }
        });

        Button login = new Button();
        login.setPrefWidth(25); login.setPrefHeight(25); login.setLayoutX(1080); login.setLayoutY(15);
        login.setOpacity(0);
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

                    Scene scene = new Scene(loginRoot, 650, 380);
                    loginStage.setScene(scene);
                    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("loginscreen.css")).toExternalForm());
                    loginStage.setResizable(false);
                    loginStage.initStyle(StageStyle.UNDECORATED);

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
        usernameField.setPrefWidth(300); usernameField.setPrefHeight(40); usernameField.setLayoutX(322); usernameField.setLayoutY(80);
        usernameField.setPromptText("USERNAME");
        loginRoot.getChildren().add(usernameField);

        TextField emailField = new TextField();
        emailField.setPrefWidth(300); emailField.setPrefHeight(40); emailField.setLayoutX(322); emailField.setLayoutY(140);
        emailField.setPromptText("EMAIL");
        loginRoot.getChildren().add(emailField);

        TextField passwordField = new TextField();
        passwordField.setPrefWidth(300); passwordField.setPrefHeight(40); passwordField.setLayoutX(322); passwordField.setLayoutY(200);
        passwordField.setPromptText("PASSWORD");
        loginRoot.getChildren().add(passwordField);

        Button close = new Button();
        close.setPrefWidth(25); close.setPrefHeight(25); close.setLayoutX(630); close.setLayoutY(10);
        close.setOpacity(0);
        loginRoot.getChildren().add(close);
        close.setOnAction(e -> {
            stage.close();
        });

        Button loginButton = new Button();
        loginButton.setPrefWidth(300); loginButton.setPrefHeight(40); loginButton.setLayoutX(322); loginButton.setLayoutY(260);
        loginButton.setText("Login");
        loginRoot.getChildren().add(loginButton);
        loginButton.setOnAction(e -> {
            stage.close();
        });

        Label registerButton = new Label();
        registerButton.setLayoutX(502); registerButton.setLayoutY(324);
        loginRoot.getChildren().add(registerButton);
        registerButton.setText("Register here.");
        registerButton.setOnMouseClicked(e -> {
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(CapstoneApplication.class.getResource("register-view.fxml"));
                    AnchorPane registerRoot = new AnchorPane();
                    try {
                        registerRoot.getChildren().add(fxmlLoader.load());

                        registerSetup(registerRoot, stage);
                        Scene scene = new Scene(registerRoot, 650, 380);
                        scene.getStylesheets().add(Objects.requireNonNull(CapstoneApplication.class.getResource("registerscreen.css")).toExternalForm());
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

   public static User person = new User();

    public static void registerSetup(AnchorPane root, Stage stage) {
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(300);
        usernameField.setPrefHeight(40);
        usernameField.setLayoutX(322);
        usernameField.setLayoutY(70);
        usernameField.setPromptText("USERNAME");
        root.getChildren().add(usernameField);

        TextField emailField = new TextField();
        emailField.setPrefWidth(300);
        emailField.setPrefHeight(40);
        emailField.setLayoutX(322);
        emailField.setLayoutY(130);
        emailField.setPromptText("EMAIL");
        root.getChildren().add(emailField);

        TextField passwordField = new TextField();
        passwordField.setPrefWidth(300);
        passwordField.setPrefHeight(40);
        passwordField.setLayoutX(322);
        passwordField.setLayoutY(190);
        passwordField.setPromptText("PASSWORD");
        root.getChildren().add(passwordField);

        TextField firstNameField = new TextField();
        firstNameField.setPrefWidth(140);
        firstNameField.setPrefHeight(40);
        firstNameField.setLayoutX(322);
        firstNameField.setLayoutY(250);
        firstNameField.setPromptText("f. Name");
        root.getChildren().add(firstNameField);

        TextField lastNameField = new TextField();
        lastNameField.setPrefWidth(140);
        lastNameField.setPrefHeight(40);
        lastNameField.setLayoutX(482);
        lastNameField.setLayoutY(250);
        lastNameField.setPromptText("l. Name");
        root.getChildren().add(lastNameField);

        Button close = new Button();
        close.setPrefWidth(25);
        close.setPrefHeight(25);
        close.setLayoutX(630);
        close.setLayoutY(10);
        close.setOpacity(0);
        root.getChildren().add(close);
        close.setOnAction(e -> {
            stage.close();
        });

        Button registerButton = new Button();
        registerButton.setPrefWidth(300);
        registerButton.setPrefHeight(40);
        registerButton.setLayoutX(322);
        registerButton.setLayoutY(310);
        registerButton.setText("Register");
        root.getChildren().add(registerButton);
        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = firstNameField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();



            if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                System.out.println("One or more fields do not have inputs");
            } else {
                person.setUsername(username);
                person.setPassword(password);
                person.setFirstName(firstName);
                person.setLastName(lastName);
                person.setEmail(email);
                stage.close();
            }
        });

        Label loginButton = new Label();
        loginButton.setLayoutX(539);
        loginButton.setLayoutY(356);
        root.getChildren().add(loginButton);
        loginButton.setText("Log in.");
        loginButton.setOnMouseClicked(e-> {
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(CapstoneApplication.class.getResource("login-view.fxml"));
            try {
                Stage loginStage = new Stage();
                AnchorPane loginRoot = new AnchorPane();
                loginRoot.getChildren().add(fxmlLoader.load());
                loginSetup(loginRoot, loginStage);

                Scene scene = new Scene(loginRoot, 650, 380);
                loginStage.setScene(scene);
                scene.getStylesheets().add(Objects.requireNonNull(CapstoneApplication.class.getResource("loginscreen.css")).toExternalForm());
                loginStage.setResizable(false);
                loginStage.initStyle(StageStyle.UNDECORATED);

                loginStage.show();
            } catch (IOException er) {
                throw new RuntimeException(er);
            }
        });
    }
}