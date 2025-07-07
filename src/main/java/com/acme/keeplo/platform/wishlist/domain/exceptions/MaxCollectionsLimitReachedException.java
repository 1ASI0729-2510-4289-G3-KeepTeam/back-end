package com.acme.keeplo.platform.wishlist.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Exception thrown when a user attempts to create more collections
 * than allowed by their current membership plan.
 *
 * <p>This exception results in an HTTP 409 Conflict response.</p>
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class MaxCollectionsLimitReachedException extends RuntimeException {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message explaining the collection limit violation.
     */
    public MaxCollectionsLimitReachedException(String message) {
        super(message);
    }
}
