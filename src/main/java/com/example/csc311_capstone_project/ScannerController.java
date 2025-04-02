package com.example.csc311_capstone_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ScannerController {

    private final ObservableList<Invoice> invoices = FXCollections.observableArrayList();

    @FXML
    private TextField invoiceNumField, accountIDField, orderDateField, deliveryDateField, statusField, shippingAddressField;

    @FXML
    protected void addInvoice() {
        boolean canCreate = true;
        String inNum = invoiceNumField.getText();
        String inAccount = accountIDField.getText();
        String inOrder = orderDateField.getText();
        String inDeliv = deliveryDateField.getText();
        String inAddress = shippingAddressField.getText();
        String inStat = statusField.getText();


        if (inNum.isEmpty() || inAccount.isEmpty() || inOrder.isEmpty() || inDeliv.isEmpty() || inStat.isEmpty() || inAddress.isEmpty()) {
            System.out.println("Error: One or more fields do not have inputs");
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
                case "Delivered" -> invoices.add(new Invoice(inNum, inAccount, inOrder, inDeliv, inAddress, Status.delivered));
                case "En-Route" -> invoices.add(new Invoice(inNum, inAccount, inOrder, inDeliv, inAddress, Status.en_route));
                case "Not Delivered" -> invoices.add(new Invoice(inNum, inAccount, inOrder, inDeliv, inAddress, Status.not_delivered));
                default -> invoices.add(new Invoice(inNum, inAccount, inOrder, inDeliv, inAddress, Status.unknown));
            }
        }
    }
}
