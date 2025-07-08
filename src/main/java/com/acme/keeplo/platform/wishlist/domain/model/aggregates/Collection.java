package com.acme.keeplo.platform.wishlist.domain.model.aggregates;

import com.acme.keeplo.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateCollectionCommand;
import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;
import com.acme.keeplo.platform.wishlist.domain.model.valueobjects.Tag;
//import com.acme.keeplo.platform.wishlist.domain.model.events.CollectionCreatedEvent;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Aggregate root representing a user's collection of wishes.
 * A collection may have a parent collection, be marked as trash,
 * contain multiple wishes, and have associated tags.
 */
@Entity
public class Collection extends AuditableAbstractAggregateRoot<Collection> {
    /**
     * The title of the collection.
     */
    @Getter
    private String title;
    /**
     * Indicates whether the collection is marked as trash.
     */
    @Getter
    private boolean isInTrash;
    /**
     * ID of the parent collection, if this is a nested collection.
     */
    @Getter
    private Long idParentCollection;
    /**
     * ID of the user who owns this collection.
     */
    @Getter
    private Long idUser;
    /**
     * Default constructor for JPA.
     */
    public Collection() {
    }
    /**
     * Constructs a collection with the given attributes.
     *
     * @param name              The title of the collection.
     * @param isInTrash         Whether the collection is in trash.
     * @param idParentCollection The ID of the parent collection (nullable).
     * @param idUser            The ID of the user who owns the collection.
     */
    public Collection(String name,boolean isInTrash, Long idParentCollection, Long idUser) {
        this();
        this.title = name;
        this.isInTrash = isInTrash;
        this.idParentCollection = idParentCollection;
        this.idUser = idUser;
    }
    /**
     * Constructs a collection from a {@link CreateCollectionCommand}.
     *
     * @param command The command containing the data to create the collection.
     */
    public Collection(CreateCollectionCommand command) {
        this();
        this.title = command.title();
        this.isInTrash = command.isInTrash();
        this.idParentCollection = command.idParentCollection();
        this.idUser = command.idUser();
    }

    /**
     * Updates the collection's attributes.
     *
     * @param title             The new title.
     * @param isInTrash         Whether the collection is in trash.
     * @param idParentCollection The new parent collection ID.
     */
    public void update(String title, boolean isInTrash, Long idParentCollection) {
        this.title = title;
        this.isInTrash = isInTrash;
        this.idParentCollection = idParentCollection;
    }


}
