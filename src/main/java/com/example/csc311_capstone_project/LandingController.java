package com.example.csc311_capstone_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class LandingController{

    private final ObservableList<Invoice> invoices = FXCollections.observableArrayList();

    private boolean init = true;

    @FXML
    private TableView<Invoice> invoiceTableView;

    @FXML
    private TableColumn<Invoice, String> invoiceNum, accountID, orderDate, deliveryDate, status, shippingAddress;

    @FXML
    private TableColumn<Invoice, Item[]> item;

    @FXML
    private TextField invoiceNumField, accountIDField, orderDateField, deliveryDateField, statusField, shippingAddressField;


    //@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        invoiceNum.setCellValueFactory(new PropertyValueFactory<>("Invoice Number"));
        accountID.setCellValueFactory(new PropertyValueFactory<>("Account ID"));
        orderDate.setCellValueFactory(new PropertyValueFactory<>("Order Date"));
        deliveryDate.setCellValueFactory(new PropertyValueFactory<>("Delivery Date"));
        shippingAddress.setCellValueFactory(new PropertyValueFactory<>("Shipping Address"));
        status.setCellValueFactory(new PropertyValueFactory<>("Status"));

        //invoiceTableView.setItems(invoices);
    }

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

        if(canCreate) {
            invoices.add(new Invoice(inNum, inAccount, inOrder, inDeliv, inAddress, Status.not_delivered));
            if(init) {
                invoiceNum.setCellValueFactory(new PropertyValueFactory<>("Invoice Number"));
                accountID.setCellValueFactory(new PropertyValueFactory<>("Account ID"));
                orderDate.setCellValueFactory(new PropertyValueFactory<>("Order Date"));
                deliveryDate.setCellValueFactory(new PropertyValueFactory<>("Delivery Date"));
                shippingAddress.setCellValueFactory(new PropertyValueFactory<>("Shipping Address"));
                status.setCellValueFactory(new PropertyValueFactory<>("Status"));

                invoiceTableView.setItems(invoices);
                init = false;
            }
        }
    }
}