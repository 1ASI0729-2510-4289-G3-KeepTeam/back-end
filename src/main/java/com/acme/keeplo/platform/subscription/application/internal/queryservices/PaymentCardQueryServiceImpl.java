package com.acme.keeplo.platform.subscription.application.internal.queryservices;

import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetPaymentCardByIdQuery;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetPaymentCardsByUserIdQuery;
import com.acme.keeplo.platform.subscription.domain.services.PaymentCardQueryService;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.PaymentCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Service implementation for handling queries related to payment cards.
 *
 * Provides methods to retrieve payment card details by ID.
 */
@Service
public class PaymentCardQueryServiceImpl implements PaymentCardQueryService {

    private final PaymentCardRepository paymentCardRepository;

    /**
     * Constructs the query service with the required payment card repository.
     *
     * @param paymentCardRepository payment card data access layer
     */
    public PaymentCardQueryServiceImpl(PaymentCardRepository paymentCardRepository) {
        this.paymentCardRepository = paymentCardRepository;
    }

    /**
     * Handles the retrieval of a payment card by its ID.
     *
     * @param query the query containing the payment card ID
     * @return an Optional containing the payment card if found, or empty otherwise
     */
    @Override
    public Optional<PaymentCard> handle(GetPaymentCardByIdQuery query) {
        return paymentCardRepository.findById(query.cardId());
    }

    @Override
    public Optional<List<PaymentCard>> handle(GetPaymentCardsByUserIdQuery query) {
        List<PaymentCard> cards = paymentCardRepository.findAllByUser_Id(query.userId());
        return cards.isEmpty() ? Optional.empty() : Optional.of(cards);
    }
}