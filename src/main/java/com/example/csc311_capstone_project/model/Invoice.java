package com.example.csc311_capstone_project.model;

import com.example.csc311_capstone_project.ItemController;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * This class is created to store the Invoices. On program start, Invoices that are already
 * stored in the database are imported. New invoices created by the user are stored internally,
 * as well as uploaded to the database for future use.
 * @since 3/21/2025
 * @author Nathaniel Rivera, Aidan Rodriguez
 */
public class Invoice {

    private String invoice_id;
    private String account_id;
    private String order_date;
    private String delivery_date;
    private String delivery_address;
    private Status status;
    private String name;
    private String price;
    private Item[] items;
    private Image image;

    /*
    /**
     * Creates an invoice with placeholder values.
     * @since 3/21/2025
     * @author Aidan Rodriguez
     *
    public Invoice() {
        invoice_id = "1234";
        account_id = "123";
        order_date = "01-01-1900";
        delivery_date = "01-01-1900";
        delivery_address = "21 Jump Street";
        status = Status.unknown;
        items = new Item[999];
    }
    */

    /**
     * Creates an Invoice populated with parameter values. Used for invoices with predefined price values.
     * @param invoice_id Assigns a String to item_id.
     * @param account_id Assigns a String to account_id.
     * @param order_date Assigns a String to the order_date.
     * @param delivery_date Assigns a String to the delivery_date
     * @param delivery_address Assigns a String to delivery_address.
     * @param status Assigns a Status to status.
     * @param name Assigns a String to name.
     * @param price Assigns a String to price.
     * @param image Assigns a Image to image.
     * @since 3/21/2025
     * @author Aidan Rodriguez, Nathaniel Rivera
     */
    public Invoice(String invoice_id, String account_id, String order_date, String delivery_date, String delivery_address, Status status, String name, String price, Image image) {
        this.invoice_id = invoice_id;
        this.account_id = account_id;
        this.order_date = order_date;
        this.delivery_date = delivery_date;
        this.delivery_address = delivery_address;
        this.status = status;
        this.items = new Item[999];
        this.name = name;
        this.price = price;
        this.image = image;
    }


    /**
     * Creates an Invoice populated with parameter values plus it converts the list of items with their quantities into the price value.
     * @param invoice_id Assigns a String to item_id.
     * @param account_id Assigns a String to account_id.
     * @param order_date Assigns a String to the order_date.
     * @param delivery_date Assigns a String to the delivery_date
     * @param delivery_address Assigns a String to delivery_address.
     * @param status Assigns a Status to status.
     * @param name Assigns a String to name.
     * @param item String of items and their quantities which will be converted into the price value.
     * @param image Image of the invoice.
     * @author Nathaniel Rivera
     * @since 4/1/2025
     */
    public Invoice(String invoice_id, String account_id, String order_date, String delivery_date, String delivery_address, String name, String item, Image image, Status status) {
        this.invoice_id = invoice_id;
        this.account_id = account_id;
        this.order_date = order_date;
        this.delivery_date = delivery_date;
        this.delivery_address = delivery_address;
        this.status = status;
        this.image = image;
        this.name = name;
        this.price = "$" + findPrice(item);
    }

    /**
     * Gets the invoice_Id of the specified Invoice.
     * @return The invoice_Id of the invoice instance as an String.
     */
    public String getInvoiceId() {
        return this.invoice_id;
    }

