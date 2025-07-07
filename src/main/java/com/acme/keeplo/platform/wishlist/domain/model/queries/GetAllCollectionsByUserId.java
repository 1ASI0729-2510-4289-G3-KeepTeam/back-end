package com.acme.keeplo.platform.wishlist.domain.model.queries;
/**
 * Query used to retrieve all collections associated with a specific user.
 *
 * @param userId The ID of the user whose collections are to be retrieved.
 */
public record GetAllCollectionsByUserId(Long userId){}