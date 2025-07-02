package com.acme.keeplo.platform.wishlist.domain.model.commands;

public record CreateCollectionCommand(String name, String description, boolean isInTrash, Long idParentCollection, String idUser) {
}
