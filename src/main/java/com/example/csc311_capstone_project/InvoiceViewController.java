package com.example.csc311_capstone_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InvoiceViewController {
    @FXML
    private Label account_id;

    @FXML
    private Label invoice_id;

    @FXML
    private Label delivery_address;

    @FXML
    private Label status;

    @FXML
    private VBox items_view;
}
