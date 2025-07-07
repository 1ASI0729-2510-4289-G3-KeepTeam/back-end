package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;
/**
 * Resource object representing the request payload for creating a new wish.
 * Typically used in POST requests.
 *
 * @param title        The title of the wish.
 * @param redirectUrl  The URL to which the user will be redirected when selecting the wish.
 * @param description  A brief description of the wish.
 * @param urlImg       The URL of the image representing the wish.
 * @param collectionId The ID of the collection to which the wish belongs.
 * @param isInTrash    Indicates whether the wish is initially created in the trash.
 */

    public record CreateWishResource(String title, String redirectUrl, String description, String urlImg, Long collectionId, Boolean isInTrash){}

