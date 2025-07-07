package com.acme.keeplo.platform.wishlist.interfaces.rest.transform;

import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateTagToWishCommand;
import com.acme.keeplo.platform.wishlist.domain.model.valueobjects.Tag;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.TagResource;


import java.util.List;
/**
 * Assembler class responsible for transforming a list of {@link TagResource} objects
 * into a {@link CreateTagToWishCommand} to be processed by the application layer.
 */
public class AddTagToWishCommandFromResourceAssembler {
    /**
     * Converts a list of {@link TagResource} and a target wish ID into a {@link CreateTagToWishCommand}.
     *
     * @param wishId       The ID of the wish to which the tags will be added.
     * @param tagResources The list of tag resources received in the request.
     * @return A {@link CreateTagToWishCommand} containing the mapped tags and wish ID.
     */

    public static CreateTagToWishCommand toCommand(Long wishId, List<TagResource> tagResources) {
        List<Tag> tags = tagResources.stream()
                .map(tag -> new Tag(tag.name(), tag.color()))
                .toList();

        return new CreateTagToWishCommand(wishId, tags);
    }
}