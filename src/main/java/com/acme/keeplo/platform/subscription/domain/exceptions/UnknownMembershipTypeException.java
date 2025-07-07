package com.acme.keeplo.platform.subscription.domain.exceptions;

public class UnknownMembershipTypeException extends RuntimeException {
    public UnknownMembershipTypeException(String type) {
        super("Unknown membership type: " + type);
    }
}

