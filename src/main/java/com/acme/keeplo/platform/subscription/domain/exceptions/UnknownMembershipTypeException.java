package com.acme.keeplo.platform.subscription.domain.exceptions;
/**
 * Exception thrown when a membership type is not recognized by the system.
 */
public class UnknownMembershipTypeException extends RuntimeException {
    /**
     * Constructs a new exception with a message indicating the unknown membership type.
     *
     * @param type The unrecognized membership type.
     */
    public UnknownMembershipTypeException(String type) {
        super("Unknown membership type: " + type);
    }
}

