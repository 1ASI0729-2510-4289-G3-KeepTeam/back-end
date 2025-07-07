package com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards;

import java.util.Date;
/**
 * Command to update an existing payment card.
 *
 * <p>This command validates that all required fields are present and correctly formatted.
 * The expiration date must follow the MM/yy format, and the card ID must be a positive value.</p>
 *
 * @param cardId          The ID of the card to be updated (must be positive).
 * @param cardNumber      The number of the payment card (required).
 * @param holderName      The name of the cardholder (required).
 * @param expirationDate  The expiration date in MM/yy format (required).
 * @param cvv             The CVV security code of the card (required).
 */
public record UpdatePaymentCardCommand(
        Long cardId,
        String cardNumber,
        String holderName,
        String expirationDate,
        String cvv
) {
    /**
     * Compact constructor that validates required fields and formats.
     *
     * @throws IllegalArgumentException if any field is null, blank, invalid, or if cardId is not positive.
     */
    public UpdatePaymentCardCommand {
        if (cardId <= 0)
            throw new IllegalArgumentException("cardId must be positive.");
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