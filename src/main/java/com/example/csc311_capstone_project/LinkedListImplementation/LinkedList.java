package com.example.csc311_capstone_project.LinkedListImplementation;

import com.example.csc311_capstone_project.*;

/**
 * Basic LinkedList implementation. The LinkedList stores Nodes of Invoices. On user login, invoices
 * are drawn from the database and stored in a new LinkedList.
 * @since 3/21/2025
 * @author Aidan Rodriguez
 */
public class LinkedList {
    private Node head;
    private Node tail;
    private int counter;

    /**
     * Creates an empty LinkedList.
     */
    public LinkedList() {
        head = null;
        tail = null;
        counter = 0;
    }

    /**
     * Adds a new Invoice to the LinkedList.
     * @param invoice The Invoice to be added to the list.
     */
    public void add(Invoice invoice) {
        Node temp = new Node(invoice);
        if (counter == 0) {
            head = temp;
            tail = temp;
        }
        else if (counter == 1) {
            head.next = temp;
            tail = temp;
        }
        else {
            tail.next = temp;
            tail = temp;
        }
        counter++;
    }

    /**
     * Clears the LinkedList of all values.
     */
    public void clear() {
        head = null;
        tail = null;
        counter = 0;
    }

    /**
     * Returns an Array of all the currently stored Invoices, then clears the LinkedList.
     * @return An Array of Invoices.
     */
    public Invoice[] returnList() {
        Invoice[] invoices = new Invoice[counter];
        Node temp = head;
        while (temp.next != null) {
            invoices[counter] = temp.getInvoice();
            counter--;
        }
        this.clear();
        return invoices;
    }
}
