package se.kth.iv1350.util;

/**
 * Observer interface for objects interested in being notified
 * when a new payment has been made.
 */
public interface RevenueObserver {
    /**
     * Called when a new payment has been completed.
     * @param paidAmount The amount paid by the customer.
     */
    void newPayment(double paidAmount);
}
