package com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories;

import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
    List<Wish> findByCollectionId(Long collectionId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM wish_tags WHERE wish_id = :wishId", nativeQuery = true)
    void deleteTagsByWishId(Long wishId);
}