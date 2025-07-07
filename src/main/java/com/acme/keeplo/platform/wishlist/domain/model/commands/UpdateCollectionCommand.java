package com.acme.keeplo.platform.wishlist.domain.model.commands;
/**
 * Command used to update an existing collection.
 *
 * @param id                The ID of the collection to be updated.
 * @param title             The new title of the collection.
 * @param isInTrash         Whether the collection should be marked as trash.
 * @param idParentCollection The new parent collection ID, if applicable.
 */
public record UpdateCollectionCommand(
        Long id,
        String title,
        boolean isInTrash,
        Long idParentCollection) {
}