package com.acme.keeplo.platform.wishlist.domain.model.commands;
/**
 * Command used to request the creation of a new collection for a specific user.
 *
 * @param title              The title of the new collection.
 * @param isInTrash          Whether the collection should be created in the trash.
 * @param idUser             The ID of the user who owns the collection.
 * @param idParentCollection The ID of the parent collection, if this is a sub-collection.
 */
public record CreateCollectionCommand(
                                              String title,
                                              boolean isInTrash,
                                              Long idUser,
                                              Long idParentCollection) {
}
