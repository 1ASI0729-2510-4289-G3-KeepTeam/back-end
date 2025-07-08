package com.acme.keeplo.platform.subscription.domain.exceptions;

/**
 * Exception thrown when a membership has a negative price.
 */
public class NegativeMembershipPriceException extends RuntimeException {
    public NegativeMembershipPriceException() {
        super("Membership price cannot be negative.");
    }
}