package com.acme.keeplo.platform.wishlist.application.internal.commandservices;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.wishlist.domain.model.commands.AddTagToCollectionCommand;
import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateCollectionCommand;
import com.acme.keeplo.platform.wishlist.domain.model.services.CollectionCommandService;
import com.acme.keeplo.platform.wishlist.domain.model.valueobjects.Tag;
import com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories.CollectionRepository;
import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;
import com.acme.keeplo.platform.wishlist.domain.model.commands.AddWishCommand;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CollectionCommandServiceImpl implements CollectionCommandService {

    private final CollectionRepository collectionRepository;

    public CollectionCommandServiceImpl(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    @Override
    public Optional<Collection> handle(CreateCollectionCommand command) {
        Collection collection = new Collection(command.name(), command.description(), command.isPublic());
        collectionRepository.save(collection);
        return Optional.of(collection);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!collectionRepository.existsById(id)) return false;
        collectionRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean addTagToCollection(AddTagToCollectionCommand command) {
        var collectionOptional = collectionRepository.findById(command.collectionId());
        if (collectionOptional.isEmpty()) return false;

        var collection = collectionOptional.get();
        var tag = new Tag(command.name(), command.color());
        collection.addTag(tag);
        collectionRepository.save(collection);

        return true;
    }

}
