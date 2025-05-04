package se.kth.iv1350.integration;

import se.kth.iv1350.model.Receipt;
import se.kth.iv1350.model.Sale;

/**
 * Handles interaction with external systems such as the inventory system,
 * accounting system, and printer. here we groups these responsibilities into a single
 * cohesive unit to reduce coupling in the controller.
 */
public class ExternalSystemHandler {
    private final ExternalAccounting accounting;
    private final InventorySystem inventory;
    private final Printer printer;

    /**
     * Creates a new handler for external systems.
     * @param accounting The external accounting system.
     * @param inventory The external inventory system.
     * @param printer The printer used to print receipts.
     */
    public ExternalSystemHandler(ExternalAccounting accounting, InventorySystem inventory, Printer printer) {
        this.accounting = accounting;
        this.inventory = inventory;
        this.printer = printer;
    }

    /**
     * Prints the given receipt using the configured printer.
     * @param receipt The receipt to print.
     */
    public void print(Receipt receipt) {
        printer.print(receipt);
    }

    /**
     * Registers the sale in the external accounting system.
     * @param sale The completed sale to register.
     */
    public void registerSale(Sale sale) {
        accounting.registerSale(sale);
    }

    /**
     * Updates the external inventory system based on the sold items.
     * @param sale The completed sale to use for inventory updates.
     */
    public void updateInventory(Sale sale) {
        inventory.updateInventory(sale);
    }
}
