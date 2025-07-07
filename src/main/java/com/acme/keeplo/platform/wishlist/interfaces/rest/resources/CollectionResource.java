package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;
/**
 * Resource object representing a collection in the wishlist context.
 * Used to transfer data between the API and the client.
 *
 * @param id                 The unique identifier of the collection.
 * @param title              The title of the collection.
 * @param isInTrash          Indicates whether the collection is in the trash.
 * @param idUser             The ID of the user who owns the collection.
 * @param idParentCollection The ID of the parent collection, if this is a subcollection.
 */
public record CollectionResource(
        Long id,
        String title,
        boolean isInTrash,
        Long idUser,
        Long idParentCollection
) {}