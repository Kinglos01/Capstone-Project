package com.example.csc311_capstone_project;

import com.example.csc311_capstone_project.db.ConnDbOps;
import com.example.csc311_capstone_project.model.User;
import com.example.csc311_capstone_project.service.CurrentUser;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CapstoneApplication is the main handler for the Application.
 * It controls the startup of the program as well as the setup for
 * the login, register and landing pages.
 * @since 3/12/25
 * @author Nathaniel Rivera, Anthony Costa, Carlos Berio
 */
public class CapstoneApplication extends Application {

    public static List<User> userbase;

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
        stage.getIcons().add(new Image(Objects.requireNonNull(CapstoneApplication.class.getResourceAsStream("/com/example/csc311_capstone_project/images/colored_icon.png"))));
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
        ConnDbOps db = new ConnDbOps();
        db.connectToDatabase();
        userbase = db.retrieveUsers();

        Button launcher = new Button();
        launcher.setPrefWidth(300); launcher.setPrefHeight(100); launcher.setLayoutX(850); launcher.setLayoutY(550);
        launcher.setText("Launch");
        root.getChildren().add(launcher);
        launcher.setOnAction(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(CapstoneApplication.class.getResource("landing-view.fxml"));
            try {
                Stage landingStage = new Stage();
                AnchorPane landingRoot = new AnchorPane();
                landingRoot.getChildren().add(fxmlLoader.load());
                landingSetup(landingRoot, landingStage);

                Scene scene = new Scene(landingRoot, 1800, 800);
                landingStage.setScene(scene);
                landingStage.setResizable(false);

                stage.close();
                landingStage.getIcons().add(new Image(Objects.requireNonNull(CapstoneApplication.class.getResourceAsStream("/com/example/csc311_capstone_project/images/colored_icon.png"))));
                scene.getStylesheets().add(CapstoneApplication.class.getResource("/com/example/csc311_capstone_project/landing.css").toExternalForm());
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
                    loginStage.getIcons().add(new Image(Objects.requireNonNull(CapstoneApplication.class.getResourceAsStream("/com/example/csc311_capstone_project/images/colored_icon.png"))));
                    loginStage.show();
                } catch (IOException e) { }
            }
        });
    }

    /**
     * Sets up the interactable parts of the Login page.
     * These elements include the input fields for username and password, and the buttons for login and register.
     * @param loginRoot The AnchorPane for the login screen.
     * @param stage The stage the login scene is set in.
     * @author Nathaniel Rivera, Anthony Costa
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

        // Clear red border when user types in usernameField
        usernameField.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.isEmpty()) {
                usernameField.setStyle(""); // Reset to default
            }
        });

        // Clear red border when user types in emailField
        emailField.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.isEmpty()) {
                emailField.setStyle(""); // Reset to default
            }
        });

        // Clear red border when user types in passwordField
        passwordField.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.isEmpty()) {
                passwordField.setStyle(""); // Reset to default
            }
        });

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED); // Set text color to red
        errorLabel.setLayoutX(322); // Position near the input fields
        errorLabel.setLayoutY(300);
        loginRoot.getChildren().add(errorLabel);

        Button loginButton = new Button();
        loginButton.setPrefWidth(300); loginButton.setPrefHeight(40); loginButton.setLayoutX(322); loginButton.setLayoutY(260);
        loginButton.setText("LOGIN");
        loginRoot.getChildren().add(loginButton);
        loginButton.setOnAction(e -> {
            boolean canLogin = false;
            User currUser = null;
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();

            for(User user : userbase) {
                if(user.getUsername().equals(username) && user.getPassword().equals(password) && user.getEmail().equals(email)) {
                    currUser = user;
                    canLogin = true;
                    break;
                }
            }

            if(canLogin) {
                CurrentUser.setCurrentUser(currUser.getUsername(), currUser.getPassword());
                stage.close();
            } else {
                System.out.println("One of the following fields: Username, password, or email is incorrect");
                errorLabel.setText("Username, email, or password are incorrect."); // print error to UI
            }

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                System.out.println("Error: One or more fields do not have inputs");
                errorLabel.setText("All fields must be filled."); // Print error to UI

                // Highlight empty fields with a red border
                if (username.isEmpty()) usernameField.setStyle("-fx-border-color: red;");
                if (password.isEmpty()) passwordField.setStyle("-fx-border-color: red;");
                if (email.isEmpty()) emailField.setStyle("-fx-border-color: red;");
            }
        });

        loginButton.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                loginButton.setStyle("-fx-border-color: #039ED3;");
            } else {
                loginButton.setStyle(""); // Reset style
            }
        });

        Button close = new Button();
        close.setPrefWidth(25); close.setPrefHeight(25); close.setLayoutX(630); close.setLayoutY(10);
        close.setOpacity(0);
        loginRoot.getChildren().add(close);
        close.setOnAction(e -> {
            stage.close();
        });

        Label registerButton = new Label();
        registerButton.setId("register-label");
        registerButton.setLayoutX(502); registerButton.setLayoutY(324);
        loginRoot.getChildren().add(registerButton);
        registerButton.setText("Register here.");
        registerButton.setTextFill(Color.WHITE);
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
     * @author Nathaniel Rivera, Anthony Costa
     * @since 3/13/2025
     */
    public static void registerSetup(AnchorPane root, Stage stage) {
        ConnDbOps db = new ConnDbOps();
        db.connectToDatabase();
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(300);
        usernameField.setPrefHeight(40);
        usernameField.setLayoutX(322);
        usernameField.setLayoutY(70);
        usernameField.setPromptText("USERNAME");
        root.getChildren().add(usernameField);
        Label usernameError = new Label();
        usernameError.setTextFill(Color.RED); // Set text color to red
        usernameError.setLayoutX(322); // Position near the input fields
        usernameError.setLayoutY(110);
        root.getChildren().add(usernameError);

        Pattern userNamePattern = Pattern.compile("[\\w|-]{2,25}");
        // Live border coloring while typing
        usernameField.textProperty().addListener((obs, oldText, newText) -> {
            boolean valid = userNamePattern.matcher(newText).matches();
            usernameField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
        });
        // Show/hide error message on focus loss
        usernameField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                boolean valid = userNamePattern.matcher(usernameField.getText()).matches();
                usernameError.setText(valid ? "" : "2–25 characters, only letters, digits, or '-' allowed");
                usernameField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
            }
        });

        TextField emailField = new TextField();
        emailField.setPrefWidth(300);
        emailField.setPrefHeight(40);
        emailField.setLayoutX(322);
        emailField.setLayoutY(130);
        emailField.setPromptText("EMAIL");
        root.getChildren().add(emailField);
        Label emailError = new Label();
        emailError.setTextFill(Color.RED);
        emailError.setLayoutX(322);
        emailError.setLayoutY(170);
        root.getChildren().add(emailError);

        Pattern emailPattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
        // Live border coloring while typing
        emailField.textProperty().addListener((obs, oldText, newText) -> {
            boolean valid = emailPattern.matcher(newText).matches();
            emailField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
        });
        // Show/hide error message on focus loss
        emailField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                boolean valid = emailPattern.matcher(emailField.getText()).matches();
                emailError.setText(valid ? "" : "Must be a valid email address format");
                emailField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
            }
        });

        TextField passwordField = new TextField();
        passwordField.setPrefWidth(300);
        passwordField.setPrefHeight(40);
        passwordField.setLayoutX(322);
        passwordField.setLayoutY(190);
        passwordField.setPromptText("PASSWORD");
        root.getChildren().add(passwordField);
        Label passwordError = new Label();
        passwordError.setTextFill(Color.RED);
        passwordError.setLayoutX(322);
        passwordError.setLayoutY(230);
        root.getChildren().add(passwordError);

        Pattern passwordPattern = Pattern.compile("\\w{2,25}");
        // Live border coloring while typing
        passwordField.textProperty().addListener((obs, oldText, newText) -> {
            boolean valid = passwordPattern.matcher(newText).matches();
            passwordField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
        });
        // Show/hide error message on focus loss
        passwordField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                boolean valid = passwordPattern.matcher(passwordField.getText()).matches();
                passwordError.setText(valid ? "" : "2–25 characters, letters or digits only");
                passwordField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
            }
        });

        TextField firstNameField = new TextField();
        firstNameField.setPrefWidth(140);
        firstNameField.setPrefHeight(40);
        firstNameField.setLayoutX(322);
        firstNameField.setLayoutY(250);
        firstNameField.setPromptText("F. Name");
        root.getChildren().add(firstNameField);
        Label fNameError = new Label();
        fNameError.setTextFill(Color.RED);
        fNameError.setLayoutX(322);
        fNameError.setLayoutY(290);
        root.getChildren().add(fNameError);

        Pattern namePattern = Pattern.compile("\\w{2,25}+");
        // Live border coloring while typing
        firstNameField.textProperty().addListener((obs, oldText, newText) -> {
            boolean valid = namePattern.matcher(newText).matches();
            firstNameField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
        });
        // Show/hide error message on focus loss
        firstNameField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                boolean valid = namePattern.matcher(firstNameField.getText()).matches();
                fNameError.setText(valid ? "" : "2–25 letters only");
                firstNameField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
            }
        });

        TextField lastNameField = new TextField();
        lastNameField.setPrefWidth(140);
        lastNameField.setPrefHeight(40);
        lastNameField.setLayoutX(482);
        lastNameField.setLayoutY(250);
        lastNameField.setPromptText("L. Name");
        root.getChildren().add(lastNameField);
        Label lNameError = new Label();
        lNameError.setTextFill(Color.RED);
        lNameError.setLayoutX(482);
        lNameError.setLayoutY(290);
        root.getChildren().add(lNameError);

        // Live border coloring while typing
        lastNameField.textProperty().addListener((obs, oldText, newText) -> {
            boolean valid = namePattern.matcher(newText).matches();
            lastNameField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
        });
        // Show/hide error message on focus loss
        lastNameField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                boolean valid = namePattern.matcher(lastNameField.getText()).matches();
                lNameError.setText(valid ? "" : "2–25 letters only");
                lastNameField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
            }
        });

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED); // Set text color to red
        errorLabel.setLayoutX(322); // Position near the input fields
        errorLabel.setLayoutY(346);
        root.getChildren().add(errorLabel);

        Button registerButton = new Button();
        registerButton.setPrefWidth(300);
        registerButton.setPrefHeight(40);
        registerButton.setLayoutX(322);
        registerButton.setLayoutY(310);
        registerButton.setText("Register");
        root.getChildren().add(registerButton);

        registerButton.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                registerButton.setStyle("-fx-border-color: #039ED3;");
            } else {
                registerButton.setStyle(""); // Reset style
            }
        });

        registerButton.setOnAction(e -> {
            boolean canCreate = true;
            String username = usernameField.getText();
            String password = passwordField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();

            Matcher userNameMatcher = userNamePattern.matcher(username);
            Matcher passwordMatcher = passwordPattern.matcher(password);
            Matcher firstNameMatcher = namePattern.matcher(firstName);
            Matcher lastNameMatcher = namePattern.matcher(lastName);
            Matcher emailMatcher = emailPattern.matcher(email);

            if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                System.out.println("Error: One or more fields do not have inputs");
                errorLabel.setText("All fields must be filled."); // Print error to UI
                canCreate = false;

                // Highlight empty fields with red border
                if (username.isEmpty()) usernameField.setStyle("-fx-border-color: red;");
                if (password.isEmpty()) passwordField.setStyle("-fx-border-color: red;");
                if (firstName.isEmpty()) firstNameField.setStyle("-fx-border-color: red;");
                if (lastName.isEmpty()) lastNameField.setStyle("-fx-border-color: red;");
                if (email.isEmpty()) emailField.setStyle("-fx-border-color: red;");
            }

            if(!userNameMatcher.matches()){
                System.out.println("Error: Username needs to be within 2-25 characters, no special characters besides '-'");
                usernameError.setText("2–25 characters, only letters, digits, or '-' allowed");
                usernameField.setStyle("-fx-border-color: red;");
                canCreate = false;
            }

            if(!passwordMatcher.matches()){
                System.out.println("Error: Password needs to be within 2-25 characters, no special characters");
                passwordError.setText("2–25 characters, letters or digits only");
                passwordField.setStyle("-fx-border-color: red;");
                canCreate = false;
            }

            if(!firstNameMatcher.matches()){
                System.out.println("Error: First name needs to be within 2-25 letters, no other characters");
                fNameError.setText("2–25 letters only");
                firstNameField.setStyle("-fx-border-color: red;");
                canCreate = false;
            }

            if(!lastNameMatcher.matches()){
                System.out.println("Error: Last name needs to be within 2-25 letters, no other characters");
                lNameError.setText("2–25 letters only");
                lastNameField.setStyle("-fx-border-color: red;");
                canCreate = false;
            }

            if(!emailMatcher.matches()){
                System.out.println("Error: Invalid email input.  Please use a valid email address");
                emailError.setText("Must be a valid email address format");
                emailField.setStyle("-fx-border-color: red;");
                canCreate = false;
            }

            for (User user : userbase) {
                if (user.getEmail().equals(email) || user.getUsername().equals(username)) {
                    System.out.println("Error: This username or email is already in use");
                    errorLabel.setText("Error: Username or email already exists."); // Print error to UI
                    canCreate = false;
                }
            }

            if(canCreate) {
                userbase.add(new User(firstName, lastName, username, email, password));
                db.insertUser(username, email, password, firstName, lastName);
                stage.close();
            }
        });

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

        Label loginButton = new Label();
        loginButton.setId("login-label");
        loginButton.setLayoutX(539);
        loginButton.setLayoutY(356);
        root.getChildren().add(loginButton);
        loginButton.setText("Log in.");
        loginButton.setTextFill(Color.WHITE);
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

    /**
     * Sets ups the interactable parts of the landing page as well as the observable lists and
     * pathway to the scanner.
     * @param root The AnchorPane for the landing page.
     * @param landingStage The stage the landing page is set in
     * @since 3/26/2025
     * @author Nathaniel Rivera
     */
    public static void landingSetup(AnchorPane root, Stage landingStage) {
        Button addInvoice = new Button();
        addInvoice.setPrefWidth(160);
        addInvoice.setPrefHeight(50);
        addInvoice.setLayoutX(20);
        addInvoice.setLayoutY(600);
        addInvoice.setText("ADD INVOICE");
        root.getChildren().add(addInvoice);
        addInvoice.setOnMouseClicked(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(CapstoneApplication.class.getResource("scanner-view.fxml"));
            AnchorPane scannerRoot = new AnchorPane();
            try {
                Stage stage = new Stage();
                scannerRoot.getChildren().add(fxmlLoader.load());

                scannerSetup(scannerRoot, stage);
                Scene scene = new Scene(scannerRoot, 1100, 630);
                scene.getStylesheets().add(Objects.requireNonNull(CapstoneApplication.class.getResource("scannerscreen.css")).toExternalForm());
                stage.setScene(scene);
                stage.setResizable(false);
                stage.getIcons().add(new Image(Objects.requireNonNull(CapstoneApplication.class.getResourceAsStream("/com/example/csc311_capstone_project/images/colored_icon.png"))));

                stage.show();
            }  catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        /*
        Button removeInvoice = new Button();
        removeInvoice.setPrefWidth(160);
        removeInvoice.setPrefHeight(50);
        removeInvoice.setLayoutX(20);
        removeInvoice.setLayoutY(720);
        removeInvoice.setText("REMOVE INVOICE");
        root.getChildren().add(removeInvoice);*/

    }

    /**
     * Sets ups the interactable parts of the scanner page.
     * @param root The AnchorPane for the scanner page.
     * @param stage The stage the scanner page is set in
     * @since 3/26/2025
     * @author Nathaniel Rivera
     */
    public static void scannerSetup(AnchorPane root, Stage stage) {

        /*
        TextField invoiceNumberField = new TextField();
        invoiceNumberField.setPrefWidth(270); invoiceNumberField.setPrefHeight(30); invoiceNumberField.setLayoutX(430); invoiceNumberField.setLayoutY(120);
        invoiceNumberField.setPromptText("INVOICE#");
        root.getChildren().add(invoiceNumberField);

        TextField customerIdField = new TextField();
        customerIdField.setPrefWidth(270); customerIdField.setPrefHeight(30); customerIdField.setLayoutX(720); customerIdField.setLayoutY(120);
        customerIdField.setPromptText("CUSTOMER-ID");
        root.getChildren().add(customerIdField);

        TextField orderDateField = new TextField();
        orderDateField.setPrefWidth(270); orderDateField.setPrefHeight(30); orderDateField.setLayoutX(430); orderDateField.setLayoutY(230);
        orderDateField.setPromptText("ORDER-DATE");
        root.getChildren().add(orderDateField);

        TextField deliveryDateField = new TextField();
        deliveryDateField.setPrefWidth(270); deliveryDateField.setPrefHeight(30); deliveryDateField.setLayoutX(720); deliveryDateField.setLayoutY(230);
        deliveryDateField.setPromptText("DELIVERY-DATE");
        root.getChildren().add(deliveryDateField);

        TextField itemIdField = new TextField();
        itemIdField.setPrefWidth(270); itemIdField.setPrefHeight(30); itemIdField.setLayoutX(430); itemIdField.setLayoutY(340);
        itemIdField.setPromptText("ITEM-ID");
        root.getChildren().add(itemIdField);

        TextField shippingAddressField = new TextField();
        shippingAddressField.setPrefWidth(270); shippingAddressField.setPrefHeight(30); shippingAddressField.setLayoutX(720); shippingAddressField.setLayoutY(340);
        shippingAddressField.setPromptText("SHIPPING-ADDRESS");
        root.getChildren().add(shippingAddressField);

        TextField statusField = new TextField();
        statusField.setPrefWidth(270); statusField.setPrefHeight(30); statusField.setLayoutX(575); statusField.setLayoutY(430);
        statusField.setPromptText("STATUS");
        root.getChildren().add(statusField);


        ImageView invoicePic = new ImageView(new Image("C:\\Users\\nycpu\\IdeaProjects\\CSC311_Capstone_Project\\src\\main\\resources\\com\\example\\csc311_capstone_project\\images\\close_symbol.png"));
        ImageView invoicePic = new ImageView();
        invoicePic.setFitWidth(465);
        invoicePic.setFitHeight(600);
        invoicePic.setLayoutX(15);
        invoicePic.setLayoutY(15);
        root.getChildren().add(invoicePic);


        Button imageChanger = new Button();
        imageChanger.setPrefWidth(465);
        imageChanger.setPrefHeight(600);
        imageChanger.setLayoutX(15);
        imageChanger.setLayoutY(15);
        imageChanger.setOpacity(0.0);
        root.getChildren().add(imageChanger);
        imageChanger.setCursor(Cursor.HAND); // Set cursor to hand on hover
        imageChanger.setOnMouseClicked(e-> {
            File file = (new FileChooser()).showOpenDialog(stage.getScene().getWindow());
            if(file != null) {
                invoicePic.setImage(new Image(file.toURI().toString()));
            }
        });


        Button addInvoice = new Button();
        addInvoice.setPrefWidth(230);
        addInvoice.setPrefHeight(50);
        addInvoice.setLayoutX(740);
        addInvoice.setLayoutY(530);
        addInvoice.setText("ADD INVOICE");
        root.getChildren().add(addInvoice);
        */
    }
}
