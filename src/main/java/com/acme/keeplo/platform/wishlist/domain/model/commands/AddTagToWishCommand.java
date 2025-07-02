package com.acme.keeplo.platform.wishlist.domain.model.commands;

public record AddTagToWishCommand(Long wishId, String name, String color) {
}
