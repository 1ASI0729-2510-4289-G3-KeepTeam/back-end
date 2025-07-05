package com.acme.keeplo.platform.subscription.interfaces.rest.resources;

import java.util.Date;
/**
 * Resource representing the data required to create a new payment card.
 * This resource is typically received as part of an HTTP POST request to the
 * payment cards endpoint. It includes the card number, holder name, expiration
 * date, and CVV.
 *
 * @param cardNumber     the credit/debit card number
 * @param holderName     the full name of the card holder
 * @param expirationDate the expiration date of the card
 * @param cvv            the card's CVV security code
 * @throws IllegalArgumentException if any required field is null
 */
public record CreatePaymentCardResource(String cardNumber, String holderName, String expirationDate, String cvv) {
    /**
     * Constructs a CreatePaymentCardResource with validation.
     *
     * @throws IllegalArgumentException if any field is null
     */
    public CreatePaymentCardResource {
        if (cardNumber == null)
            throw new IllegalArgumentException("cardNumber must be provided.");
        if (holderName == null)
            throw new IllegalArgumentException("holderName must be provided.");
        if (expirationDate == null)
            throw new IllegalArgumentException("expirationDate must be provided");
        if (!expirationDate.matches("^(0[1-9]|1[0-2])/\\d{2}$"))
            throw new IllegalArgumentException("expirationDate must be in MM/yy format.");
        if (cvv == null)
            throw new IllegalArgumentException("cvv must be provided");
    }

}
