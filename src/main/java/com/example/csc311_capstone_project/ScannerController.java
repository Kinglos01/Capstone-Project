package com.example.csc311_capstone_project;

import com.example.csc311_capstone_project.db.ConnDbOps;
import com.example.csc311_capstone_project.model.Invoice;
import com.example.csc311_capstone_project.model.Status;
import com.example.csc311_capstone_project.service.CurrentUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScannerController {

    protected final ObservableList<Invoice> invoices = FXCollections.observableArrayList();

    @FXML
    protected Button generateButton;

    @FXML
    protected Button addButton;

    protected String currImage;

    ConnDbOps db = new ConnDbOps();

    @FXML
    protected ImageView invoiceImage;

    @FXML
    protected TextField invoiceNumField, accountIDField, orderDateField, deliveryDateField, statusField, shippingAddressField, invoiceNameField, itemsField;

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
            canCreate = false;
        }

        if(!invoiceMatcher.matches()){
            System.out.println("Error: The invoice id must be 'IN' followed by 8 digits");
            canCreate = false;
        }

        if(!customerMatcher.matches()){
            System.out.println("Error: The customer id must be 'CUS' followed by 7 digits");
            canCreate = false;
        }

        if(!orderDateMatcher.matches()){
            System.out.println("Error: The order date must follow the MM-DD-YYYY format, dashes included");
            canCreate = false;
        }

        if(!deliveryDateMatcher.matches() && !inDeliv.isEmpty()){
            System.out.println("Error: Any delivery date must follow the MM-DD-YYYY format, dashes included");
            canCreate = false;
        }

        if(!addressMatcher.matches()){
            System.out.println("Error: Address must be a street address, a city name, a state abbreviation, and a 5 digit zip code");
            canCreate = false;
        }

        if(!statusMatcher.matches()){
            System.out.println("Error: Status must be 'Delivered', 'En-Route', 'Not Delivered', or 'Unknown', exactly");
            canCreate = false;
        }

        for (Invoice invoice : invoices) {
            if (invoice.getInvoiceId().equals(inNum)) {
                System.out.println("Error: An invoice with this id already exists");
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
            invoiceNameField.setText("");
            statusField.setText("");
            accountIDField.setText("");
            shippingAddressField.setText("");
            orderDateField.setText("");
            deliveryDateField.setText("");
            itemsField.setText("");
            invoiceImage.setImage(null);
            currImage = null;

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

        File file = (new FileChooser()).showOpenDialog(invoiceImage.getScene().getWindow());

        if(file != null) {
            invoiceImage.setImage(new Image(file.toURI().toString()));
        }

        currImage = file.toURI().toString();
    }
}