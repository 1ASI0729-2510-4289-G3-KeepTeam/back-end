package com.acme.keeplo.platform.wishlist.application.internal.commandservices;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateTagToWishCommand;
import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateCollectionCommand;
import com.acme.keeplo.platform.wishlist.domain.model.commands.UpdateCollectionCommand;
import com.acme.keeplo.platform.wishlist.domain.model.services.CollectionCommandService;
import com.acme.keeplo.platform.wishlist.domain.model.valueobjects.Tag;
import com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories.CollectionRepository;

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
        Collection collection = new Collection(command);
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
    public Optional<Collection> handle(UpdateCollectionCommand command) {
        Optional<Collection> existingCollectionOptional = collectionRepository.findById(command.id());

        if (existingCollectionOptional.isEmpty()) {
            return Optional.empty();
        }

        Collection existingCollection = existingCollectionOptional.get();

        existingCollection.update(command.title(), command.isInTrash(), command.idParentCollection());

        collectionRepository.save(existingCollection);
        return Optional.of(existingCollection);
    }
}
