package com.acme.keeplo.platform.wishlist.domain.model.commands;

import com.acme.keeplo.platform.wishlist.domain.model.valueobjects.Tag;

import java.util.List;

/**
 * Command used to assign a list of tags to a specific wish.
 *
 * @param wishId The ID of the wish to which tags will be assigned.
 * @param tag    The list of {@link Tag} objects to be associated with the wish.
 */
public record CreateTagToWishCommand(Long wishId, List<Tag> tag) {
}
