package com.example.csc311_capstone_project.LinkedListImplementation;

import com.example.csc311_capstone_project.Invoice;

/**
 * The Node class is for a basic LinkedList implementation. Each Node stores an invoice to be used by
 * the program.
 * @since 3/21/2025
 * @author Aidan Rodriguez
 */
public class Node {
    public Node next;
    public Invoice invoice;

    /**
     * Creates an empty node, not associated with any Invoice.
     */
    public Node() {
        next = null;
        invoice = null;
    }

    /**
     * Creates a node associated with an Invoice.
     * @param invoice The Invoice to be stored within the Node.
     */
    public Node(Invoice invoice) {
        next = null;
        this.invoice = invoice;
    }

    /**
     * Returns the invoice stored in the Node.
     * @return The Invoice stored in the Node.
     */
    public Invoice getInvoice() {
        return this.invoice;
    }
}
