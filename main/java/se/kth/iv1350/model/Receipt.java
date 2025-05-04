package se.kth.iv1350.model;

import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Represents a receipt generated at the end of a sale.
 * Contains formatted text detailing the transaction.
 */
public class Receipt {
    private String receiptText;

    /**
     * Creats a new receipt for the completed sale and payment.
     * @param sale The completed sale.
     * @param payment The payment made by the customer.
     */
    public Receipt(Sale sale, Payment payment) {
        this.receiptText = generateReceiptText(sale, payment);
    }

    /**
     * Returns the formatted receipt as a string.
     * @return The receipt text.
     */
    public String getReceiptText() {
        return receiptText;
    }

    
    private String generateReceiptText(Sale sale, Payment payment) {
        StringBuilder sb = new StringBuilder();
        sb.append("------------------ Begin receipt -------------------\n");
        sb.append("Time of Sale: ")
          .append(sale.getTimeOfSale().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
          .append("\n\n");

          for (Item item : sale.getItems()) {
            sb.append(item.getName()).append(" ")
            .append(item.getQuantity()).append(" x ")
            .append(String.format("%.2f", item.getPrice())).append(" ")
            .append(String.format("%.2f", item.getTotalPriceIncVAT())).append(" SEK\n");

        }
        

        sb.append("\nTotal: ").append(String.format("%.2f", sale.getTotalPriceIncVAT())).append(" SEK\n");
        sb.append("VAT: ").append(String.format("%.2f", sale.getTotalVAT())).append("\n");
        sb.append("Cash: ").append(String.format("%.2f", payment.getAmountPaid())).append(" SEK\n");
        sb.append("Change: ").append(String.format("%.2f", payment.getAmountPaid() - sale.getTotalPriceIncVAT())).append(" SEK\n");
        sb.append("------------------ End receipt ---------------------\n");

        return sb.toString();
    }
}
