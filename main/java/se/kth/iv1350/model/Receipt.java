package se.kth.iv1350.model;

import se.kth.iv1350.dto.ReceiptDTO;
import se.kth.iv1350.dto.ReceiptDTO.SoldItem;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a receipt generated at the end of a sale.
 * Contains the full transaction data as a DTO.
 */
public class Receipt {
    private final ReceiptDTO receiptDTO;

    /**
     * Creates a new receipt for the completed sale and payment.
     * @param sale The completed sale.
     * @param payment The payment made by the customer.
     */
    public Receipt(Sale sale, Payment payment) {
        this.receiptDTO = createDTO(sale, payment);
    }

    /**
     * Returns a data transfer object containing receipt information.
     * @return The receipt data.
     */
    public ReceiptDTO getReceiptDTO() {
        return receiptDTO;
    }

    private ReceiptDTO createDTO(Sale sale, Payment payment) {
        List<SoldItem> itemList = new ArrayList<>();
        for (Item item : sale.getItems()) {
            itemList.add(new SoldItem(
                item.getName(),        
                item.getQuantity(),
                item.getPrice(),
                item.getVAT() / 100.0  
            ));
        }

        double totalPrice = sale.getTotalPriceIncVAT();
        double totalVAT = sale.getTotalVAT();
        double amountPaid = payment.getAmountPaid();
        double change = amountPaid - totalPrice;

        return new ReceiptDTO(
            sale.getTimeOfSale(),
            itemList,
            totalPrice,
            totalVAT,
            amountPaid,
            change
        );
    }
}

