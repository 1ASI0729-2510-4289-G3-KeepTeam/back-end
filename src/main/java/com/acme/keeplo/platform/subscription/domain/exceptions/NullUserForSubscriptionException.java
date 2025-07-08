package com.acme.keeplo.platform.subscription.domain.exceptions;

/**
 * Exception thrown when a subscription is created without an associated user.
 */
public class NullUserForSubscriptionException extends RuntimeException {
    public NullUserForSubscriptionException() {
        super("Subscription must be associated with a user.");
    }
}