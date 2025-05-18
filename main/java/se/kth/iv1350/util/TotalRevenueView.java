package se.kth.iv1350.util;

/**
 * Displays the total revenue to the console every time a new payment is made.
 */
public class TotalRevenueView implements RevenueObserver {
    private double totalRevenue;

    @Override
    public void newPayment(double paidAmount) {
        totalRevenue += paidAmount;
        System.out.println("[TotalRevenueView] Updated total revenue: " + String.format("%.2f", totalRevenue) + " SEK");
    }
}
