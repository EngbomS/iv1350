package se.kth.iv1350.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {
    private Sale sale;
    private ItemDescription desc;

    @BeforeEach
    public void setUp() {
        sale = new Sale();
        desc = new ItemDescription("abc123", "BigWheel Oatmeal","BigWheel Oatmeal 500g, whole grain oats, high fiber, gluten free", 29.90, 12);
    }

    @Test
    public void testAddNewItemAddsCorrectQuantity() {
        sale.addItem(desc, 2);
        Item item = sale.getItems().iterator().next();
        assertEquals(2, item.getQuantity(), "Item quantity should be 2.");
    }

    @Test
    public void testIncreaseQuantityUpdatesExistingItem() {
        sale.addItem(desc, 1);
        sale.increaseQuantity("abc123", 2);
        Item item = sale.getItems().iterator().next();
        assertEquals(3, item.getQuantity(), "Item quantity should increase to 3.");
    }

    @Test
    public void testHasItemReturnsTrueIfItemExists() {
        sale.addItem(desc, 1);
        assertTrue(sale.hasItem("abc123"), "Expected item to be found.");
    }

    @Test
    public void testHasItemReturnsFalseIfItemMissing() {
        assertFalse(sale.hasItem("nonexistent"), "Expected item to be absent.");
    }

    @Test
    public void testGetSummaryCalculatesCorrectTotals() {
        sale.addItem(desc, 2); // 2 x 29.90 incl VAT
        SaleSummary summary = sale.getSummary();
        assertEquals(59.80, summary.totalPriceIncVAT, 0.01, "Incorrect total price.");
        assertTrue(summary.totalVAT > 0, "VAT should be greater than 0.");
    }
}
