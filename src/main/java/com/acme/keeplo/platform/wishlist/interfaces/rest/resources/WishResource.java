package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;

import java.util.List;
/**
 * Resource object representing a wish returned by the API.
 * Used as a response DTO for read operations.
 *
 * @param id           The unique identifier of the wish.
 * @param title        The title of the wish.
 * @param redirectUrl  The URL to which the user will be redirected when selecting the wish.
 * @param description  A short description of the wish.
 * @param urlImg       The URL of the image associated with the wish.
 * @param collectionId The ID of the collection the wish belongs to.
 * @param isInTrash    Indicates whether the wish is currently marked as trashed.
 * @param tags         A list of tags associated with the wish.
 */
public record WishResource(
        Long id,
        String title,
        String redirectUrl,
        String description,
        String urlImg,
        Long collectionId,
        Boolean isInTrash,
        List<TagResource> tags
) {}
