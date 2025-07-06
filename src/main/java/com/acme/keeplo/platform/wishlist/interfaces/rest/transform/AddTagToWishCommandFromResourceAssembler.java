package com.acme.keeplo.platform.wishlist.interfaces.rest.transform;

import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateTagToWishCommand;
import com.acme.keeplo.platform.wishlist.domain.model.valueobjects.Tag;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.TagResource;


import java.util.List;

public class AddTagToWishCommandFromResourceAssembler {

    public static CreateTagToWishCommand toCommand(Long wishId, List<TagResource> tagResources) {
        List<Tag> tags = tagResources.stream()
                .map(tag -> new Tag(tag.name(), tag.color()))
                .toList();

        return new CreateTagToWishCommand(wishId, tags);
    }
}