    /**
     * Sets the invoice_Id of the specified Invoice.
     * @param invoice_id The new invoice_Id of the invoice, specified as a String.
     */
    public void setInvoiceId(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    /**
     * Gets the account_Id of the specified Invoice.
     * @return The account_Id of the specified Invoice
     */
    public String getAccountId() {
        return this.account_id;
    }

    /**
     * Sets the account_Id of the specified invoice.
     * @param account_id The new account_Id of the specified invoice, as a String.
     */
    public void setAccountId(String account_id) {
        this.account_id = account_id;
    }

    /**
     * Getter method for order_date.
     * @return The order_date of the invoice.
     * @since 3/30/2025
     * @author Nathaniel Rivera
     */
    public String getOrderDate() {
        return this.order_date;
    }

    /**
     * Setter method for the order_date.
     * @param order_date The new order_date of the Invoice.
     * @since 3/30/2025
     * @author Nathaniel Rivera
     */
    public void setOrderDate(String order_date) {
        this.order_date = order_date;
    }

    /**
     * Getter method for delivery_date.
     * @return The delivery_date of the invoice.
     * @since 3/30/2025
     * @author Nathaniel Rivera
     */
    public String getDeliveryDate() {
        return this.delivery_date;
    }

    /**
     * Setter method for the delivery_date.
     * @param delivery_date The new delivery_date of the Invoice.
     * @since 3/30/2025
     * @author Nathaniel Rivera
     */
    public void setDeliveryDate(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    /**
     * Returns the delivery address of the Invoice.
     * @return The delivery addres of the Invoice, as a String.
     */
    public String getDeliveryAddress() {
        return this.delivery_address;
    }

    /**
     * Sets the delivery address of the Invoice.
     * @param delivery_address The new delivery address of the Invoice, as a String.
     */
    public void setDeliveryAddress(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    /**
     * Gets the Status of the Invoice object.
     * @return The Status of the Invoice object, returned as a Status enum.
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Sets the Status of the Invoice.
     * @param status A Status enum to be changed in the Invoice object.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Adds an Item to the list of Items contained within the Invoice object.
     * @param item The Item object to be added.
     */
    public void addItem(Item item) {
        int counter = 0;
        while (items[counter] != null) {
            counter++;
        }
        items[counter] = item;
    }

    /**
     * A getter method for the price of the invoice
     * @return A String of the price
     * @since 4/23/2025
     * @author Nathaniel Rivera
     */
    public String getPrice() {
        return price;
    }

    /**
     * A getter method for the price of the invoice
     * @return A String of the price
     * @since 4/23/2025
     * @author Nathaniel Rivera
     */
    public String getInvoiceName() {
        return name;
    }

    /**
     * A getter method for the items in the invoice.
     * Necessary for the PropertyValueFactory to get the information
     * @return A String of all items in the invoice
     * @since 4/14/25
     * @author Jared Mitchell
     */
    public String getItems() {
        String itemString = "";
        for (Item i : items){
            itemString += i.toString() + ", ";
        }
        itemString = itemString.substring(0, itemString.length()-1);
        return itemString;
    }

    /**
     * Returns the image of the given invoice
     * @return A String with the image of the invoice.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Takes in a string of Items:Quantity and outputs the price
     * of all the items in the String
     * @param items The String of items with their quantity must be in Item:Quantity order.
     * @return The total price of the invoice
     * @since 4/29/2025
     * @author Nathaniel Rivera
     */
    public double findPrice(String items) {

        String word = null;
        String quantity = null;

        int prev = 0;
        int mid;
        int end;

        double price = 0;

        ObservableList<Item> itemList = ItemController.getItemsList();

        for(int i = 0; i < items.length(); i++) {

            if(items.charAt(i) == ':') {

                    mid = i;

                    for(int j = i; j < items.length(); j++) {

                        if(items.charAt(j) == ',') {
                            end = j;

                            word = items.substring(prev, mid);
                            quantity = items.substring(mid + 1, end);
                            prev = end + 2;
                            break;
                        } else if(j == items.length() - 1) {
                            end = j + 1;

                            word = items.substring(prev, mid);
                            quantity = items.substring(mid + 1, end);
                            prev = end;
                            break;
                        }
                    }

                    for (Item item : itemList) {
                        if (item.getName().equals(word)) {
                            price = price + (item.getPpi() * Double.parseDouble(quantity));
                        }

                    }

            }
        }
        price = price * 1.08875;
        int rounder = (int) (price * 100);
        price = (double) rounder / 100;
        return price;
    }
}
