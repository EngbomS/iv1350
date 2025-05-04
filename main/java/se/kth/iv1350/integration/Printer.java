package se.kth.iv1350.integration;
import se.kth.iv1350.model.Receipt;

public class Printer {
    public Printer() {
       
    }
    public void print(Receipt receipt) {
        System.out.println(receipt.getReceiptText());
    }
    
}
