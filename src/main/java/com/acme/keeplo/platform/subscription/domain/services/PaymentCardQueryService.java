package com.acme.keeplo.platform.subscription.domain.services;

import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetPaymentCardByIdQuery;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetPaymentCardsByUserIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for handling queries related to payment cards.
 *
 * Provides method to retrieve a PaymentCard entity by its identifier.
 */
public interface PaymentCardQueryService {

    /**
     * Handles the retrieval of a payment card by its ID.
     *
     * @param query the query containing the ID of the payment card
     * @return an Optional containing the found PaymentCard entity, or empty if not found
     */
    Optional<PaymentCard> handle(GetPaymentCardByIdQuery query);
    Optional<List<PaymentCard>> handle(GetPaymentCardsByUserIdQuery query);

}