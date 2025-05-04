package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.*;
import se.kth.iv1350.view.View;

public class Main {
    public static void main(String[] args) {
        ItemRegistry itemRegistry = new ItemRegistry();
        DiscountDatabase discountDB = new DiscountDatabase();
        ExternalAccounting accounting = new ExternalAccounting();
        InventorySystem inventory = new InventorySystem();
        Printer printer = new Printer();

        ExternalSystemHandler systemHandler = new ExternalSystemHandler(accounting, inventory, printer);

        Controller controller = new Controller(itemRegistry, discountDB, systemHandler);

        new View(controller);
    }
}
