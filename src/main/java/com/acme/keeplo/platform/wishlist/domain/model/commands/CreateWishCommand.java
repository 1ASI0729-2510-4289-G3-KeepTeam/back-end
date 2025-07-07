package com.acme.keeplo.platform.wishlist.domain.model.commands;
/**
 * Command used to create a new wish and associate it with a specific collection.
 *
 * @param title        The title of the wish.
 * @param redirectUrl  The URL to which the user will be redirected when clicking the wish.
 * @param description  A short description of the wish.
 * @param urlImg       The URL of the image representing the wish.
 * @param collectionId The ID of the collection to which this wish belongs.
 * @param isInTrash    Whether the wish is initially placed in the trash.
 */
public record CreateWishCommand(String title, String redirectUrl, String description, String urlImg, Long collectionId, Boolean isInTrash) {
}
