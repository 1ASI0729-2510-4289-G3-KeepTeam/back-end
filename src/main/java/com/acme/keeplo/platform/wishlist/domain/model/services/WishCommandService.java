package com.acme.keeplo.platform.wishlist.domain.model.services;

import com.acme.keeplo.platform.wishlist.domain.model.commands.*;
import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;

import java.util.Optional;

/**
 * Application service interface for handling write operations related to {@link Wish} entities.
 * Represents the command side of the CQRS pattern.
 */
public interface WishCommandService {
    /**
     * Handles the creation of a new wish.
     *
     * @param command The command containing the data to create the wish.
     * @return An {@link Optional} containing the created wish if successful, or empty if creation failed.
     */
    Optional<Wish> handle(CreateWishCommand command);
    /**
     * Handles the deletion of a wish by its ID.
     *
     * @param command The command containing the ID of the wish to delete.
     * @return {@code true} if the wish was successfully deleted, {@code false} otherwise.
     */
    boolean handle(DeleteWishCommand command);
    /**
     * Handles assigning tags to an existing wish.
     *
     * @param command The command containing the wish ID and the tags to assign.
     * @return {@code true} if the tags were successfully assigned, {@code false} otherwise.
     */
    boolean handle(CreateTagToWishCommand command);
    /**
     * Handles removing all tags from a specific wish.
     *
     * @param command The command containing the wish ID from which tags will be removed.
     * @return {@code true} if the tags were successfully removed, {@code false} otherwise.
     */
    boolean handle(DeleteTagsOfWishCommand command);
    /**
     * Handles updating an existing wish.
     *
     * @param command The command containing the updated data for the wish.
     * @return An {@link Optional} containing the updated wish if successful, or empty if the update failed.
     */
    Optional<Wish> handle(UpdateWishCommand command);
}