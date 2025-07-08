package com.acme.keeplo.platform.subscription.domain.model.aggregates;

/**
 * Subscriptions Aggregate Root
 *
 * @summary
 * The Subscriptions class is an aggregate root that represents Subscriptions.
 * It is responsible for handling the CreateSubscriptions command.
 */

import com.acme.keeplo.platform.iam.domain.model.aggregates.User; // Importa tu nueva entidad User
import com.acme.keeplo.platform.subscription.domain.exceptions.NullMembershipException;
import com.acme.keeplo.platform.subscription.domain.exceptions.NullUserForSubscriptionException;
import com.acme.keeplo.platform.subscription.domain.exceptions.PaymentCardRequiredException;
import com.acme.keeplo.platform.subscription.domain.model.entity.Memberships;
import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard;
import jakarta.persistence.*;
import lombok.Getter; // Asegúrate de que esta importación esté presente
import lombok.Setter; // Asegúrate de que esta importación esté presente
import org.springframework.data.domain.AbstractAggregateRoot;


@Entity
@Getter
@Setter
public class Subscription extends AbstractAggregateRoot<Subscription> {

    /**
     * Unique identifier of the subscription.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Membership associated with the subscription.
     */
    @ManyToOne
    @JoinColumn(name = "membership_id", nullable = false)
    private Memberships membership;

    /**
     * Payment card used for the subscription, if applicable.
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "payment_card_id", unique = true)
    private PaymentCard paymentCard;

    /**
     * User who owns the subscription.
     */
    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    /**
     * Protected no-args constructor for JPA.
     */
    protected Subscription() {
    }

    /**
     * Creates a new subscription.
     *
     * @param membership the membership plan
     * @param paymentCard the payment card (required for non-free memberships)
     * @param user the user subscribing
     */
    public Subscription(Memberships membership, PaymentCard paymentCard, User user) {
        if (membership == null) throw new NullMembershipException();
        if (!membership.isFree() && paymentCard == null) throw new PaymentCardRequiredException();
        if (user == null) throw new NullUserForSubscriptionException();

        this.membership = membership;
        this.paymentCard = paymentCard;
        this.user = user;
    }

    /**
     * Updates the membership associated with the subscription.
     *
     * @param newMembership the new membership
     */
    public void updateMembership(Memberships newMembership) {
        if (newMembership == null) throw new IllegalArgumentException("New membership cannot be null");
        this.membership = newMembership;
    }

    /**
     * Updates the payment card associated with the subscription.
     *
     * @param newPaymentCard the new payment card
     */
    public void updatePaymentCard(PaymentCard newPaymentCard) {
        if (!this.membership.isFree() && newPaymentCard == null) {
            throw new IllegalArgumentException("Non-free membership requires a payment card.");
        }
        this.paymentCard = newPaymentCard;
    }

    /**
     * Validates the current state of the subscription.
     * Ensures that required fields are present and consistent.
     */
    public void validateState() {
        if (membership == null) throw new IllegalStateException("Subscription must have a membership");
        if (!membership.isFree() && paymentCard == null)
            throw new IllegalStateException("Non-free membership requires a payment card");
        if (user == null) throw new IllegalStateException("Subscription must be associated with a user");
    }
}