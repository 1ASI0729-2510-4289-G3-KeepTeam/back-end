package com.acme.keeplo.platform.wishlist.interfaces.rest.transform;

import com.acme.keeplo.platform.wishlist.domain.model.valueobjects.Tag;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.TagResource;
/**
 * Assembler class responsible for converting a {@link Tag} domain entity
 * into a {@link TagResource} for API responses.
 */

public class TagResourceFromEntityAssembler {

    /**
     * Transforms a {@link Tag} domain object into a {@link TagResource}.
     *
     * @param entity The tag domain object.
     * @return The resource representation of the tag for external use.
     */
    public static TagResource toResourceFromEntity(Tag entity) {
        return new TagResource(entity.getName(), entity.getColor());
    }
}