package com.acme.keeplo.platform.wishlist.domain.model.services;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateTagToWishCommand;
import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateCollectionCommand;
import com.acme.keeplo.platform.wishlist.domain.model.commands.UpdateCollectionCommand;

import java.util.Optional;

public interface CollectionCommandService {
    Optional<Collection> handle(CreateCollectionCommand command);
    boolean deleteById(Long id);
    boolean addTagToCollection(CreateTagToWishCommand command);
    Optional<Collection> handle(UpdateCollectionCommand command);
}