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

@Entity
public class Collection extends AuditableAbstractAggregateRoot<Collection> {

    @Getter
    private String title;

    @Getter
    private boolean isInTrash;

    @Getter
    private Long idParentCollection;

    @Getter
    private Long idUser;

    public Collection() {
    }

    public Collection(String name,boolean isInTrash, Long idParentCollection, Long idUser) {
        this();
        this.title = name;
        this.isInTrash = isInTrash;
        this.idParentCollection = idParentCollection;
        this.idUser = idUser;
    }

    public Collection(CreateCollectionCommand command) {
        this();
        this.title = command.title();
        this.isInTrash = command.isInTrash();
        this.idParentCollection = command.idParentCollection();
        this.idUser = command.idUser();
    }


    public void updateDetails(String name,  boolean isPublic) {
        this.title = name;
        this.isInTrash = isPublic;
    }

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wish> wishes = new ArrayList<>();

    public void addWish(Wish wish) {
        wish.setCollection(this);
        this.wishes.add(wish);
    }

    @ElementCollection
    @CollectionTable(name = "collection_tags", joinColumns = @JoinColumn(name = "collection_id"))
    private Set<Tag> tags = new HashSet<>();

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }


}
