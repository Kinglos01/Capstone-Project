package com.example.csc311_capstone_project;

public class Invoice {
    public int invoice_id;
    public int account_id;
    public String delivery_address;
    public Status status;
    public Item[] items;
    /**
     * Creates an invoice with placeholder values.
     */
    public Invoice() {
        invoice_id = -1;
        account_id = -1;
        delivery_address = "21 Jump Street";
        status = Status.not_delivered;
        items = new Item[999];
    }

    /**
     * Creates an Invoice populated with parameter values.
     * @param invoice_id Assigns an integer to item_id.
     * @param account_id Assigns an integer to account_id
     * @param delivery_address Assigns a String to delivery_address.
     * @param status Assigns a Status to status.
     */
    public Invoice(int invoice_id, int account_id, String delivery_address, Status status) {
        this.invoice_id = invoice_id;
        this.account_id = account_id;
        this.delivery_address = delivery_address;
        this.status = status;
        this.items = new Item[999];
    }

    public int getInvoiceId() {
        return this.invoice_id;
    }

    public void setInvoiceId(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getAccountId() {
        return this.account_id;
    }

    public void setAccountId(int account_id) {
        this.account_id = account_id;
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
