package com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa;

import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
/**
 * Repository interface for accessing payment card data.
 *
 * Provides methods for querying PaymentCard entities from the database.
 */
public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long> {

    /**
     * Retrieves all payment cards from the database.
     *
     * @return a list of all PaymentCard entities
     */
    List<PaymentCard> findAll();

    /**
     * Retrieves a payment card by its ID.
     *
     * @param id the ID of the payment card
     * @return an Optional containing the PaymentCard if found, or empty if not found
     */
    Optional<PaymentCard> findById(Long id);
    List<PaymentCard> findAllByUser_Id(Long userId);

}