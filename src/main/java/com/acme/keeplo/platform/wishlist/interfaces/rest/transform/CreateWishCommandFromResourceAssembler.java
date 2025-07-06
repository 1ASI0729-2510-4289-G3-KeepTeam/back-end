package com.acme.keeplo.platform.wishlist.interfaces.rest.transform;

import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateWishCommand;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.CreateWishResource;


public class CreateWishCommandFromResourceAssembler {



    public static CreateWishCommand toCommandFromResource(CreateWishResource resource) {

        return new CreateWishCommand(
                resource.title(),
                resource.redirectUrl(),
                resource.description(),
                resource.urlImg(),
                resource.collectionId(),
                resource.isInTrash()
        );
    }
}
