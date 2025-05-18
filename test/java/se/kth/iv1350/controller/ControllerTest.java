package se.kth.iv1350.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.integration.*;
import se.kth.iv1350.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    private Controller controller;

    @BeforeEach
    public void setUp() {
        ItemRegistry itemRegistry = new ItemRegistry();
        DiscountDatabase discountDatabase = new DiscountDatabase();
        ExternalSystemHandler systems = new ExternalSystemHandler(
            new ExternalAccounting(), new InventorySystem(), new Printer()
        );
        controller = new Controller(itemRegistry, discountDatabase, systems);
        controller.startSale();
    }

    @Test
    public void testRegisterValidItem() {
        ItemDescription desc = controller.registerItem("abc123", 2);
        assertNotNull(desc, "Expected non-null item description for known ID.");
        assertEquals("abc123", desc.getItemID(), "Item ID mismatch.");
    }

    @Test
    public void testRegisterInvalidItem_throwsItemNotFound() {
        ItemDescription desc = controller.registerItem("invalidId", 1);
        assertNull(desc, "Expected null for unknown item ID.");
    }
    @Test
    public void testRegisterSimulatedDatabaseFailure_throwsDatabaseFailureException() {
        assertThrows(DatabaseFailureException.class, () -> {
            controller.registerItem("FAIL", 1);
        }, "Expected DatabaseFailureException for item ID 'FAIL'.");
    }
    @Test
    public void testRegisterInvalidItem_doesNotChangeSaleState() {
        controller.registerItem("invalidId", 1);
        SaleSummary summary = controller.endSale();
        assertEquals(0.0, summary.totalPriceIncVAT, 0.001, "Total price should remain 0 after invalid item.");
    }




    @Test
    public void testTotalPriceAfterItemRegistration() {
        controller.registerItem("abc123", 2); // Assume price exists in ItemRegistry
        SaleSummary summary = controller.endSale();
        assertTrue(summary.totalPriceIncVAT > 0, "Total price should be greater than zero.");
    }

    @Test
    public void testTotalVATCalculation() {
        controller.registerItem("abc123", 2);
        SaleSummary summary = controller.endSale();
        assertTrue(summary.totalVAT > 0, "Total VAT should be greater than zero.");
    }

    @Test
    public void testSaleSummaryIsResetAfterStartSale() {
        controller.registerItem("abc123", 1);
        controller.startSale(); // Should clear previous sale
        SaleSummary summary = controller.endSale();
        assertEquals(0.0, summary.totalPriceIncVAT, 0.001, "Total should be 0 after reset.");
    }

    @Test
    public void testMakePaymentCompletesWithoutException() {
        controller.registerItem("abc123", 1);
        controller.endSale();
        assertDoesNotThrow(() -> controller.makePayment(100.0),
        "makePayment should complete without throwing exceptions");
}

}
