package se.kth.iv1350.model;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Represents a sale, containing items and metadata such as the time of sale.
 */
public class Sale {
    private Map<String, Item> items = new HashMap<>();
    private LocalDateTime timeOfSale;

    /**
     * Sets the timestamp for when the sale started.
     */
    public void setTimeOfSale() {
        timeOfSale = LocalDateTime.now();
    }

    /**
     * Adds a new item to the sale.
     * @param desc The description of the item to add.
     * @param quantity The quantity of the item to add.
     */
    public void addItem(ItemDescription desc, int quantity) {
        items.put(desc.getItemID(), new Item(desc, quantity));
    }

    /**
     * Increases the quantity of an existing item in the sale.
     * @param itemId The ID of the item whose quantity is to be increased.
     * @param quantity The amount to increase.
     */
    public void increaseQuantity(String itemId, int quantity) {
        Item item = items.get(itemId);
        if (item != null) {
            item.increaseQuantity(quantity);
        }
    }

    /**
     * Checks whether the sale already contains an item with the given ID.
     * @param itemId The ID of the item to check.
     * @return {@code true} if the item exists in the sale; otherwise, {@code false}.
     */
    public boolean hasItem(String itemId) {
        return items.containsKey(itemId);
    }

    /**
     * Calculates the total price for all items, including VAT.
     * @return The total price including VAT.
     */
    public double getTotalPriceIncVAT() {
        double total = 0;
        for (Item item : items.values()) {
            total += item.getTotalPriceIncVAT();
        }
        return total;
    }

    /**
     * Returns the timestamp for when the sale was started.
     * @return The time of sale.
     */
    public LocalDateTime getTimeOfSale() {
        return timeOfSale;
    }

    /**
     * Returns a read-only view of the items in this sale.
     * @return A collection of all items in the sale.
     */
    public Collection<Item> getItems() {
        return Collections.unmodifiableCollection(items.values());
    }


    /**
     * Calculates the total VAT for all items in the sale.
     * @return The total VAT, rounded to two decimal places.
     */
    public double getTotalVAT() {
        double totalVAT = 0;
        for (Item item : items.values()) {
            double priceInclVAT = item.getPrice();
            double priceExclVAT = priceInclVAT / (1 + item.getVAT() / 100.0);

            double vatPerItem = priceInclVAT - priceExclVAT;
            totalVAT += vatPerItem * item.getQuantity();
        }
        return Math.round(totalVAT * 100.0) / 100.0;
    }
    /**
     * Summary information.
     * @return summary of totals and vat
     */
    public SaleSummary getSummary() {
        return new SaleSummary(getTotalPriceIncVAT(), getTotalVAT());
    }
    
}
