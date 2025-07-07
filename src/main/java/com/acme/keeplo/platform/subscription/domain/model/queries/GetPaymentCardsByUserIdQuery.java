package com.acme.keeplo.platform.subscription.domain.model.queries;
/**
 * Query to retrieve all payment cards associated with a specific user.
 *
 * @param userId The ID of the user whose payment cards are being requested.
 */
public record GetPaymentCardsByUserIdQuery(Long userId) {
}
