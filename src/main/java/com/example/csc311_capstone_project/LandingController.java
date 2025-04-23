package com.example.csc311_capstone_project;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LandingController implements Initializable{

    private static ObservableList<Invoice> invoices = FXCollections.observableArrayList(new Invoice("I123", "C123", "01-01-1900", "01-01-1900", "Place", Status.unknown, "Test Invoice", "$ 0.0"));
    public Button removeButton;

    @FXML
    private TableView<Invoice> invoiceTable;

    @FXML
    private TableColumn<Invoice, String> invoiceName, invoiceNum, accountID, orderDate, deliveryDate, status, shippingAddress, price;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        invoiceName.setCellValueFactory(new PropertyValueFactory<>("InvoiceName"));
        invoiceNum.setCellValueFactory(new PropertyValueFactory<>("InvoiceId"));
        accountID.setCellValueFactory(new PropertyValueFactory<>("AccountId"));
        orderDate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
        deliveryDate.setCellValueFactory(new PropertyValueFactory<>("DeliveryDate"));
        shippingAddress.setCellValueFactory(new PropertyValueFactory<>("DeliveryAddress"));
        status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));

        invoiceTable.setItems(invoices);
    }

    @FXML
    public void delete() {

    }

    public static ObservableList<Invoice> addInvoices() {
        return invoices;
    }

    /**
     * Changes the image of the invoice displayed on the right with the one of the selected invoice.
     * @param mouseEvent
     * @since 4/23/2025
     * @author Nathaniel Rivera
     */
    public void selectedInvoice(MouseEvent mouseEvent) {

    }
}