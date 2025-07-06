package com.acme.keeplo.platform.wishlist.domain.model.commands;

import com.acme.keeplo.platform.wishlist.domain.model.valueobjects.Tag;

import java.util.List;

public record CreateTagToWishCommand(Long wishId, List<Tag> tag) {
}
