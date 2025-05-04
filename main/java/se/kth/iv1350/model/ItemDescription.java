package se.kth.iv1350.model;

/**
 * Represents descriptive information about an item,
 * including its ID, name, text description, price, and VAT rate.
 */
public class ItemDescription {
    private String itemID;
    private String name;
    private String description;
    private double price;
    private int vat;

    /**
     * Creates a new instance of an item description.
     * @param itemID The unique identifier of the item.
     * @param name The name of the item.
     * @param description The textual description of the item.
     * @param price The price of the item (excluding VAT).
     * @param vat The VAT percentage for the item.
     */
    public ItemDescription(String itemID, String name, String description, double price, int vat) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.vat = vat;
    }

    /**
     * Returns the item ID.
     * @return The unique identifier of the item.
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * Returns the name of the item.
     * @return The item name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the item.
     * @return The item description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the price of the item (excluding VAT).
     * @return The price of the item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the VAT percentage for the item.
     * @return The VAT rate.
     */
    public int getVAT() {
        return vat;
    }

}
