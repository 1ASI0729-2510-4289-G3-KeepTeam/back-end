package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;
/**
 * Resource object representing the request payload for updating an existing wish.
 * Typically used in PUT or PATCH requests.
 *
 * @param title        The new title for the wish.
 * @param redirectUrl  The updated URL to which the wish should redirect.
 * @param description  The new description of the wish.
 * @param urlImg       The new image URL representing the wish.
 * @param collectionId The ID of the collection the wish belongs to (can be updated).
 * @param isInTrash    Indicates whether the wish is marked as trashed.
 */
public record UpdateWishResource(
        String title, String redirectUrl, String description, String urlImg, Long collectionId, Boolean isInTrash
) {}
