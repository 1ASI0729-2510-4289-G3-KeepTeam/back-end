package com.acme.keeplo.platform.wishlist.domain.model.commands;

public record UpdateCollectionCommand(
        Long id,
        String title,
        boolean isInTrash,
        Long idParentCollection) {
}