package com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards;

import java.util.Date;
/**
 * Command to create a new payment card.
 *
 * <p>This command validates that all required fields are present and correctly formatted.
 * The expiration date must follow the MM/yy format.</p>
 *
 * @param cardNumber     The number of the payment card (required).
 * @param holderName     The name of the cardholder (required).
 * @param expirationDate The expiration date in MM/yy format (required).
 * @param cvv            The CVV security code of the card (required).
 * @param userId         The ID of the user to whom the card belongs.
 */
public record CreatePaymentCardCommand(
        String cardNumber,
        String holderName,
        String expirationDate,
        String cvv,
        Long userId
) {

    /**
     * Compact constructor that validates required fields and formats.
     *
     * @throws IllegalArgumentException if any required field is null, blank, or improperly formatted.
     */
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