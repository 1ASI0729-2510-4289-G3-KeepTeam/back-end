package com.acme.keeplo.platform.unit.tests;

import com.acme.keeplo.platform.iam.domain.model.aggregates.User;
import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.entity.Memberships;
import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionTests {
    private User createTestUser() {
        return new User("user@ejemplo.com", "password", "UserEjemplo", "imagenEjemplo.png");
    }

    @Test
    public void createSubscription_WithValidData_ShouldCreateSuccessfully() {
        // Arrange
        Memberships membership = new Memberships("Plus", 9.99f, "Access to extra features");
        PaymentCard card = new PaymentCard("1234567890123456", "John Doe", "12/25", "123");
        User user = createTestUser();

        // Act
        Subscription subscription = new Subscription(membership, card, user);

        // Assert
        assertEquals(membership, subscription.getMembership());
        assertEquals(card, subscription.getPaymentCard());
        assertEquals(user, subscription.getUser());
    }

    @Test
    public void createSubscription_WithoutCardForPaidPlan_ShouldThrowException() {
        // Arrange
        Memberships membership = new Memberships("Plus", 5.99f, "Extra features");
        User user = createTestUser();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Subscription(membership, null, user));
    }

    @Test
    public void createSubscription_WithFreePlan_ShouldAllowNullCard() {
        // Arrange
        Memberships freeMembership = new Memberships("Free", 0.0f, "Limited access");
        User user = createTestUser();

        // Act
        Subscription subscription = new Subscription(freeMembership, null, user);

        // Assert
        assertEquals(freeMembership, subscription.getMembership());
        assertNull(subscription.getPaymentCard());
        assertEquals(user, subscription.getUser());
    }

    @Test
    public void updateMembership_ShouldReplaceMembershipSuccessfully() {
        // Arrange
        Memberships initial = new Memberships("Free", 0.0f, "Limited access");
        Memberships updated = new Memberships("Infinite", 9.99f, "Full access");

        User user = createTestUser();
        Subscription subscription = new Subscription(initial, null, user);

        // Act
        subscription.updateMembership(updated);

        // Assert
        assertEquals(updated, subscription.getMembership());
    }

    @Test
    public void updatePaymentCard_ShouldReplaceCardSuccessfully() {
        // Arrange
        Memberships membership = new Memberships("Infinite", 9.99f, "Full access");
        PaymentCard oldCard = new PaymentCard("1234567890123456", "Mango", "12/25", "123");
        PaymentCard newCard = new PaymentCard("9876543210987654", "Leo", "01/30", "456");
        User user = createTestUser();
        Subscription subscription = new Subscription(membership, oldCard, user);

        // Act
        subscription.updatePaymentCard(newCard);

        // Assert
        assertEquals(newCard, subscription.getPaymentCard());
    }

    @Test
    public void updatePaymentCard_WithNullForPaidPlan_ShouldThrowException() {
        // Arrange
        Memberships membership = new Memberships("Plus", 5.99f, "Access to extra features");
        PaymentCard oldCard = new PaymentCard("1234567890123456", "John Doe", "12/25", "123");
        User user = createTestUser();
        Subscription subscription = new Subscription(membership, oldCard, user);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> subscription.updatePaymentCard(null));
    }

    @Test
    public void validateState_WithValidData_ShouldNotThrow() {
        // Arrange
        Memberships membership = new Memberships("Plus", 5.99f, "Paid plan");
        PaymentCard card = new PaymentCard("1234567890123456", "John Doe", "12/25", "123");
        User user = createTestUser();
        Subscription subscription = new Subscription(membership, card, user);

        // Act & Assert
        assertDoesNotThrow(subscription::validateState);
    }

    @Test
    public void validateState_WithMissingUser_ShouldThrow() {
        // Arrange
        Memberships membership = new Memberships("Plus", 5.99f, "Paid plan");
        PaymentCard card = new PaymentCard("1234567890123456", "John Doe", "12/25", "123");

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            Subscription invalidSubscription = new Subscription(membership, card, null);
            invalidSubscription.validateState();
        });
    }
}