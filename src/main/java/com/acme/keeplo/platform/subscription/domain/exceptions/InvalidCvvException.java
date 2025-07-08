package com.acme.keeplo.platform.subscription.domain.exceptions;

/**
 * Exception thrown when the CVV is null or blank.
 */
public class InvalidCvvException extends RuntimeException {
    public InvalidCvvException() {
        super("CVV cannot be null or empty.");
    }
}