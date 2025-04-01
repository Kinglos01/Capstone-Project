package com.example.csc311_capstone_project;

import javafx.scene.image.Image;

public class Invoice {

    private String invoice_id;
    private String account_id;
    private String order_date;
    private String delivery_date;
    private String delivery_address;
    private Status status;
    private Item[] items;
    /**
     * Creates an invoice with placeholder values.
     */
    public Invoice() {
        invoice_id = "1234";
        account_id = "123";
        order_date = "01-01-1900";
        delivery_date = "01-01-1900";
        delivery_address = "21 Jump Street";
        status = Status.not_delivered;
        items = new Item[999];
    }

    /**
     * Creates an Invoice populated with parameter values.
     * @param invoice_id Assigns a String to item_id.
     * @param account_id Assigns a String to account_id.
     * @param order_date Assigns a String to the order_date.
     * @param delivery_date Assigns a String to the delivery_date
     * @param delivery_address Assigns a String to delivery_address.
     * @param status Assigns a Status to status.
     */
    public Invoice(String invoice_id, String account_id, String order_date, String delivery_date, String delivery_address, Status status) {
        this.invoice_id = invoice_id;
        this.account_id = account_id;
        this.order_date = order_date;
        this.delivery_date = delivery_date;
        this.delivery_address = delivery_address;
        this.status = status;
        this.items = new Item[999];
    }

    /**
     * Creates an Invoice populated with parameter values.
     * @param invoice_id Assigns a String to item_id.
     * @param account_id Assigns a String to account_id.
     * @param order_date Assigns a String to the order_date.
     * @param delivery_date Assigns a String to the delivery_date
     * @param delivery_address Assigns a String to delivery_address.
     * @param status Assigns a Status to status.
     * @param item String of items that will be added to the array
     */
    public Invoice(String invoice_id, String account_id, String order_date, String delivery_date, String delivery_address, Status status, String item) {
        this.invoice_id = invoice_id;
        this.account_id = account_id;
        this.order_date = order_date;
        this.delivery_date = delivery_date;
        this.delivery_address = delivery_address;
        this.status = status;
        this.items = new Item[999];
    }

    public String getInvoiceId() {
        return this.invoice_id;
    }

    public void setInvoiceId(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getAccountId() {
        return this.account_id;
    }

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

    public String getDeliveryAddress() {
        return this.delivery_address;
    }

    public void setDeliveryAddress(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void addItem(Item item) {
        int counter = 0;
        while (items[counter] != null) {
            counter++;
        }
        items[counter] = item;
    }
}
