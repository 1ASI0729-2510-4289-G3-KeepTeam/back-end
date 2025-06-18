package com.acme.keeplo.platform.wishlist.domain.model.services;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetAllCollectionsQuery;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetCollectionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CollectionQueryService {
    Optional<Collection> handle(GetCollectionByIdQuery query);
    List<Collection> handle(GetAllCollectionsQuery query);
}
