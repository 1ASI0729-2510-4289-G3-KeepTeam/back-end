package com.acme.keeplo.platform.subscription.domain.exceptions;

/**
 * Exception thrown when the card number is null.
 */
public class InvalidCardNumberException extends RuntimeException {
    public InvalidCardNumberException() {
        super("Card number cannot be null.");
    }
}