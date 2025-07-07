package com.acme.keeplo.platform.wishlist.domain.model.queries;
/**
 * Query used to retrieve a collection by its unique identifier.
 *
 * @param id The ID of the collection to retrieve.
 */
public record GetCollectionByIdQuery(Long id) {
}
