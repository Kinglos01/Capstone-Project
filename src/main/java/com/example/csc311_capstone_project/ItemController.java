package com.example.csc311_capstone_project;

import com.example.csc311_capstone_project.db.ConnDbOps;
import com.example.csc311_capstone_project.model.Item;
import com.example.csc311_capstone_project.service.CurrentUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemController implements Initializable {

    @FXML
    protected TextField itemNameField;

    @FXML
    protected TextField itemPriceField;

    @FXML
    protected TableView<Item> itemTable;

    @FXML
    protected TableColumn<Item, String> itemID, itemName, itemPrice;

    protected ObservableList<Item> items = FXCollections.observableArrayList(new ArrayList<>());

    ConnDbOps db = new ConnDbOps();

    /**
     * Initialization method which sets up the table columns and connects
     * to and obtains the list of items for the current User from the DB.
     * @param url URL
     * @param resourceBundle Resource Bundle
     * @since 4/28/2025
     * @author Nathaniel Rivera
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        db.connectToDatabase();
        db.setCurrentUser(CurrentUser.getCurrentUsername(), CurrentUser.getCurrentEmail());

        itemID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        itemName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        itemPrice.setCellValueFactory(new PropertyValueFactory<>("Ppi"));

        itemTable.setItems(items);
    }

    /**
     * Adds a new object to the TableView based on the currently inputted values
     * @since 4/28/2025
     * @author Nathaniel Rivera
     */
    @FXML
    protected void addItem() {
        boolean canAdd = true;
        String iName = itemNameField.getText();
        String iPrice = itemPriceField.getText();

        Pattern itemNamePattern = Pattern.compile(".{2,250}");
        Matcher itemNameMatcher = itemNamePattern.matcher(iName);
        Pattern itemPricePattern = Pattern.compile("\\d+\\.\\d{2}");
        Matcher itemPriceMatcher = itemPricePattern.matcher(iPrice);

        if( iName.isEmpty() || iPrice.isEmpty() ){
            System.out.println("Error: One or more fields are empty");
            canAdd = false;
        }

        if(!itemNameMatcher.matches()){
            System.out.println("Error: The item name must be between 2 to 250 characters");
            canAdd = false;
        }

        if(!itemPriceMatcher.matches()){
            System.out.println("Error: The price must only be numbers an include exactly two decimals.");
            canAdd = false;
        }

        if(canAdd) {
            items.add(new Item(items.size() + 1, itemNameField.getText(), Double.parseDouble(itemPriceField.getText())));
            clearForm();
        }
    }

    /**
     * Deletes the currently selected item in the table.
     * @since 4/28/2025
     * @author Nathaniel Rivera
     */
    @FXML
    protected void delete() {
        Item item = itemTable.getSelectionModel().getSelectedItem();
        int index = items.indexOf(item);
        //db.removeInvoice(item.getInvoiceId());
        items.remove(index);

        itemTable.getSelectionModel().select(index);
    }


    /**
     * Changes the text fields based on the item selected on the TableView.
     * @param mouseEvent The event of clicking on a specific TableView value.
     * @since 4/28/2025
     * @author Nathaniel Rivera
     */
    @FXML
    protected void selectedItem(MouseEvent mouseEvent) {
        Item i = itemTable.getSelectionModel().getSelectedItem();
        itemNameField.setText(i.getName());
        itemPriceField.setText(String.valueOf(i.getPpi()));
    }

    /**
     * Edits the currently selected Item in the TableView.
     * @since 4/28/2025
     * @author Nathaniel Rivera
     */
    @FXML
    protected void edit() {
        Item i = itemTable.getSelectionModel().getSelectedItem();
        int index = items.indexOf(i);
        Item i2 = new Item(index + 1, i.getName(), i.getPpi());
        items.remove(i);
        items.add(index, i2);
        itemTable.getSelectionModel().select(index);

    }

    /**
     * Clears the current form.
     * @since 4/28/2025
     * @author Nathaniel Rivera
     */
    @FXML
    protected void clearForm() {
        itemNameField.setText("");
        itemPriceField.setText("");
    }

    /**
     * Allows the user to import a CSV file. After importing
     * this CSV file the data will be automatically inputted
     * into a table.
     * @since 4/28/2025
     * @author Nathaniel Rivera
     */
    @FXML
    protected void importForms() {

    }

}
