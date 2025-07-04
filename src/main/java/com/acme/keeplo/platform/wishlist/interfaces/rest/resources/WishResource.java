package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;

import java.util.List;

public record WishResource(
        Long id,
        String title,
        String redirectUrl,
        String description,
        String urlImg,
        Long collectionId,
        Boolean isInTrash,
        List<TagResource> tags
) {}
