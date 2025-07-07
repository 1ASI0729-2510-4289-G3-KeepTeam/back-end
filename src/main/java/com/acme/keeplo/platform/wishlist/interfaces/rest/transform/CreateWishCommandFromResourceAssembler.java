package com.acme.keeplo.platform.wishlist.interfaces.rest.transform;

import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateWishCommand;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.CreateWishResource;

/**
 * Assembler class responsible for transforming a {@link CreateWishResource}
 * into a {@link CreateWishCommand} that can be processed by the application layer.
 */
public class CreateWishCommandFromResourceAssembler {


    /**
     * Converts a {@link CreateWishResource} into a {@link CreateWishCommand}.
     *
     * @param resource The resource object received from the API request body.
     * @return A domain command representing the intention to create a new wish.
     */

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
