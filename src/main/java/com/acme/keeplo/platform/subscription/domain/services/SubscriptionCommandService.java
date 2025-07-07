package com.acme.keeplo.platform.subscription.domain.services;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions.CreateSubscriptionCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions.UpdateSubscriptionCommand;

import java.util.Optional;

/**
 * @name SubscriptionCommandService
 * @summary
 * This interface represents the service to handle Subscription commands.
 */

public interface SubscriptionCommandService {
    /**
     * Handles the create Subscription command.
     * @param command The created Subscription command.
     * @return The created Subscription.
     *
     * @throws IllegalArgumentException If ID is null or empty
     * @see CreateSubscriptionCommand
     */
    Optional<Subscription> handle(CreateSubscriptionCommand command);

    Optional<Subscription> handle(UpdateSubscriptionCommand command);

}
