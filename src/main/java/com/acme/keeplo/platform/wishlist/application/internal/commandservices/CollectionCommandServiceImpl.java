package com.acme.keeplo.platform.wishlist.application.internal.commandservices;

import com.acme.keeplo.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.acme.keeplo.platform.wishlist.domain.exceptions.MaxCollectionsLimitReachedException;
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
    private final UserRepository userRepository;

    public CollectionCommandServiceImpl(CollectionRepository collectionRepository, UserRepository userRepository) {
        this.collectionRepository = collectionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Collection> handle(CreateCollectionCommand command) {
        var user = userRepository.findById(command.idUser())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + command.idUser()));

        // 2. Obtener la suscripción y el límite permitido
        var subscription = user.getSubscription();
        var maxAllowed = subscription.getMembership().getMaxCollectionsAllowed();

        // 3. Contar colecciones activas
        int currentCount = collectionRepository.countByIdUserAndIsInTrashFalse(user.getId());

        // 4. Validar si excede el límite
        if (currentCount >= maxAllowed) {
            throw new MaxCollectionsLimitReachedException("Maximum number of collections reached for current membership.");
        }

        // 5. Crear y guardar la colección
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
