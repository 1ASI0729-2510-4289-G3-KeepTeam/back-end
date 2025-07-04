package com.acme.keeplo.platform.wishlist.interfaces.rest.transform;

import com.acme.keeplo.platform.wishlist.domain.model.valueobjects.Tag;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.TagResource;


public class TagResourceFromEntityAssembler {
    public static TagResource toResourceFromEntity(Tag entity) {
        return new TagResource(entity.getName(), entity.getColor());
    }
}