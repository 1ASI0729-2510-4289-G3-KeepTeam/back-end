package com.acme.keeplo.platform.wishlist.domain.model.queries;
/**
 * Query used to retrieve all collections that are children of a specific parent collection.
 *
 * @param parentCollectionId The ID of the parent collection.
 */
public record GetAllCollectionsByParentCollectionId(Long parentCollectionId) {}
