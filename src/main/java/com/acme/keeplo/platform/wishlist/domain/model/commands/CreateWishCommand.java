package com.acme.keeplo.platform.wishlist.domain.model.commands;

public record CreateWishCommand(String title, String redirectUrl, String description, String urlImg, Long collectionId, Boolean isInTrash) {
}
