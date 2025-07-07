package com.acme.keeplo.platform.wishlist.domain.model.entities;
import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.shared.domain.model.entities.AuditableModel;
import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateWishCommand;
import com.acme.keeplo.platform.wishlist.domain.model.valueobjects.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
/**
 * Entity representing a wish, which belongs to a collection.
 * A wish may include tags, a redirect URL, description, image, and trash status.
 */
@Entity
@Getter
public class Wish extends AuditableModel {
    /**
     * The title of the wish.
     */
    private String title;

    /**
     * A brief description of the wish.
     */
    @Setter
    private String description;
    /**
     * The URL to redirect to when the wish is selected.
     */
    @Setter
    private String redirectUrl;
    /**
     * Indicates whether the wish is in the trash.
     */
    @Setter
    private Boolean isInTrash;
    /**
     * The URL of the image representing the wish.
     */
    @Setter
    private String urlImg;
    /**
     * The collection this wish belongs to.
     */
    @JsonIgnore
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;
    /**
     * List of tags associated with this wish.
     */
    @Setter
    @ElementCollection
    @CollectionTable(name = "wish_tags", joinColumns = @JoinColumn(name = "wish_id"))
    private List<Tag> tags = new ArrayList<>();


    protected Wish() {
    }
    /**
     * Constructs a new wish with the specified data.
     *
     * @param title        The title of the wish.
     * @param description  The description of the wish.
     * @param redirectUrl  The URL to redirect when the wish is selected.
     * @param collection   The parent collection of this wish.
     * @param isInTrash    Whether the wish is marked as trash.
     * @param urlImg       The image URL of the wish.
     */
    public Wish(String title, String description, String redirectUrl, Collection collection, Boolean isInTrash, String urlImg) {
        this.title = title;
        this.description = description;
        this.redirectUrl = redirectUrl;
        this.collection = collection;
        this.isInTrash =  isInTrash;
        this.urlImg = urlImg;
    }
    /**
     * Updates the wish with new information.
     *
     * @param title        The new title.
     * @param redirectUrl  The new redirect URL.
     * @param description  The new description.
     * @param urlImg       The new image URL.
     * @param isInTrash    The new trash status.
     */
    public void update(String title, String redirectUrl, String description, String urlImg, Boolean isInTrash) {
        this.title = title;
        this.redirectUrl = redirectUrl;
        this.description = description;
        this.urlImg = urlImg;
        this.isInTrash = isInTrash;
    }
    /**
     * Gets the ID of the collection this wish belongs to.
     *
     * @return The collection ID, or {@code null} if not assigned.
     */
    public Long getIdCollection() {
        return collection != null ? collection.getId() : null;
    }


}