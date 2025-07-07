package com.acme.keeplo.platform.wishlist.domain.model.services;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetAllWishesByCollectionId;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetWishById;

import java.util.List;
import java.util.Optional;
/**
 * Application service interface for handling read operations related to {@link Wish} entities.
 * Represents the query side of the CQRS pattern.
 */
public interface WishQueryService {

    /**
     * Retrieves a wish by its unique identifier.
     *
     * @param query The query containing the ID of the wish to retrieve.
     * @return An {@link Optional} containing the wish if found, or empty if not found.
     */
    Optional<Wish> handle(GetWishById query);
    /**
     * Retrieves all wishes associated with a specific collection.
     *
     * @param query The query containing the ID of the collection for which wishes are to be retrieved.
     * @return A list of wishes that belong to the specified collection.
     */
    List<Wish> handle(GetAllWishesByCollectionId query);

}
