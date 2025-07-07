package com.acme.keeplo.platform.wishlist.domain.exceptions;

public class MaxCollectionsLimitReachedException extends RuntimeException {
    public MaxCollectionsLimitReachedException(String membershipName) {
        super("You have reached the maximum number of collections allowed for your membership: " + membershipName);
    }
}
