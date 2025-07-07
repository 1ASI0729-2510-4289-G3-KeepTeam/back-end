package com.acme.keeplo.platform.wishlist.application.internal.queryservices;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetAllCollectionsByParentCollectionId;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetAllCollectionsByUserId;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetAllCollectionsQuery;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetCollectionByIdQuery;
import com.acme.keeplo.platform.wishlist.domain.model.services.CollectionQueryService;
import com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories.CollectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Implementation of {@link CollectionQueryService} that handles read operations
 * related to {@link Collection} entities using JPA repositories.
 */
@Service
public class CollectionQueryServiceImpl implements CollectionQueryService {

    private final CollectionRepository collectionRepository;
    /**
     * Constructs the service with the required collection repository.
     *
     * @param collectionRepository Repository used for accessing collection data.
     */
    public CollectionQueryServiceImpl(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }
    /**
     * Retrieves a collection by its unique ID.
     *
     * @param query The query containing the collection ID.
     * @return An {@link Optional} containing the collection if found, or empty otherwise.
     */
    @Override
    public Optional<Collection> handle(GetCollectionByIdQuery query) {
        return collectionRepository.findById(query.id());
    }
    /**
     * Retrieves all collections in the system.
     *
     * @param query The query object (not used in this case).
     * @return A list of all collections.
     */
    @Override
    public List<Collection> handle(GetAllCollectionsQuery query) {
        return collectionRepository.findAll();
    }
    /**
     * Retrieves all collections belonging to a specific user.
     *
     * @param query The query containing the user ID.
     * @return A list of collections owned by the specified user.
     */
    @Override
    public List<Collection> handle(GetAllCollectionsByUserId query) {
        return collectionRepository.findCollectionByIdUser(query.userId());
    }
    /**
     * Retrieves all collections that are children of a specific parent collection.
     *
     * @param query The query containing the parent collection ID.
     * @return A list of collections that are nested under the given parent collection.
     */
    @Override
    public List<Collection> handle(GetAllCollectionsByParentCollectionId query) {
        return collectionRepository.findCollectionByIdParentCollection(query.parentCollectionId());
    }
}
