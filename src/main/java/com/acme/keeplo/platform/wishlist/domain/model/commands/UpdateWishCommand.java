package com.acme.keeplo.platform.wishlist.domain.model.commands;

public record UpdateWishCommand(
        Long id,
        String title,
        String redirectUrl,
        String description,
        String urlImg,
        Boolean isInTrash) {
}