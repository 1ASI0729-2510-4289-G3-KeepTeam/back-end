package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;

public record UpdateCollectionResource(
        String title,
        boolean isInTrash,
        Long idParentCollection
) {}