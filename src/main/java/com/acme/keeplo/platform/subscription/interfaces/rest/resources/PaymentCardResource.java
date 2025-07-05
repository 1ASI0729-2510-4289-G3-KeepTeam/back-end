package com.acme.keeplo.platform.subscription.interfaces.rest.resources;

import java.util.Date;

/**
 * Resource representing a payment card in the subscription system.
 * This resource is used for returning payment card data to API clients,
 * such as card number (masked or partial), cardholder name, expiration date, and CVV.
 *
 * @param id             the unique identifier of the payment card
 * @param cardNumber     the number of the card
 * @param holderName     the name of the cardholder
 * @param expirationDate the expiration date of the card
 * @param Cvv            the card verification value (CVV)
 */
public record PaymentCardResource(
        Long id,
        String cardNumber,
        String holderName,
        String expirationDate,
        String Cvv
) {}
