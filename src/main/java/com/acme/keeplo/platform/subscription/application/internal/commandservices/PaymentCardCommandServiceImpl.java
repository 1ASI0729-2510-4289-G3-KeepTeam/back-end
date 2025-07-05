package com.acme.keeplo.platform.subscription.application.internal.commandservices;

import com.acme.keeplo.platform.iam.domain.model.aggregates.User;
import com.acme.keeplo.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards.CreatePaymentCardCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards.UpdatePaymentCardCommand;
import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard; // Import the correct entity
import com.acme.keeplo.platform.subscription.domain.services.PaymentCardCommandService;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.PaymentCardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 * Service that handles payment card-related command operations.
 *
 * Provides the logic for creating and updating payment cards.
 */
@Service
public class PaymentCardCommandServiceImpl implements PaymentCardCommandService {

    private final PaymentCardRepository paymentCardRepository;
    private final UserRepository userRepository;


    /**
     * Constructs the service with the required repository dependency.
     *
     * @param paymentCardRepository the repository used for payment card persistence
     */
    public PaymentCardCommandServiceImpl(PaymentCardRepository paymentCardRepository,
                                         UserRepository userRepository) {
        this.paymentCardRepository = paymentCardRepository;
        this.userRepository = userRepository;
    }

    /**
     * Handles the creation of a new payment card.
     *
     * @param command the command containing payment card data
     * @return an optional containing the created payment card
     */
    @Override
    public Optional<PaymentCard> handle(CreatePaymentCardCommand command) {
        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var newPaymentCard = new PaymentCard(
                command.cardNumber(),
                command.holderName(),
                command.expirationDate(),
                command.cvv()
        );

        newPaymentCard.setUser(user);

        paymentCardRepository.save(newPaymentCard);
        return Optional.of(newPaymentCard);
    }

    /**
     * Handles the update of an existing payment card.
     *
     * @param command the command containing updated payment card data
     * @return an optional containing the updated payment card, or empty if not found
     */
    @Override
    public Optional<PaymentCard> handle(UpdatePaymentCardCommand command) {
        var existingCard = paymentCardRepository.findById(command.cardId());
        if (existingCard.isEmpty()) {
            return Optional.empty();
        }

        var cardToUpdate = existingCard.get();
        cardToUpdate.update(
                command.cardNumber(),
                command.holderName(),
                command.expirationDate(),
                command.cvv()
        );

        paymentCardRepository.save(cardToUpdate);
        return Optional.of(cardToUpdate);
    }


}