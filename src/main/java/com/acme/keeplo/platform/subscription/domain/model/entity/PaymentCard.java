package com.acme.keeplo.platform.subscription.domain.model.entity;

import com.acme.keeplo.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * PaymentCard Entity
 *
 * Represents a payment card used for subscriptions.
 * Stores card number, holder's name, expiration date, and CVV.
 * Inherits auditing fields such as creation and update timestamps.
 */
@Getter
@Setter
@Entity
public class PaymentCard extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String holderName;

    @Column(nullable = false)
    private String expirationDate;

    @Column(nullable = false)
    private String cvv;

    /**
     * Protected constructor for JPA.
     */
    protected PaymentCard() {
        this.cardNumber = null;
        this.holderName = "";
        this.expirationDate = null;
        this.cvv = "";
    }

    /**
     * Constructs a new PaymentCard with the specified values.
     *
     * @param cardNumber the card number (must not be null)
     * @param holderName the name of the cardholder (must not be null or blank)
     * @param expirationDate the expiration date of the card (must not be null)
     * @param cvv the CVV code of the card (must not be null or blank)
     */
    public PaymentCard(String cardNumber, String holderName, String expirationDate, String cvv) {
        if (cardNumber == null) {
            throw new IllegalArgumentException("Card number cannot be null.");
        }
        if (holderName == null || holderName.isBlank()) {
            throw new IllegalArgumentException("Holder name cannot be null or empty.");
        }
        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date cannot be null.");
        }
        if (!expirationDate.matches("^(0[1-9]|1[0-2])/\\d{2}$")) {
            throw new IllegalArgumentException("Expiration date must be in MM/yy format.");
        }
        if (cvv == null || cvv.isBlank()) {
            throw new IllegalArgumentException("CVV cannot be null or empty.");
        }

        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    /**
     * Updates the current PaymentCard with new values.
     *
     * @param cardNumber the new card number (must not be null)
     * @param holderName the new holder name (must not be null or blank)
     * @param expirationDate the new expiration date (must not be null)
     * @param cvv the new CVV code (must not be null or blank)
     */
    public void update(String cardNumber, String holderName, String expirationDate, String cvv) {
        if (cardNumber == null) {
            throw new IllegalArgumentException("Card number cannot be null.");
        }
        if (holderName == null || holderName.isBlank()) {
            throw new IllegalArgumentException("Holder name cannot be null or empty.");
        }
        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date cannot be null.");
        }
        if (!expirationDate.matches("^(0[1-9]|1[0-2])/\\d{2}$")) {
            throw new IllegalArgumentException("Expiration date must be in MM/yy format.");
        }
        if (cvv == null || cvv.isBlank()) {
            throw new IllegalArgumentException("CVV cannot be null or empty.");
        }
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }
}