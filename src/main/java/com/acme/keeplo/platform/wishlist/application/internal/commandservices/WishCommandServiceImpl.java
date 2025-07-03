package com.acme.keeplo.platform.wishlist.application.internal.commandservices;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateWishCommand;
import com.acme.keeplo.platform.wishlist.domain.model.commands.UpdateWishCommand;
import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;
import com.acme.keeplo.platform.wishlist.domain.model.services.WishCommandService;
import com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories.CollectionRepository;
import com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories.WishRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class WishCommandServiceImpl implements WishCommandService {

    private final WishRepository wishRepository;
    private final CollectionRepository collectionRepository;

    public WishCommandServiceImpl(WishRepository wishRepository, CollectionRepository collectionRepository) {
        this.wishRepository = wishRepository;
        this.collectionRepository = collectionRepository;
    }

    @Override
    public Optional<Wish> handle(CreateWishCommand command) {

        Collection collection = collectionRepository.findById(command.collectionId())
                .orElseThrow(() -> new IllegalArgumentException("Collection not found with ID: " + command.collectionId()));

        Wish wish = new Wish(
                command.title(),
                command.description(),
                command.redirectUrl(),
                collection,
                command.isInTrash(),
                command.urlImg()
        );

        wishRepository.save(wish);
        return Optional.of(wish);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!wishRepository.existsById(id)) return false;
        wishRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<Wish> handle(UpdateWishCommand command) {
        Optional<Wish> existingWishOptional = wishRepository.findById(command.id());

        if (existingWishOptional.isEmpty()) {
            return Optional.empty();
        }

        Wish existingWish = existingWishOptional.get();

        existingWish.update(command.title(), command.redirectUrl(), command.description(), command.urlImg(), command.isInTrash());
        wishRepository.save(existingWish);
        return Optional.of(existingWish);
    }
}