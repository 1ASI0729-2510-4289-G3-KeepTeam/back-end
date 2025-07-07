package com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories;

import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * JPA repository interface for managing {@link Wish} entities.
 * Provides methods for retrieving and manipulating wishes in the database.
 */
@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
    /**
     * Retrieves all wishes associated with the given collection ID.
     *
     * @param collectionId The ID of the collection.
     * @return A list of wishes belonging to the specified collection.
     */
    List<Wish> findByCollectionId(Long collectionId);
    /**
     * Deletes all tags associated with a specific wish.
     *
     * @param wishId The ID of the wish whose tags will be removed.
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM wish_tags WHERE wish_id = :wishId", nativeQuery = true)
    void deleteTagsByWishId(Long wishId);
}