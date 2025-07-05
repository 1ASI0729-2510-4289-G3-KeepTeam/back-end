package com.acme.keeplo.platform.subscription.application.internal.commandservices;

import com.acme.keeplo.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions.CreateSubscriptionCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions.UpdateSubscriptionCommand;
import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard;
import com.acme.keeplo.platform.subscription.domain.services.SubscriptionCommandService;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.MembershipRepository;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.PaymentCardRepository;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.SubscriptionRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service that handles subscription-related command operations.
 *
 * Provides the logic for creating and updating subscriptions, including validation
 * of associated user, membership, and payment card data.
 */
@Service
public class SubscriptionCommandServiceImpl implements SubscriptionCommandService {

    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;
    private final PaymentCardRepository paymentCardRepository;
    private final SubscriptionRepository subscriptionRepository;

    /**
     * Constructs the service with required repository dependencies.
     *
     * @param userRepository user data access
     * @param membershipRepository membership data access
     * @param paymentCardRepository payment card data access
     * @param subscriptionRepository subscription data access
     */
    public SubscriptionCommandServiceImpl(
            UserRepository userRepository,
            MembershipRepository membershipRepository,
            PaymentCardRepository paymentCardRepository,
            SubscriptionRepository subscriptionRepository
    ) {
        this.userRepository = userRepository;
        this.membershipRepository = membershipRepository;
        this.paymentCardRepository = paymentCardRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    /**
     * Handles creation of a new subscription.
     *
     * Validates user and membership existence, ensures payment card is required for non-free memberships,
     * and verifies that the user does not already have an active subscription.
     *
     * @param command the command with subscription details
     * @return the created subscription, if successful
     */
    @Override
    public Optional<Subscription> handle(CreateSubscriptionCommand command) {
        var user = userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + command.userId()));

        var membership = membershipRepository.findById(command.membershipId())
                .orElseThrow(() -> new IllegalArgumentException("Membership not found with ID: " + command.membershipId()));

        PaymentCard paymentCard = null;
        if (command.paymentCardId() == null) {
                throw new IllegalArgumentException("Payment card is required for non-free membership.");
            }
        paymentCard = paymentCardRepository.findById(command.paymentCardId())
                    .orElseThrow(() -> new IllegalArgumentException("Payment card not found with ID: " + command.paymentCardId()));


        if (subscriptionRepository.findByUserId(user.getId()).isPresent()) {
            throw new IllegalStateException("User with ID " + user.getId() + " already has an active subscription.");
        }

        var subscription = new Subscription(membership, paymentCard, user);
        subscription.validateState();

        subscriptionRepository.save(subscription);
        user.setSubscription(subscription);
        userRepository.save(user);

        return Optional.of(subscription);
    }

    /**
     * Handles updating an existing subscription.
     *
     * Validates subscription, membership, and payment card existence, and applies the updates.
     *
     * @param command the command with updated subscription details
     * @return the updated subscription, if successful
     */
    @Override
    public Optional<Subscription> handle(UpdateSubscriptionCommand command) {
        var existingSubscription = subscriptionRepository.findById(command.subscriptionId())
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found with ID: " + command.subscriptionId()));

        var newMembership = membershipRepository.findById(command.membershipId())
                .orElseThrow(() -> new IllegalArgumentException("Membership not found with ID: " + command.membershipId()));

        PaymentCard newPaymentCard = null;
        if (command.paymentCardId() == null) {
            throw new IllegalArgumentException("Payment card is required for non-free membership.");
        }
        newPaymentCard = paymentCardRepository.findById(command.paymentCardId())
                .orElseThrow(() -> new IllegalArgumentException("Payment card not found with ID: " + command.paymentCardId()));

        existingSubscription.updateMembership(newMembership);
        existingSubscription.updatePaymentCard(newPaymentCard);
        existingSubscription.validateState();

        subscriptionRepository.save(existingSubscription);

        return Optional.of(existingSubscription);
    }
}