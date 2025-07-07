package com.acme.keeplo.platform.wishlist.domain.model.commands;
/**
 * Command used to update an existing wish.
 *
 * @param id          The ID of the wish to be updated.
 * @param title       The new title of the wish.
 * @param redirectUrl The new redirect URL associated with the wish.
 * @param description The new description of the wish.
 * @param urlImg      The new image URL representing the wish.
 * @param isInTrash   Whether the wish should be marked as trash.
 */
public record UpdateWishCommand(
        Long id,
        String title,
        String redirectUrl,
        String description,
        String urlImg,
        Boolean isInTrash) {
}