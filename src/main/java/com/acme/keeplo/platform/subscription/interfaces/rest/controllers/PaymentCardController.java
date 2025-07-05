package com.acme.keeplo.platform.subscription.interfaces.rest.controllers;

import com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards.CreatePaymentCardCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards.UpdatePaymentCardCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions.CreateSubscriptionCommand;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetPaymentCardsByUserIdQuery;
import com.acme.keeplo.platform.subscription.domain.services.PaymentCardCommandService;
import com.acme.keeplo.platform.subscription.domain.services.PaymentCardQueryService;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.CreatePaymentCardResource;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.PaymentCardResource;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.UpdatePaymentCardResource;
import com.acme.keeplo.platform.subscription.interfaces.rest.transform.PaymentCardResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST controller for managing Payment Cards.
 * Provides endpoints to create and update user payment cards.
 */
@RestController
@RequestMapping(value = "/api/v1/payment-cards", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Payment Cards", description = "Endpoints for managing payment cards")
public class PaymentCardController {

    private final PaymentCardCommandService paymentCardCommandService;
    private final PaymentCardQueryService paymentCardQueryService;

    /**
     * Constructs a new PaymentCardController with the required PaymentCardCommandService.
     *
     * @param paymentCardCommandService service handling payment card commands
     */
    public PaymentCardController(PaymentCardCommandService paymentCardCommandService,
     PaymentCardQueryService paymentCardQueryService) {
        this.paymentCardCommandService = paymentCardCommandService;
        this.paymentCardQueryService = paymentCardQueryService;
    }

    /**
     * Creates a new payment card.
     *
     * @param resource the resource containing payment card data
     * @return ResponseEntity with the created payment card resource or bad request
     */
    @PostMapping
    @Operation(summary = "Create a new payment card", description = "Creates a new payment card.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment card created successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request (e.g., invalid data).")
    })
    public ResponseEntity<PaymentCardResource> createPaymentCard(@RequestBody CreatePaymentCardResource resource) {
        var command = new CreatePaymentCardCommand(
                resource.cardNumber(),
                resource.holderName(),
                resource.expirationDate(),
                resource.cvv(),
                resource.userId()
        );

        var result = paymentCardCommandService.handle(command);

        return result.map(card ->
                new ResponseEntity<>(PaymentCardResourceFromEntityAssembler.toResourceFromEntity(card), CREATED)
        ).orElse(ResponseEntity.badRequest().build());
    }

    /**
     * Updates an existing payment card.
     *
     * @param paymentCardId ID of the payment card to update
     * @param resource      the resource containing updated payment card data
     * @return ResponseEntity with the updated payment card resource or bad request
     */
    @PutMapping("/{paymentCardId}")
    @Operation(summary = "Update an existing payment card", description = "Updates an existing payment card.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment card updated successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request (e.g., card not found).")
    })
    public ResponseEntity<PaymentCardResource> updatePaymentCard(@PathVariable Long paymentCardId, @RequestBody UpdatePaymentCardResource resource) {
        var command = new UpdatePaymentCardCommand(
                paymentCardId,
                resource.cardNumber(),
                resource.holderName(),
                resource.expirationDate(),
                resource.cvv()
        );

        var result = paymentCardCommandService.handle(command);
        return result.map(card ->
                ResponseEntity.ok(PaymentCardResourceFromEntityAssembler.toResourceFromEntity(card))
        ).orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get payment cards by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment cards retrieved"),
            @ApiResponse(responseCode = "404", description = "No payment cards found for this user")
    })
    public ResponseEntity<List<PaymentCardResource>> getCardsByUserId(@PathVariable Long userId) {
        var query = new GetPaymentCardsByUserIdQuery(userId);
        var result = paymentCardQueryService.handle(query);

        return result.map(cards -> ResponseEntity.ok(
                        cards.stream()
                                .map(PaymentCardResourceFromEntityAssembler::toResourceFromEntity)
                                .toList()
                )
        ).orElse(ResponseEntity.notFound().build());
    }


}