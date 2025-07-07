package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;
/**
 * Resource object representing the request payload for updating an existing collection.
 * Typically used in PUT or PATCH requests.
 *
 * @param title              The new title for the collection.
 * @param isInTrash          Indicates whether the collection should be moved to trash.
 * @param idParentCollection The new parent collection ID, if the collection is being reorganized.
 */
public record UpdateCollectionResource(
        String title,
        boolean isInTrash,
        Long idParentCollection
) {}