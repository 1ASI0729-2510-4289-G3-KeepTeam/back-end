package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateWishCommand;
import com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories.CollectionRepository;


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
