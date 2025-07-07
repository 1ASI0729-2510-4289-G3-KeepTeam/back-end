package com.acme.keeplo.platform.wishlist.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MaxCollectionsLimitReachedException extends RuntimeException {
    public MaxCollectionsLimitReachedException(String message) {
        super(message);
    }
}
