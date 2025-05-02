package com.example.csc311_capstone_project;

import com.example.csc311_capstone_project.db.ConnDbOps;
import com.example.csc311_capstone_project.model.Invoice;
import com.example.csc311_capstone_project.model.Status;
import com.example.csc311_capstone_project.service.CurrentUser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.time.Clock;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller for the Landing Page of the autoCommerce application.
 * Controls the initialization, invoice selection and deletion, and
 * menu items for the Landing Page. The Add Invoices button is handled
 * in Capstone Application instead of directly here.
 * @since 4/27/2025
 * @author Nathaniel Rivera
 */
public class LandingController implements Initializable{

    public static ObservableList<Invoice> invoices = FXCollections.observableArrayList(new ArrayList<>());

    @FXML
    protected Button removeButton;

    @FXML
    protected TableView<Invoice> invoiceTable;

    @FXML
    protected TableColumn<Invoice, String> invoiceName, invoiceNum, accountID, orderDate, deliveryDate, status, shippingAddress, price;

    @FXML
    protected ImageView invoiceDisplay;

    @FXML
    protected MenuItem closeApp;

    @FXML
    protected MenuItem logOut;

    ConnDbOps db = new ConnDbOps();

    /**
     * Initialization method which sets up the table columns and connects
     * to and obtains the list of invoices for the current User from the DB.
     * @param url URL
     * @param resourceBundle Resource Bundle
     * @since 4/10/2025
     * @author Nathaniel Rivera
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        db.connectToDatabase();
        db.setCurrentUser(CurrentUser.getCurrentUsername(), CurrentUser.getCurrentEmail());
        invoices = db.retrieveInvoices();

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
    protected void delete() {
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
    protected static ObservableList<Invoice> addInvoices() {
        return invoices;
    }

    /**
     * Changes the image of the invoice displayed on the right with the one of the selected invoice.
     * @param mouseEvent The event of clicking on a specific TableView value.
     * @since 4/23/2025
     * @author Nathaniel Rivera
     */
    @FXML
    protected void selectedInvoice(MouseEvent mouseEvent) {
        try {
            Invoice invoice = null;

            for (Invoice value : invoices) {
                if (Objects.equals(value.getInvoiceId(), invoiceTable.getSelectionModel().getSelectedItem().getInvoiceId())) {
                    invoice = value;
                }
            }


            invoiceDisplay.setImage(Objects.requireNonNull(invoice).getImage());
        } catch(NullPointerException _) { }
    }

    /**
     * Launches the item importer screen.
     * @throws IOException IOException.
     * @since 4/27/2025
     * @author Nathaniel Rivera
     */
    @FXML
    protected void openItemList() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LandingController.class.getResource("item-view.fxml"));
        Stage stage = new Stage();
        AnchorPane itemRoot = new AnchorPane();
        itemRoot.getChildren().add(fxmlLoader.load());;

        Scene scene = new Scene(itemRoot, 655, 800);

        stage.setScene(scene);
        scene.getStylesheets().add(LandingController.class.getResource("/com/example/csc311_capstone_project/item.css").toExternalForm());
        stage.setResizable(false);

        stage.show();
    }

    /**
     * Toggles the current status of the Invoice that
     * is selected. If the status is toggled to 'Delivered'
     * the Invoice will have its delivery date set to the
     * current time.
     * @since 4/26/2025
     * @author Nathaniel Rivera
     */
    @FXML
    protected void toggleStatus() {
        StringBuilder sb = new StringBuilder();

        Clock zoneClock = Clock.system(ZoneId.of("-05:00"));
        sb.append(zoneClock.instant().toString(), 5, 7).append("-");
        sb.append(zoneClock.instant().toString(), 8, 10).append("-");
        sb.append(zoneClock.instant().toString(), 0, 4);
        String time  = sb.toString();
        System.out.println(time);

        Invoice invoice = invoiceTable.getSelectionModel().getSelectedItem();
        switch(invoice.getStatus()) {
            case Status.delivered -> {}
            case Status.en_route -> {
                int index = invoices.indexOf(invoice);
                Invoice invoice2 = new Invoice(invoice.getInvoiceId(), invoice.getAccountId(), invoice.getOrderDate(), time, invoice.getDeliveryAddress(), Status.delivered, invoice.getInvoiceName(), invoice.getPrice(), invoice.getImage());
                invoices.remove(invoice);
                invoices.add(index, invoice2);
                db.editInvoice(invoice.getInvoiceId(), "Delivered", time);
                invoiceTable.getSelectionModel().select(index);}
            case Status.not_delivered -> {
                int index = invoices.indexOf(invoice);
                Invoice invoice2 = new Invoice(invoice.getInvoiceId(), invoice.getAccountId(), invoice.getOrderDate(), invoice.getDeliveryDate(), invoice.getDeliveryAddress(), Status.en_route, invoice.getInvoiceName(), invoice.getPrice(), invoice.getImage());
                invoices.remove(invoice);
                invoices.add(index, invoice2);
                db.editInvoice(invoice.getInvoiceId(), "En-Route", invoice.getDeliveryDate());
                invoiceTable.getSelectionModel().select(index);}
            case Status.unknown -> {invoice.setStatus(Status.not_delivered);
                int index = invoices.indexOf(invoice);
                Invoice invoice2 = new Invoice(invoice.getInvoiceId(), invoice.getAccountId(), invoice.getOrderDate(), invoice.getDeliveryDate(), invoice.getDeliveryAddress(), Status.not_delivered, invoice.getInvoiceName(), invoice.getPrice(), invoice.getImage());
                invoices.remove(invoice);
                invoices.add(index, invoice2);
                db.editInvoice(invoice.getInvoiceId(), "Not Delivered", invoice.getDeliveryDate());
                invoiceTable.getSelectionModel().select(index);}
        }
    }

    /***
     * Closes application through the menu bar
     * @since 4/30/2025
     * @author Carlos Berio
     */
    @FXML
    protected void closeApplication() {
        System.exit(0);
    }

    /**
     * Logs out of the application returning the user to the home screen
     * @since 5/2/2025
     * @author Nathaniel Rivera
     */
    @FXML
    protected void logOutClicked(){

        Stage landingStage = (Stage) invoiceTable.getScene().getWindow();
        landingStage.close();
        CurrentUser.logOut();

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(CapstoneApplication.class.getResource("splash-view.fxml"));
            AnchorPane root = new AnchorPane();
            root.getChildren().add(fxmlLoader.load());
            CapstoneApplication.splashSetup(root, stage);

            Scene scene = new Scene(root, 1200, 700);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("splashscreen.css")).toExternalForm());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.getIcons().add(new Image(Objects.requireNonNull(CapstoneApplication.class.getResourceAsStream("/com/example/csc311_capstone_project/images/colored_icon.png"))));
            stage.show();
        } catch(IOException _) {

        }
    }

}

