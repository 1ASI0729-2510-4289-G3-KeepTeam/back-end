package com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/**
 * Repository interface for accessing subscription data.
 *
 * Provides methods for querying Subscription entities from the database.
 */
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    /**
     * Retrieves a subscription by the user's ID.
     *
     * @param userId the ID of the user associated with the subscription
     */
    Optional<Subscription> findByUserId(Long userId);
}