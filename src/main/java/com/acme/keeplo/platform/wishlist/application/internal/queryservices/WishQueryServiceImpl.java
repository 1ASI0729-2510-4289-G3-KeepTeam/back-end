package com.acme.keeplo.platform.wishlist.application.internal.queryservices;

import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetAllWishesByCollectionId;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetCollectionByIdQuery;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetWishById;
import com.acme.keeplo.platform.wishlist.domain.model.services.WishQueryService;
import com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories.CollectionRepository;
import com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Implementation of {@link WishQueryService} responsible for handling queries
 * related to {@link Wish} entities.
 */
@Service
public class WishQueryServiceImpl implements WishQueryService {

    private final WishRepository wishRepository;
    /**
     * Constructs the query service with the required wish repository.
     *
     * @param wishRepository Repository used to access wish data.
     */
    public WishQueryServiceImpl(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }
    /**
     * Retrieves a wish by its unique ID.
     *
     * @param query The query containing the wish ID.
     * @return An {@link Optional} containing the wish if found, or empty otherwise.
     */
    @Override
    public Optional<Wish> handle(GetWishById query) {
        return wishRepository.findById(query.id());
    }
    /**
     * Retrieves all wishes that belong to a specific collection.
     *
     * @param query The query containing the collection ID.
     * @return A list of wishes associated with the specified collection.
     */
    @Override
    public List<Wish> handle(GetAllWishesByCollectionId query) {
        return wishRepository.findByCollectionId(query.collectionId());
    }
}
