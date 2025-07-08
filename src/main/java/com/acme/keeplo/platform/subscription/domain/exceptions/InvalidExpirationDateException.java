package com.acme.keeplo.platform.subscription.domain.exceptions;

/**
 * Exception thrown when the expiration date is null or does not match MM/yy.
 */
public class InvalidExpirationDateException extends RuntimeException {
    public InvalidExpirationDateException() {
        super("Expiration date must be in MM/yy format.");
    }
}