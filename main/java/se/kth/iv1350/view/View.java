package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.ItemDescription;
import se.kth.iv1350.model.SaleSummary;

/**
 * Simulates a user interface for testing and demonstrating the sale process.
 * It performs hardcoded calls to the controller and prints outputs to System.out.
 */
public class View {
    private Controller controller;

    /**
     * Creates a new instance of the view and starts the simulated sale process.
     * @param controller The controller that manages the application logic.
     */
    public View(Controller controller) {
        this.controller = controller;
        simulateSale();
    }

    
    private void simulateSale() {
        controller.startSale();

        registerAndPrint("abc123", 1);
        registerAndPrint("abc123", 1);
        registerAndPrint("def456", 1);

        SaleSummary summary = controller.endSale();
        double total = summary.totalPriceIncVAT;
        
        System.out.println("End sale:");
        System.out.println("Total cost (incl VAT): " + total + " SEK\n");

        controller.makePayment(100.0);
    }

    
    private void registerAndPrint(String itemId, int quantity) {
        System.out.println("\n\n\nAdd " + quantity + " item with item id " + itemId + ":");
        ItemDescription desc = controller.registerItem(itemId, quantity);
        if (desc != null) {
            System.out.println("Item ID: " + desc.getItemID());
            System.out.println("Item name: " + desc.getName());
            System.out.println("Item cost: " + desc.getPrice() + " SEK");
            System.out.println("VAT: " + desc.getVAT() + "%");
            System.out.println("Item description: " + desc.getDescription());
    
            
            SaleSummary summary = controller.endSale();
            System.out.println("Total cost (incl VAT): " + String.format("%.2f", summary.totalPriceIncVAT) + " SEK");
            System.out.println("Total VAT: " + String.format("%.2f", summary.totalVAT) + " SEK");
        } else {
            System.out.println("Invalid item ID: " + itemId);
        }
    }
    
}
