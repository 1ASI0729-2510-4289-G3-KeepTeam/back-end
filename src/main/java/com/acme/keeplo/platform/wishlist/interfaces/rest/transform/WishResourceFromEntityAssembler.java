package com.acme.keeplo.platform.wishlist.interfaces.rest.transform;

import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.WishResource;

import java.util.stream.Collectors;

public class WishResourceFromEntityAssembler {

    public static WishResource toResourceFromEntity(Wish wish) {
        return new WishResource(
                wish.getId(),
                wish.getTitle(),
                wish.getRedirectUrl(),
                wish.getDescription(),
                wish.getUrlImg(),
                wish.getIdCollection(),
                wish.getIsInTrash(),
                wish.getTags().stream()
                        .map(TagResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList())
        );
    }
}