package com.acme.keeplo.platform.wishlist.application.internal.commandservices;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.wishlist.domain.model.commands.*;
import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;
import com.acme.keeplo.platform.wishlist.domain.model.services.WishCommandService;
import com.acme.keeplo.platform.wishlist.domain.model.valueobjects.Tag;
import com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories.CollectionRepository;
import com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories.WishRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link WishCommandService} responsible for handling commands
 * related to {@link Wish} entities, such as create, update, delete, and tag management.
 */
@Service
public class WishCommandServiceImpl implements WishCommandService {

    private final WishRepository wishRepository;
    private final CollectionRepository collectionRepository;

    /**
     * Constructs the service with required repositories.
     *
     * @param wishRepository       Repository for managing wish persistence.
     * @param collectionRepository Repository for accessing collections.
     */
    public WishCommandServiceImpl(WishRepository wishRepository, CollectionRepository collectionRepository) {
        this.wishRepository = wishRepository;
        this.collectionRepository = collectionRepository;
    }
    /**
     * Handles the creation of a new wish and associates it with a collection.
     *
     * @param command The command containing wish creation data.
     * @return An {@link Optional} with the created wish.
     * @throws IllegalArgumentException if the specified collection does not exist.
     */
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
    /**
     * Handles the deletion of a wish by its ID.
     *
     * @param command The command containing the wish ID to delete.
     * @return {@code true} if the wish was deleted, {@code false} otherwise.
     */
    @Override
    public boolean handle(DeleteWishCommand command) {
        if (!wishRepository.existsById(command.wishId())) return false;
        wishRepository.deleteById(command.wishId());
        return true;
    }
    /**
     * Handles the update of an existing wish.
     *
     * @param command The command containing updated wish data.
     * @return An {@link Optional} with the updated wish, or empty if the wish does not exist.
     */
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
    /**
     * Handles the assignment of tags to a wish.
     *
     * @param command The command containing the wish ID and list of tags.
     * @return {@code true} if the tags were assigned successfully, {@code false} otherwise.
     */
    @Override
    public boolean handle(CreateTagToWishCommand command) {
        Optional<Wish> optionalWish = wishRepository.findById(command.wishId());
        if (optionalWish.isEmpty()) return false;

        Wish wish = optionalWish.get();

        wish.setTags(new ArrayList<>(command.tag()));
        wishRepository.save(wish);
        return true;
    }

    /**
     * Handles the deletion of all tags from a wish.
     *
     * @param command The command containing the wish ID.
     * @return {@code true} if tags were deleted successfully, {@code false} otherwise.
     */
    @Override
    public boolean handle(DeleteTagsOfWishCommand command) {
        if (!wishRepository.existsById(command.wishId())) return false;
        wishRepository.deleteTagsByWishId(command.wishId());
        return true;
    }

}