package com.acme.keeplo.platform.subscription.interfaces.rest.resources;

import java.util.Date;
/**
 * Resource representing the data required to update an existing payment card.
 * This resource is used in the request body of the update operation.
 * It validates the presence of all required fields.
 *
 * @param cardNumber     the credit/debit card number
 * @param holderName     the name of the cardholder
 * @param expirationDate the expiration date of the card
 * @param cvv            the CVV security code of the card
 */
public record UpdatePaymentCardResource(String cardNumber, String holderName, String expirationDate, String cvv){
    public UpdatePaymentCardResource {
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