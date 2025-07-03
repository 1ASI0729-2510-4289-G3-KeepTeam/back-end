package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;

public record CollectionResource(
        Long id,
        String title,
        boolean isInTrash,
        Long idUser,
        Long idParentCollection
) {}