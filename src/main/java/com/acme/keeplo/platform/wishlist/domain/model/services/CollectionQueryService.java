package com.acme.keeplo.platform.wishlist.domain.model.services;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetAllCollectionsByParentCollectionId;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetAllCollectionsByUserId;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetAllCollectionsQuery;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetCollectionByIdQuery;

import java.util.List;
import java.util.Optional;
/**
 * Application service interface for handling read operations related to {@link Collection} aggregates.
 * Represents the query side of the CQRS pattern.
 */
    public interface CollectionQueryService {

        /**
         * Retrieves a collection by its unique identifier.
         *
         * @param query The query containing the collection ID.
         * @return An {@link Optional} containing the collection if found, or empty otherwise.
         */
        Optional<Collection> handle(GetCollectionByIdQuery query);
        /**
         * Retrieves all collections in the system.
         *
         * @param query The query object (not used in this case).
         * @return A list of all collections.
         */
        List<Collection> handle(GetAllCollectionsQuery query);

        /**
         * Retrieves all collections owned by a specific user.
         *
         * @param query The query containing the user ID.
         * @return A list of collections owned by the specified user.
         */
        List<Collection> handle(GetAllCollectionsByUserId query);
        /**
         * Retrieves all collections that are children of a specified parent collection.
         *
         * @param query The query containing the parent collection ID.
         * @return A list of collections that belong to the specified parent collection.
         */
        List<Collection> handle(GetAllCollectionsByParentCollectionId query);
    }
