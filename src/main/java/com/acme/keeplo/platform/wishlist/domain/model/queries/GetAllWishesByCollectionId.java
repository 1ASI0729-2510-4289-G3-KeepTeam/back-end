package com.acme.keeplo.platform.wishlist.domain.model.queries;
/**
 * Query used to retrieve all wishes that belong to a specific collection.
 *
 * @param collectionId The ID of the collection whose wishes are to be retrieved.
 */
public record GetAllWishesByCollectionId(Long collectionId){
}
