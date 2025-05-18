package se.kth.iv1350.integration;

/**
 * Thrown when an item identifier is not found in the item registry.
 */
public class ItemNotFoundException extends Exception {
    private final String itemID;

    public ItemNotFoundException(String itemID) {
        super("No item with ID: " + itemID);
        this.itemID = itemID;
    }

    public String getItemID() {
        return itemID;
    }
}
