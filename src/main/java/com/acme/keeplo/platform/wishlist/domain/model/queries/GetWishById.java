package com.acme.keeplo.platform.wishlist.domain.model.queries;
/**
 * Query used to retrieve a specific wish by its unique identifier.
 *
 * @param id The ID of the wish to retrieve.
 */
public record GetWishById (Long id){
}
