package com.acme.keeplo.platform.wishlist.domain.model.valueobjects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
/**
 * Value object representing a tag that can be assigned to a wish or collection.
 * A tag consists of a name and a color for identification and categorization.
 */
@Embeddable
@Getter
@Setter
public class Tag {
    /**
     * The name of the tag.
     */
    private String name;
    /**
     * The color associated with the tag.
     */
    private String color;
    /**
     * Default constructor required by JPA.
     */

    public Tag() {}
    /**
     * Constructs a new Tag with the specified name and color.
     *
     * @param name  The name of the tag.
     * @param color The color of the tag.
     */
    public Tag(String name, String color) {
        this.name = name;
        this.color = color;
    }

    /**
     * Compares this tag with another object for equality.
     *
     * @param o The object to compare with.
     * @return {@code true} if both tags have the same name and color, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag tag)) return false;
        return Objects.equals(name, tag.name) &&
                Objects.equals(color, tag.color);
    }
}
