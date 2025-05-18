package se.kth.iv1350.util;

import java.io.*;
import java.time.LocalDateTime;

/**
 * Handles logging of exceptions and error messages to a file.
 */
public class FileLogger {
    private PrintWriter logStream;
    private static final String LOG_FILE_NAME = "error-log.txt";

    /**
     * Creates a new file logger that writes to error-log.txt.
     */
    public FileLogger() {
        try {
            logStream = new PrintWriter(new FileWriter(LOG_FILE_NAME, true), true);
        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG TO FILE.");
            ioe.printStackTrace();
        }
    }

    /**
     * Logs a simple message to the log file.
     * @param message The message to log.
     */
    public void log(String message) {
        logStream.println("[" + LocalDateTime.now() + "] " + message);
    }

    /**
     * Logs an exception with message and full stack trace.
     * @param exception The exception to log.
     */
    public void logException(Exception exception) {
        log("Exception occurred: " + exception.getMessage());
        exception.printStackTrace(logStream);
        logStream.println("---------------------------------------------------");
    }
}
