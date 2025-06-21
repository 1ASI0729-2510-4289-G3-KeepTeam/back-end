package com.acme.keeplo.platform.wishlist.domain.model.aggregates;

import com.acme.keeplo.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
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
    private String name;

    @Getter
    private String description;

    @Getter
    private boolean isPublic;

    public Collection() {
    }

    public Collection(String name, String description, boolean isPublic) {
        this();
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
    }

    public void updateDetails(String name, String description, boolean isPublic) {
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
    }

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wish> wishes = new ArrayList<>();

    public void addWish(Wish wish) {
        wish.setCollection(this);
        this.wishes.add(wish);
    }

    @ElementCollection
    @CollectionTable(name = "wish_tags", joinColumns = @JoinColumn(name = "wish_id"))
    private Set<Tag> tags = new HashSet<>();

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }


}
