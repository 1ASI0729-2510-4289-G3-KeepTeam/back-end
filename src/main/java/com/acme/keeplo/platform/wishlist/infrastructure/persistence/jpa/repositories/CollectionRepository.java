package com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
/**
 * JPA repository interface for managing {@link Collection} entities.
 * Provides basic CRUD operations and custom queries for collections.
 */
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    /**
     * Finds a collection by its unique identifier.
     *
     * @param id The ID of the collection.
     * @return An {@link Optional} containing the collection if found, or empty otherwise.
     */
    Optional<Collection> findById(Long id);
    /**
     * Retrieves all collections associated with a specific user.
     *
     * @param id The ID of the user.
     * @return A list of collections belonging to the user.
     */
    List<Collection> findCollectionByIdUser(Long id);
    /**
     * Retrieves all collections that are children of a specific parent collection.
     *
     * @param id The ID of the parent collection.
     * @return A list of collections with the given parent ID.
     */
    List<Collection> findCollectionByIdParentCollection(Long id);
    /**
     * Counts the number of collections a user has that are not in the trash.
     *
     * @param idUser The ID of the user.
     * @return The number of active (non-trashed) collections.
     */
    int countByIdUserAndIsInTrashFalse(Long idUser);



}
