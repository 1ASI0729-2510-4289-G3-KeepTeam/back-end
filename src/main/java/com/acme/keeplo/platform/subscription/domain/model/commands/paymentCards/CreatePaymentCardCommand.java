package com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards;

import java.util.Date;
/**
 * Command to create a new payment card.
 *
 * Validates that all required fields are present and not blank.
 *
 * @param cardNumber The number of the payment card.
 * @param holderName The name of the card holder.
 * @param expirationDate The expiration date of the card.
 * @param cvv The CVV security code of the card.
 */
public record CreatePaymentCardCommand(
        String cardNumber,
        String holderName,
        String expirationDate,
        String cvv
) {
    public CreatePaymentCardCommand {
        if (cardNumber == null || cardNumber.isBlank())
            throw new IllegalArgumentException("cardNumber is required.");
        if (holderName == null || holderName.isBlank())
            throw new IllegalArgumentException("holderName is required.");
        if (expirationDate == null)
            throw new IllegalArgumentException("expirationDate is required.");
        if (!expirationDate.matches("^(0[1-9]|1[0-2])/\\d{2}$"))
            throw new IllegalArgumentException("expirationDate must be in MM/yy format.");
        if (cvv == null || cvv.isBlank())
            throw new IllegalArgumentException("cvv is required.");
    }
}