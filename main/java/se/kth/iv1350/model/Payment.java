package se.kth.iv1350.model;

/**
 * Represents a payment made by the customer.
 */
public class Payment {
    private double amountPaid;

    /**
     * Creates a new instance representing the payment.
     * @param amountPaid The amount paid by the customer.
     */
    public Payment(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * Returns the amount paid by the customer.
     * @return The amount paid.
     */
    public double getAmountPaid() {
        return amountPaid;
    }
}
