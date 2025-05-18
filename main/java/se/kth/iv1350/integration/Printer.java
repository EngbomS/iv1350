package se.kth.iv1350.integration;

import se.kth.iv1350.dto.ReceiptDTO;
import se.kth.iv1350.dto.ReceiptDTO.SoldItem;

import java.time.format.DateTimeFormatter;

/**
 * Represents the printer that prints receipts.
 */
public class Printer {

    /**
     * Prints a formatted receipt based on the given DTO.
     * @param receipt The receipt DTO.
     */
    public void printReceipt(ReceiptDTO receipt) {
        StringBuilder sb = new StringBuilder();
        sb.append("------------------ Begin receipt -------------------\n");
        sb.append("Time of Sale: ").append(receipt.timeOfSale.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n\n");

        sb.append("Items:\n");
        for (SoldItem item : receipt.items) {
            sb.append(String.format("%s x%d - %.2f SEK (VAT %.0f%%)\n",
                    item.name,
                    item.quantity,
                    item.price * item.quantity,
                    item.vatRate * 100));
        }

        sb.append("\nTotal Price (incl. VAT): ").append(String.format("%.2f", receipt.totalPrice)).append(" SEK\n");
        sb.append("Total VAT: ").append(String.format("%.2f", receipt.totalVAT)).append(" SEK\n");
        sb.append("Amount Paid: ").append(String.format("%.2f", receipt.amountPaid)).append(" SEK\n");
        sb.append("Change: ").append(String.format("%.2f", receipt.change)).append(" SEK\n");
        sb.append("------------------- End receipt --------------------");

        System.out.println(sb.toString());
    }
}
