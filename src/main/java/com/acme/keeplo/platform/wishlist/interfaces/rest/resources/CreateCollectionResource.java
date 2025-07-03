package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;

public record CreateCollectionResource(
        String title,
        boolean isInTrash,
        Long idUser,
        Long idParentCollection
){}