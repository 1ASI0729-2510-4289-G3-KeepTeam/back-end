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

@Service
public class CollectionQueryServiceImpl implements CollectionQueryService {

    private final CollectionRepository collectionRepository;

    public CollectionQueryServiceImpl(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    @Override
    public Optional<Collection> handle(GetCollectionByIdQuery query) {
        return collectionRepository.findById(query.id());
    }

    @Override
    public List<Collection> handle(GetAllCollectionsQuery query) {
        return collectionRepository.findAll();
    }

    @Override
    public List<Collection> handle(GetAllCollectionsByUserId query) {
        return collectionRepository.findCollectionByIdUser(query.userId());
    }

    @Override
    public List<Collection> handle(GetAllCollectionsByParentCollectionId query) {
        return collectionRepository.findCollectionByIdParentCollection(query.parentCollectionId());
    }
}
