package com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
    Optional<Collection> findById(Long id);
    List<Collection> findCollectionByIdUser(Long id);
    List<Collection> findCollectionByIdParentCollection(Long id);
}
