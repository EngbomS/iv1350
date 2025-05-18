package se.kth.iv1350.integration;

/**
 * Thrown to simulate a database connection failure.
 */
public class DatabaseFailureException extends RuntimeException {
    public DatabaseFailureException(String itemID) {
        super("Simulated database failure while searching for item ID: " + itemID);
    }
}
