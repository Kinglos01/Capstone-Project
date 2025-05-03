package com.example.csc311_capstone_project;

import com.example.csc311_capstone_project.db.ConnDbOps;
import com.example.csc311_capstone_project.model.Invoice;
import com.example.csc311_capstone_project.model.Item;
import com.example.csc311_capstone_project.model.Status;
import com.example.csc311_capstone_project.service.CurrentUser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScannerController {

    protected final ObservableList<Invoice> invoices = FXCollections.observableArrayList();

    protected File file;
    @FXML
    protected Label errorLabel, invoiceNumError, accountIDError, orderDateError, deliveryDateError, statusError, shippingAddressError, invoiceNameError, itemsError, imageError;
    @FXML
    protected Button generateButton, addButton;
    @FXML
    protected String currImage;
    @FXML
    protected ImageView invoiceImage;
    @FXML
    protected TextField invoiceNumField, accountIDField, orderDateField, deliveryDateField, statusField, shippingAddressField, invoiceNameField, itemsField;

    static ConnDbOps db = new ConnDbOps();

    @FXML
    protected void initialize() {
        Pattern invoicePattern = Pattern.compile("IN\\d{8}");
        Pattern customerPattern = Pattern.compile("CUS\\d{7}");
        Pattern datePattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");
        Pattern addressPattern = Pattern.compile("[^,]+,[^,]+,\\s?[A-Z]{2},\\s?\\d{5}");
        Pattern statusPattern = Pattern.compile("Delivered|En-Route|Not Delivered|Unknown");

        // Invoice Number Field, Error Messages
        invoiceNumField.textProperty().addListener((obs, oldText, newText) -> {
            boolean valid = invoicePattern.matcher(newText).matches();
            invoiceNumField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
        });
        invoiceNumField.focusedProperty().addListener((obs, oldFocus, newFocus) -> {
            if (!newFocus) {
                boolean valid = invoicePattern.matcher(invoiceNumField.getText()).matches();
                invoiceNumError.setText(valid ? "" : "Must be 'IN' followed by 8 digits");
                invoiceNumField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
            }
        });

        // Account ID Field, Error Messages
        accountIDField.textProperty().addListener((obs, oldText, newText) -> {
            boolean valid = customerPattern.matcher(newText).matches();
            accountIDField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
        });
        accountIDField.focusedProperty().addListener((obs, oldFocus, newFocus) -> {
            if (!newFocus) {
                boolean valid = customerPattern.matcher(accountIDField.getText()).matches();
                accountIDError.setText(valid ? "" : "Must be 'CUS' followed by 7 digits");
                accountIDField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
            }
        });

        // Delivery Date Field, Error Messages
        deliveryDateField.textProperty().addListener((obs, oldText, newText) -> {
            boolean valid = datePattern.matcher(newText).matches();
            deliveryDateField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
        });
        deliveryDateField.focusedProperty().addListener((obs, oldFocus, newFocus) -> {
            if (!newFocus) {
                boolean valid = datePattern.matcher(deliveryDateField.getText()).matches();
                deliveryDateError.setText(valid ? "" : "Date must be in MM-DD-YYYY format.");
                deliveryDateField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
            }
        });

        // Order Date Field, Error Messages
        orderDateField.textProperty().addListener((obs, oldText, newText) -> {
            boolean valid = datePattern.matcher(newText).matches();
            orderDateField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
        });
        orderDateField.focusedProperty().addListener((obs, oldFocus, newFocus) -> {
            if (!newFocus) {
                boolean valid = datePattern.matcher(orderDateField.getText()).matches();
                orderDateError.setText(valid ? "" : "Date must be in MM-DD-YYYY format.");
                orderDateField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
            }
        });

        // Shipping Address Field, Error Messages
        shippingAddressField.textProperty().addListener((obs, oldText, newText) -> {
            boolean valid = addressPattern.matcher(newText).matches();
            shippingAddressField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
        });
        shippingAddressField.focusedProperty().addListener((obs, oldFocus, newFocus) -> {
            if (!newFocus) {
                boolean valid = addressPattern.matcher(shippingAddressField.getText()).matches();
                shippingAddressError.setText(valid ? "" : "Enter full address as: Street, City, ST, ZIP.");
                shippingAddressField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
            }
        });

        // Shipping Status Field, Error Messages
        statusField.textProperty().addListener((obs, oldText, newText) -> {
            boolean valid = statusPattern.matcher(newText).matches();
            statusField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
        });
        statusField.focusedProperty().addListener((obs, oldFocus, newFocus) -> {
            if (!newFocus) {
                boolean valid = statusPattern.matcher(statusField.getText()).matches();
                statusError.setText(valid ? "" : "Must be one of: Delivered, En-Route,\nNot Delivered, or Unknown.");
                statusField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
            }
        });
    }


    @FXML
    protected void addInvoice() {

        db.connectToDatabase();
        db.setCurrentUser(CurrentUser.getCurrentUsername(), CurrentUser.getCurrentEmail());

        boolean canCreate = true;
        String inNum = invoiceNumField.getText();
        String inAccount = accountIDField.getText();
        String inOrder = orderDateField.getText();
        String inDeliv = deliveryDateField.getText();
        String inAddress = shippingAddressField.getText();
        String inStat = statusField.getText();
        String inName = invoiceNameField.getText();
        String inItems = itemsField.getText();

        Pattern invoicePattern = Pattern.compile("IN\\d{8}");
        Matcher invoiceMatcher = invoicePattern.matcher(inNum);
        Pattern customerPattern = Pattern.compile("CUS\\d{7}");
        Matcher customerMatcher = customerPattern.matcher(inAccount);
        Pattern datePattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");
        Matcher orderDateMatcher = datePattern.matcher(inOrder);
        Matcher deliveryDateMatcher = datePattern.matcher(inDeliv);
        Pattern addressPattern = Pattern.compile("[^,]+,[^,]+,\\s?[A-Z]{2},\\s?\\d{5}");
        Matcher addressMatcher = addressPattern.matcher(inAddress);
        Pattern statusPattern = Pattern.compile("Delivered|En-Route|Not Delivered|Unknown");
        Matcher statusMatcher = statusPattern.matcher(inStat);

        if (inNum.isEmpty() || inAccount.isEmpty() || inOrder.isEmpty() || inStat.isEmpty() || inAddress.isEmpty() || currImage == null || inItems.isEmpty()) {
            System.out.println("Error: One or more fields do not have inputs");
            errorLabel.setText("All fields must be filled."); // Print error to UI
            canCreate = false;
        }

        if (currImage == null) {
            imageError.setText("Please add an image of the invoice.");
        }
        else imageError.setText("");

        if(!invoiceMatcher.matches()){
            System.out.println("Error: The invoice id must be 'IN' followed by 8 digits");
            invoiceNumError.setText("Must be 'IN' followed by 8 digits");
            invoiceNumField.setStyle("-fx-border-color: red;");
            canCreate = false;
        }

        if(!customerMatcher.matches()){
            System.out.println("Error: The customer id must be 'CUS' followed by 7 digits");
            accountIDError.setText("Must be 'CUS' followed by 7 digits");
            accountIDField.setStyle("-fx-border-color: red;");
            canCreate = false;
        }

        if(!orderDateMatcher.matches()){
            System.out.println("Error: The order date must follow the MM-DD-YYYY format, dashes included");
            orderDateError.setText("Date must be in MM-DD-YYYY format.");
            orderDateField.setStyle("-fx-border-color: red;");
            canCreate = false;
        }

        if(!deliveryDateMatcher.matches()){
            System.out.println("Error: Any delivery date must follow the MM-DD-YYYY format, dashes included");
            deliveryDateError.setText("Date must be in MM-DD-YYYY format.");
            deliveryDateField.setStyle("-fx-border-color: red;");
            canCreate = false;
        }

        if(!addressMatcher.matches()){
            System.out.println("Error: Address must be a street address, a city name, a state abbreviation, and a 5 digit zip code");
            shippingAddressError.setText("Enter full address as: Street, City, ST, ZIP.");
            shippingAddressField.setStyle("-fx-border-color: red;");
            canCreate = false;
        }

        if(!statusMatcher.matches()){
            System.out.println("Error: Status must be 'Delivered', 'En-Route', 'Not Delivered', or 'Unknown', exactly");
            statusError.setText("Must be one of: Delivered, En-Route,\nNot Delivered, or Unknown.");
            statusField.setStyle("-fx-border-color: red;");
            canCreate = false;
        }

        for (Invoice invoice : invoices) {
            if (invoice.getInvoiceId().equals(inNum)) {
                System.out.println("Error: An invoice with this id already exists");
                invoiceNumError.setText("An invoice with this id already exists");
                invoiceNumField.setStyle("-fx-border-color: red;");
                canCreate = false;
            }
        }

        if (canCreate) {
            switch(inStat) {
                case "Delivered" -> {
                    Invoice invoice = new Invoice(inNum, inAccount, inOrder, inDeliv, inAddress, inName, inItems, invoiceImage.getImage(), Status.delivered);
                    LandingController.addInvoices().add(invoice);
                    db.insertInvoice(inNum, inOrder, inDeliv, "Delivered", inAccount, inAddress, currImage, inName, invoice.getPrice());
                }
                case "En-Route" -> {
                    Invoice invoice = new Invoice(inNum, inAccount, inOrder, inDeliv, inAddress, inName, inItems, invoiceImage.getImage(), Status.en_route);
                    LandingController.addInvoices().add(invoice);
                    db.insertInvoice(inNum, inOrder, inDeliv, "En-Route", inAccount, inAddress, currImage, inName, invoice.getPrice());
                }
                case "Not Delivered" -> {
                    Invoice invoice = new Invoice(inNum, inAccount, inOrder, inDeliv, inAddress, inName, inItems, invoiceImage.getImage(), Status.not_delivered);
                    LandingController.addInvoices().add(invoice);
                    db.insertInvoice(inNum, inOrder, inDeliv, "Not Delivered", inAccount, inAddress, currImage, inName, invoice.getPrice());
                }
                default -> {
                    Invoice invoice = new Invoice(inNum, inAccount, inOrder, inDeliv, inAddress, inName, inItems, invoiceImage.getImage(), Status.unknown);
                    LandingController.addInvoices().add(invoice);
                    db.insertInvoice(inNum, inOrder, inDeliv, "Unknown", inAccount, inAddress, currImage, inName, invoice.getPrice());
                }

            }

            invoiceNumField.setText("");
            invoiceNumField.setStyle("");
            invoiceNameField.setText("");
            invoiceNameField.setStyle("");
            statusField.setText("");
            statusField.setStyle("");
            accountIDField.setText("");
            accountIDField.setStyle("");
            shippingAddressField.setText("");
            shippingAddressField.setStyle("");
            orderDateField.setText("");
            orderDateField.setStyle("");
            deliveryDateField.setText("");
            deliveryDateField.setStyle("");
            itemsField.setText("");
            itemsField.setStyle("");
            invoiceImage.setImage(null);
            currImage = null;
            errorLabel.setText("");

        }

    }

    /***
     * Changes the image stored in the viewer to the picture of the invoice
     * for the users choosing.
     * @since 4/24/2025
     * @author Nathaniel Rivera
     */
    @FXML
    protected void imageChange() {

        file = (new FileChooser()).showOpenDialog(invoiceImage.getScene().getWindow());

        if (file != null) {
            invoiceImage.setImage(new Image(file.toURI().toString()));
        }

        currImage = file.toURI().toString();
    }

    @FXML
    protected void addFromScanner() {
        FileReader fileReader = new FileReader(file.getPath());
        String fullText = fileReader.getTextFromFile();
        System.out.println(fullText);
        Pattern invoicePattern = Pattern.compile("IN\\d{8}");
        Matcher invoiceMatcher = invoicePattern.matcher(fullText);
        Pattern customerPattern = Pattern.compile("CUS\\d{7}");
        Matcher customerMatcher = customerPattern.matcher(fullText);
        Pattern datePattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");
        Matcher orderDateMatcher = datePattern.matcher(fullText);
        Matcher deliveryDateMatcher = datePattern.matcher(fullText);
        Pattern addressPattern = Pattern.compile("\\s[1-9]+ [^,]+,[^,]+,\\s?[A-Z]{2},\\s?\\d{5}");
        Matcher addressMatcher = addressPattern.matcher(fullText);
        Pattern statusPattern = Pattern.compile("Delivered|En-Route|Not Delivered|Unknown");
        Matcher statusMatcher = statusPattern.matcher(fullText);


        // build Invoice parameters from values found in the input pdf.
        invoiceMatcher.find();
        invoiceNumField.setText(String.valueOf(invoiceMatcher.group(0)));
        customerMatcher.find();
        accountIDField.setText(customerMatcher.group(0));
        orderDateMatcher.find();
        orderDateField.setText(orderDateMatcher.group(0));
        deliveryDateMatcher.find();
        deliveryDateField.setText(deliveryDateMatcher.group(0));
        addressMatcher.find();
        shippingAddressField.setText(addressMatcher.group(0));
        statusMatcher.find();
        statusField.setText(statusMatcher.group(0));

        // build a list of Items for the Invoice.
        Pattern itemFinder = Pattern.compile("Total");
        Pattern itemFinderEnd = Pattern.compile("Subtotal");
        Matcher findEnd = itemFinder.matcher(fullText);
        Matcher findEnd2 = itemFinderEnd.matcher(fullText);
        Pattern newLineFinder = Pattern.compile("\\n");

        findEnd.find();
        findEnd2.find();
        int startingIndex = findEnd.end(0);
        int endingIndex = findEnd2.start(0);
        String itemsText = fullText.substring(startingIndex, endingIndex);
        Matcher newLineMatcher = newLineFinder.matcher(itemsText);
        List<String> itemsAsString = new ArrayList<String>();

        while (newLineMatcher.find()) {
            int startIndex = newLineMatcher.end();
            if (!newLineMatcher.find()) {
                //itemsAsString.add(itemsText.substring(startIndex));
                break;
            }
            else {
                int endIndex = newLineMatcher.start();
                itemsAsString.add(itemsText.substring(startIndex, endIndex));
                itemsText = itemsText.substring(endIndex - 1);
                newLineMatcher.reset(itemsText);
            }
        }

        Pattern itemName = Pattern.compile("[A-Za-z ()0-9-]+");
        Pattern quant = Pattern.compile(" \\d+ ");


        StringBuilder itemsFullString = new StringBuilder();
        for (String itemString : itemsAsString) {
            StringBuilder nameBuilder = new StringBuilder();
            Matcher nameMatcher = itemName.matcher(itemString);
            nameMatcher.find();
            nameBuilder.append(nameMatcher.group());
            nameBuilder.deleteCharAt(nameMatcher.group().length() - 1);
            String name = String.valueOf(nameBuilder);

            Matcher quantMatcher = quant.matcher(itemString);
            quantMatcher.find();
            String quantity = quantMatcher.group().substring(1, quantMatcher.group().length() - 1);

            itemsFullString.append(name).append(":").append(quantity).append(", ");
        }

        itemsFullString.deleteCharAt(itemsFullString.length() - 1);
        itemsField.setText(String.valueOf(itemsFullString));
    }
}