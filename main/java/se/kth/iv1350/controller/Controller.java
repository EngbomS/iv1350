package se.kth.iv1350.controller;

import se.kth.iv1350.model.*;
import se.kth.iv1350.integration.*;
import se.kth.iv1350.dto.ReceiptDTO;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.util.RevenueObserver;


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
    private final List<RevenueObserver> revenueObservers = new ArrayList<>();


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
        for (RevenueObserver observer : revenueObservers) {
        currentSale.addRevenueObserver(observer);
    }
        currentSale.setTimeOfSale();
    }

    /**
     * Registers an item with a specified quantity in the current sale.
     * If the item is already in the sale, increases the quantity.
     * @param itemId The ID of the item to register.
     * @param quantity The number of items.
     * @return The item description if found, otherwise null.
     * 
     * @throws DatabaseFailureException if a database error occurs while searching for the item.
     */
   public ItemDescription registerItem(String itemId, int quantity) throws DatabaseFailureException, ItemNotFoundException {
    ItemDescription desc = itemRegistry.findItemById(itemId);

    if (currentSale.hasItem(itemId)) {
        currentSale.increaseQuantity(itemId, quantity);
    } else {
        currentSale.addItem(desc, quantity);
    }
    return desc;
}

    /**
    * Adds a revenue observer that will be notified after each payment.
    * @param observer The observer to register.
    */
    public void addRevenueObserver(RevenueObserver observer) {
        revenueObservers.add(observer);
    }
    



    /**
     * Ends the sale and returns the total price including VAT.
     * @return The total price including VAT.
     */
    public SaleSummary endSale() {
        return currentSale.getSummary();
    }
    

    /**
     * Processes the payment, prints the receipt using ReceiptDTO, and updates external systems.
     * @param amountPaid The amount paid by the customer.
     */
    public void makePayment(double amountPaid) {
        Payment payment = new Payment(amountPaid);
        ReceiptDTO receiptDTO = currentSale.createReceiptDTO(payment);
        systemHandler.print(receiptDTO);
        systemHandler.registerSale(currentSale);
        systemHandler.updateInventory(currentSale);
        
    }

    /**
     * Generates and prints the receipt. (Alternative interface to print directly.)
     * @param payment The payment made.
     * @param printer The printer used to print the receipt.
     */
    public void printReceipt(Payment payment, Printer printer) {
        ReceiptDTO receiptDTO = currentSale.createReceiptDTO(payment);
        printer.printReceipt(receiptDTO);
    }
}
