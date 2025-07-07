package com.acme.keeplo.platform.wishlist.domain.model.commands;
/**
 * Command used to remove all tags associated with a specific wish.
 *
 * @param wishId The ID of the wish whose tags should be removed.
 */
public record DeleteTagsOfWishCommand(Long wishId){
}
