package com.acme.keeplo.platform.subscription.domain.exceptions;

/**
 * Exception thrown when a non-free membership is created or updated without a payment card.
 */
public class PaymentCardRequiredException extends RuntimeException {
    public PaymentCardRequiredException() {
        super("Non-free membership requires a valid payment card.");
    }
}