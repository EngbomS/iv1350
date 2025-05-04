package se.kth.iv1350.model;

/**
 * A simple immutable DTO containing sale summary details like total price and total VAT.
 */
public class SaleSummary {
    public final double totalPriceIncVAT;
    public final double totalVAT;

    /**
 * summary details like total price and total VAT.
 */
    public SaleSummary(double totalPriceIncVAT, double totalVAT) {
        this.totalPriceIncVAT = totalPriceIncVAT;
        this.totalVAT = totalVAT;
    }
}
