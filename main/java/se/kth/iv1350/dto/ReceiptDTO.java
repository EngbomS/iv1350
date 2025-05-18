package se.kth.iv1350.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A DTO that carries receipt information from the model to the integration layer.
 */
public class ReceiptDTO {
    public static class SoldItem {
        public final String name;
        public final int quantity;
        public final double price;
        public final double vatRate;

        public SoldItem(String name, int quantity, double price, double vatRate) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
            this.vatRate = vatRate;
        }
    }

    public final LocalDateTime timeOfSale;
    public final List<SoldItem> items;
    public final double totalPrice;
    public final double totalVAT;
    public final double amountPaid;
    public final double change;

    public ReceiptDTO(LocalDateTime timeOfSale, List<SoldItem> items, double totalPrice,
                      double totalVAT, double amountPaid, double change) {
        this.timeOfSale = timeOfSale;
        this.items = items;
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.amountPaid = amountPaid;
        this.change = change;
    }
}
