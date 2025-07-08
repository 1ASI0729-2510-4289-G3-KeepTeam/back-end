package com.acme.keeplo.platform.subscription.domain.exceptions;

/**
 * Exception thrown when a subscription is created or updated with a null membership.
 */
public class NullMembershipException extends RuntimeException {
    public NullMembershipException() {
        super("Subscription must have a membership.");
    }
}