package se.kth.iv1350.model;

/**
 * Represents an item in a sale including its description and quantity.
 */
public class Item {
    private ItemDescription description;
    private int quantity;

    /**
     * Creates a new instance of an item with a given description and quantity.
     * @param description The description of the item.
     * @param quantity The quantity of the item.
     */
    public Item(ItemDescription description, int quantity) {
        this.description = description;
        this.quantity = quantity;
    }

    /**
     * Increases the quantity of this item by a specified amount.
     * @param amount The amount to increase the quantity by.
     */
    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    /**
     * Calculates and returns the total price including VAT for this item.
     * @return The total price including VAT.
     */
    public double getTotalPriceIncVAT() {
        return quantity * description.getPrice();
    }
    /**
     * Returns the item ID.
     * @return id
     * 
     */
    public String getItemID() {
        return description.getItemID();
    }

    /**
     * Returns the item name.
     * @return name
     */
    public String getName() {
        return description.getName();
    }

    /**
     * Returns the item description.
     * @return desc
     */
    public String getDescriptionText() {
        return description.getDescription();
    }

    /**
     * Returns the item price (excluding VAT).
     * @return price
     */
    public double getPrice() {
        return description.getPrice();
    }

    /**
     * Returns the VAT percentage for the item.
     * @return Vat
     */
    public int getVAT() {
        return description.getVAT();
    }

    /**
     * Returns the current quantity of this item.
     * @return The quantity.
     */
    public int getQuantity() {
        return quantity;
    }
    
}
