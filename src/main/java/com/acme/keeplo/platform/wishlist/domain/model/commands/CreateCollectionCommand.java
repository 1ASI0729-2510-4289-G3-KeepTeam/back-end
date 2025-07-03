package com.acme.keeplo.platform.wishlist.domain.model.commands;

public record CreateCollectionCommand(
                                              String title,
                                              boolean isInTrash,
                                              Long idUser,
                                              Long idParentCollection) {
}
