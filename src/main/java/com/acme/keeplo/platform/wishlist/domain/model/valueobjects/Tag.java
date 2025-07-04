package com.acme.keeplo.platform.wishlist.domain.model.valueobjects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@Getter
@Setter
public class Tag {

    private String name;
    private String color;

    public Tag() {}
    public Tag(String name, String color) {
        this.name = name;
        this.color = color;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag tag)) return false;
        return Objects.equals(name, tag.name) &&
                Objects.equals(color, tag.color);
    }
}
