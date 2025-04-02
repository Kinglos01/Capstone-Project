package com.example.csc311_capstone_project;

/**
 * This class is created to populate the Invoice class. On program start, Items that are already
 * stored in the database are imported to allow for the invoices to populate. New items created
 * by the user are stored internally, as well as uploaded to the database for future use.
 * @since 3/21/2025
 * @author Aidan Rodriguez
 */
public class Item {
    private int item_id;
    private String item_name;
    private double ppi;

    /**
     * Creates an empty item with placeholder values.
     * @since 3/21/2025
     * @author Aidan Rodriguez
     */
    public Item() {
        item_id = -1;
        item_name = null;
        ppi = 0.00;
    }

    /**
     * Creates an item with the specified parameters.
     * @param item_id Assigns an integer value to item_id.
     * @param item_name Assigns a String value to item_name.
     * @param ppi Assigns a double value to ppi.
     * @since 3/21/2025
     * @author Aidan Rodriguez
     */
    public Item(int item_id, String item_name, double ppi) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.ppi = ppi;
    }

    /**
     * Returns the specified item's item_id.
     * @return Return Item's item_id as an integer.
     * @since 3/21/2025
     * @author Aidan Rodriguez
     */
    public int getId() {
        return this.item_id;
    }

    /**
     * Sets the item_id of the specified Item.
     * @param item_id Assigns an integer value to the specified Item's item_id.
     * @since 3/21/2025
     * @author Aidan Rodriguez
     */
    public void setId(int item_id) {
        this.item_id = item_id;
    }

    /**
     * Returns the specified Item's item_name.
     * @return Returns Item's item_name as a String.
     * @since 3/21/2025
     * @author Aidan Rodriguez
     */
    public String getName() {
        return this.item_name;
    }

    /**
     * Sets the item_name of the specified Item.
     * @param item_name Assigns a String value to the specified Item's item_name.
     * @since 3/21/2025
     * @author Aidan Rodriguez
     */
    public void setName(String item_name) {
        this.item_name = item_name;
    }

    /**
     * Return the specified Item's ppi.
     * @return Returns Item's ppi as a double.
     * @since 3/21/2025
     * @author Aidan Rodriguez
     */
    public double getPpi() {
        return this.ppi;
    }

    /**
     * Sets the ppi of the specified Item.
     * @param ppi Assigns a double value to the specified Item's ppi.
     * @since 3/21/2025
     * @author Aidan Rodriguez
     */
    public void setPpi(double ppi) {
        this.ppi = ppi;
    }
}
