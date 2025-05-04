package se.kth.iv1350.integration;

import se.kth.iv1350.model.ItemDescription;

/**
 * Represents a registry of items. In a real application this would interact with
 * a db right now it uses hardcoded item data.
 */
public class ItemRegistry {
    /**
     * Searches for an item by its item ID.
     * @param itemId The ID of the item to search for.
     * @return The itemDescription if the item is found otherwise null.
     */
    public ItemDescription findItemById(String itemId) {
        if (itemId.equals("abc123")) {
            return new ItemDescription(
                "abc123",
                "BigWheel Oatmeal",
                "BigWheel Oatmeal 500g, whole grain oats, high fiber, gluten free",
                29.90,
                6
            );
        } else if (itemId.equals("def456")) {
            return new ItemDescription(
                "def456",
                "YouGoGo Blueberry",
                "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour",
                14.90,
                6
            );
        } else {
            return null;
        }
    }
}
