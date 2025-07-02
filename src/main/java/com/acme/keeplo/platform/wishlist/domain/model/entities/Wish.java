package com.acme.keeplo.platform.wishlist.domain.model.entities;
import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.shared.domain.model.entities.AuditableModel;
import com.acme.keeplo.platform.wishlist.domain.model.valueobjects.Tag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
public class Wish extends AuditableModel {

    private String title;
    @Setter
    private String description;
    @Setter
    private String redirectUrl;
    @Setter
    private Boolean isInTrash;
    @Setter
    private String urlImg;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;
    @ElementCollection
    @CollectionTable(name = "wish_tags", joinColumns = @JoinColumn(name = "wish_id"))
    private List<Tag> tags = new ArrayList<>();
    

    protected Wish() {
    }

    public Wish(String title, String description, String redirectUrl, Collection collection, Boolean isInTrash, String urlImg) {
        this.title = title;
        this.description = description;
        this.redirectUrl = redirectUrl;
        this.collection = collection;
        this.isInTrash =  isInTrash;
        this.urlImg = urlImg;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }



}