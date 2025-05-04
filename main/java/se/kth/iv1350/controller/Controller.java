package se.kth.iv1350.controller;

import se.kth.iv1350.model.*;
import se.kth.iv1350.integration.*;
/**
 * This class is responsible for handling the application's operations.
 * It communicates with the model and integration and performs actions
 * required during a sale scenario.
 */

public class Controller {
    private ItemRegistry itemRegistry;
    private DiscountDatabase discountDB;
    private ExternalSystemHandler systemHandler;
    private Sale currentSale;

    /**
     * Creates a new instance of Controller.
     *
     * @param itemRegistry The item registry to search for items.
     * @param discountDB The discount database.
     * @param systemHandler A handler for all external system interactions.
     */
    public Controller(ItemRegistry itemRegistry, DiscountDatabase discountDB, ExternalSystemHandler systemHandler) {
        this.itemRegistry = itemRegistry;
        this.discountDB = discountDB;
        this.systemHandler = systemHandler;
    }


     /**
     * Starts a new sale.
     */
    public void startSale() {
        currentSale = new Sale();
        currentSale.setTimeOfSale();
    }

    /**
     * Registers an item with a specified quantity in the current sale.
     * If the item is already in the sale, increases the quantity.
     * @param itemId The ID of the item to register.
     * @param quantity The number of items.
     * @return The item description if found, otherwise null.
     */
    public ItemDescription registerItem(String itemId, int quantity) {
        ItemDescription desc = itemRegistry.findItemById(itemId);
        if (desc == null) {
            return null;
        }

        if (currentSale.hasItem(itemId)) {
            currentSale.increaseQuantity(itemId, quantity);
        } else {
            currentSale.addItem(desc, quantity);
        }
        return desc;
    }

    /**
     * Ends the sale and returns the total price including VAT.
     * @return The total price including VAT.
     */
    public SaleSummary endSale() {
        return currentSale.getSummary();
    }
    
    /**
     * Processes the payment, prints the receipt, and returns the receipt text.
     * @param amountPaid The amount paid by the customer.
     * @return The text of the printed receipt.
     */
    public String makePayment(double amountPaid) {
        Payment payment = new Payment(amountPaid);
        Receipt receipt = new Receipt(currentSale, payment);
        systemHandler.print(receipt);
        systemHandler.registerSale(currentSale);
        systemHandler.updateInventory(currentSale);
        return receipt.getReceiptText();
    }
    
   
}
