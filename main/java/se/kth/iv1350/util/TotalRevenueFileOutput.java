package se.kth.iv1350.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Logs the total revenue to a file each time a new payment is made.
 */
public class TotalRevenueFileOutput implements RevenueObserver {
    private static final String FILE_NAME = "total-revenue-log.txt";
    private double totalRevenue;

    @Override
    public void newPayment(double paidAmount) {
        totalRevenue += paidAmount;
        logToFile("Updated total revenue: " + String.format("%.2f", totalRevenue) + " SEK");
    }

    private void logToFile(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            writer.println(message);
        } catch (IOException e) {
            System.out.println("ERROR: Could not write to revenue log file.");
        }
    }
}
