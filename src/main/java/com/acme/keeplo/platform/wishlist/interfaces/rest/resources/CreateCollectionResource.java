package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;
/**
 * Resource object used to represent the payload for creating a new collection.
 * This data is typically received in POST requests.
 *
 * @param title              The title of the new collection.
 * @param isInTrash          Indicates whether the collection is created in the trash.
 * @param idUser             The ID of the user creating the collection.
 * @param idParentCollection The ID of the parent collection, if this collection is a subcollection.
 */
public record CreateCollectionResource(
        String title,
        boolean isInTrash,
        Long idUser,
        Long idParentCollection
){}