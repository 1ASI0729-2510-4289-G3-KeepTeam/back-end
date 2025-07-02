package com.acme.keeplo.platform.wishlist.domain.model.commands;

public record AddWishCommand(String title, String description, String url, Long collectionId, Boolean isInTrash, String urlImg) {
}