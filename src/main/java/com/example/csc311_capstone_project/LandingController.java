package com.example.csc311_capstone_project;

import com.example.csc311_capstone_project.db.ConnDbOps;
import com.example.csc311_capstone_project.model.Invoice;
import com.example.csc311_capstone_project.model.Status;
import com.example.csc311_capstone_project.model.User;
import com.example.csc311_capstone_project.service.CurrentUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LandingController implements Initializable{

    private static ObservableList<Invoice> invoices = FXCollections.observableArrayList(new Invoice("I123", "C123", "01-01-1900", "01-01-1900", "Place", Status.unknown, "Test Invoice", "$ 0.0", ""));
    protected Button removeButton;

    @FXML
    private TableView<Invoice> invoiceTable;

    @FXML
    private TableColumn<Invoice, String> invoiceName, invoiceNum, accountID, orderDate, deliveryDate, status, shippingAddress, price;

    @FXML
    protected ImageView invoiceDisplay;

    ConnDbOps db = new ConnDbOps();

    /**
     * Initialization method which sets up the table columns and connects
     * to and obtains the list of invoices for the current User from the DB.
     * @param url
     * @param resourceBundle
     * @since 4/10/2025
     * @author Nathaniel Rivera
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        db.connectToDatabase();
        db.setCurrentUser(CurrentUser.getCurrentUsername(), CurrentUser.getCurrentEmail());

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

    /**
     * Deletes the currently selected invoice in the table.
     * @since 4/26/2025
     * @author Nathaniel Rivera
     */
    @FXML
    public void delete() {
        Invoice invoice = invoiceTable.getSelectionModel().getSelectedItem();
        int index = invoices.indexOf(invoice);
        db.removeInvoice(invoice.getInvoiceId());
        invoices.remove(index);

        invoiceTable.getSelectionModel().select(index);
    }

    /**
     * A static method which returns the invoice list so that the Scanner Controller can add the invoice.
     * @return The list of invoices for the given User.
     * @since 4/14/2025
     */
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
        Invoice invoice = invoiceTable.getSelectionModel().getSelectedItem();
        invoiceDisplay.setImage(new Image(invoice.getImage()));
    }
}