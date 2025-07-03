package com.acme.keeplo.platform.wishlist.interfaces.rest.transform;

import com.acme.keeplo.platform.wishlist.domain.model.commands.UpdateWishCommand;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.UpdateWishResource;

public class UpdateWishCommandFromResourceAssembler {

    public static UpdateWishCommand toCommandFromResource(Long wishId, UpdateWishResource resource) {
        return new UpdateWishCommand(
                wishId,
                resource.title(),
                resource.redirectUrl(),
                resource.description(),
                resource.urlImg(),
                resource.isInTrash()
        );
    }
}