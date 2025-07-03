package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;

public record UpdateWishResource(
        String title, String redirectUrl, String description, String urlImg, Long collectionId, Boolean isInTrash
) {}
