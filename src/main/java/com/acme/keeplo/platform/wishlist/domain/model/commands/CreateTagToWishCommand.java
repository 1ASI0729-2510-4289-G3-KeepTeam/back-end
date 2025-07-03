package com.acme.keeplo.platform.wishlist.domain.model.commands;

public record CreateTagToWishCommand(Long wishId, String name, String color) {
}
