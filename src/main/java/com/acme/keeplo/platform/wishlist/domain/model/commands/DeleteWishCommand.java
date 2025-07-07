package com.acme.keeplo.platform.wishlist.domain.model.commands;
/**
 * Command used to delete a specific wish by its ID.
 *
 * @param wishId The ID of the wish to be deleted.
 */
public record DeleteWishCommand(Long wishId) {
}
