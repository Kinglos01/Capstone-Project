package com.example.csc311_capstone_project;

import com.example.csc311_capstone_project.db.ConnDbOps;
import com.example.csc311_capstone_project.model.Invoice;
import com.example.csc311_capstone_project.model.Item;
import com.example.csc311_capstone_project.service.CurrentUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controls the item page of the application
 * @since 4/28/2025
 * @author Nathaniel Rivera, Jared Mitchell
 */
public class ItemController implements Initializable {

    @FXML
    protected TextField itemNameField;

    protected static boolean init = false;

    @FXML
    protected TextField itemPriceField;

    @FXML
    protected TableView<Item> itemTable;

    @FXML
    protected TableColumn<Item, String> itemID, itemName, itemPrice;

    @FXML
    protected MenuBar addItemFile;

    @FXML
    protected Label nameError, priceError, errorLabel;

    public static ObservableList<Item> items = FXCollections.observableArrayList(new ArrayList<>());

    static ConnDbOps db = new ConnDbOps();

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
        if(!init) {
            //db.connectToDatabase();
            db.setCurrentUser(CurrentUser.getCurrentUsername(), CurrentUser.getCurrentEmail());
            items = db.retrieveItems();
            init = true;
        }

        itemID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        itemName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        itemPrice.setCellValueFactory(new PropertyValueFactory<>("Ppi"));

        itemTable.setItems(items);

        Pattern itemNamePattern = Pattern.compile(".{2,250}");
        Pattern itemPricePattern = Pattern.compile("\\d+\\.\\d{2}");

        // Item Name Field, Error Messages
        itemNameField.textProperty().addListener((obs, oldText, newText) -> {
            boolean valid = itemNamePattern.matcher(newText).matches();
            itemNameField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
        });
        itemNameField.focusedProperty().addListener((obs, oldFocus, newFocus) -> {
            if (!newFocus) {
                boolean valid = itemNamePattern.matcher(itemNameField.getText()).matches();
                nameError.setText(valid ? "" : "Must be 2 to 250 characters");
                itemNameField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
            }
        });

        // Item Price Field, Error Messages
        itemPriceField.textProperty().addListener((obs, oldText, newText) -> {
            boolean valid = itemPricePattern.matcher(newText).matches();
            itemPriceField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
        });
        itemPriceField.focusedProperty().addListener((obs, oldFocus, newFocus) -> {
            if (!newFocus) {
                boolean valid = itemPricePattern.matcher(itemPriceField.getText()).matches();
                priceError.setText(valid ? "" : "Price must be a number \nwith 2 decimal places.");
                itemPriceField.setStyle(valid ? "-fx-border-color: Lime;" : "-fx-border-color: red;");
            }
        });
    }

    /**
     * Adds a new object to the TableView based on the currently inputted values
     * @since 4/28/2025
     * @author Nathaniel Rivera, Jared Mitchell
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

        for (Item value : items) {
            if (value.getName().equals(iName)) {
                canAdd = false;
                break;
            }
        }

        if( iName.isEmpty() || iPrice.isEmpty() ){
            System.out.println("Error: One or more fields are empty");
            errorLabel.setText("All fields must be filled."); // Print error to UI
            canAdd = false;

            // Highlight empty fields with a red border
            if (iName.isEmpty()) itemNameField.setStyle("-fx-border-color: red;");
            if (iPrice.isEmpty()) itemPriceField.setStyle("-fx-border-color: red;");
        }

        if(!itemNameMatcher.matches()){
            System.out.println("Error: The item name must be between 2 to 250 characters");
            nameError.setText("Must be 2 to 250 characters");
            canAdd = false;
        }

        if(!itemPriceMatcher.matches()){
            System.out.println("Error: The price must only be numbers an include exactly two decimals.");
            priceError.setText("Price must be a number \nwith 2 decimal places.");
            canAdd = false;
        }

        if(canAdd) {
            Item item = new Item(items.getLast().getId() + 1, itemNameField.getText(), Double.parseDouble(itemPriceField.getText()));
            items.add(item);
            db.insertItems(item.getId(), item.getName(), item.getPpi());
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
        db.removeItem(item.getId());
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
        Item i2 = new Item(index + 1, itemNameField.getText(), Double.parseDouble(itemPriceField.getText()));
        db.editItem(i.getId(), itemNameField.getText(), Double.parseDouble(itemPriceField.getText()));
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
        itemNameField.setStyle("");
        itemPriceField.setText("");
        itemPriceField.setStyle("");
        errorLabel.setText("");
        nameError.setText("");
        priceError.setText("");
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
        File file = (new FileChooser()).showOpenDialog(addItemFile.getScene().getWindow());

        if(file != null) {
            String fileString = file.toURI().toString().substring(5);
            System.out.println(fileString);
            BufferedReader reader;
            String line;
            try {
                reader = new BufferedReader(new FileReader(fileString));
                while((line = reader.readLine()) != null) {
                    String[] row = line.split(",");

                    if(row.length == 2) {
                        String name = row[0];
                        double price = Double.parseDouble(row[1]);

                        Item item = new Item(items.size() + 1, name, price);
                        items.add(item);
                        db.insertItems(item.getId(), item.getName(), item.getPpi());
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Closes the items page through the menu bar.
     * @since 5/2/2025
     * @author Nathaniel Rivera
     */
    @FXML
    protected void closeApp() {
        Stage stage = (Stage) itemTable.getScene().getWindow();
        stage.close();
    }

    /**
     * A static method which returns the items list so that the Invoice class can calculate the current price.
     * @return The list of items for the given User.
     * @since 4/14/2025
     */
    public static ObservableList<Item> getItemsList() {
        if(!init) {
            //db.connectToDatabase();
            db.setCurrentUser(CurrentUser.getCurrentUsername(), CurrentUser.getCurrentEmail());
            items = db.retrieveItems();
            init = true;
        }
        return items;
    }

    /**
     * Exports the items into a csv file that is downloaded into the users download folder.
     * @throws FileNotFoundException Throw FileNotFound if there is no file in the print writer.
     * @since 5/7/2025
     * @author Nathaniel Rivera
     */
    @FXML
    public void exportItems() throws FileNotFoundException {
        String home = System.getProperty("user.home");
        File csvFile = new File(home + "/Downloads/" + "ItemsList" + ".csv");

        PrintWriter out = new PrintWriter(csvFile);

        for(Item item: items) {
            out.println(item.getName() + "," + item.getPpi());
        }

        out.close();

        System.out.println("Item CSV Exported");
    }

}
