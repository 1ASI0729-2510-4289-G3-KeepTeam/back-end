package com.acme.keeplo.platform.wishlist.interfaces.rest.transform;

import com.acme.keeplo.platform.wishlist.domain.model.commands.UpdateCollectionCommand;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.UpdateCollectionResource;

public class UpdateCollectionCommandFromResourceAssembler {

    public static UpdateCollectionCommand toCommandFromResource(Long collectionId, UpdateCollectionResource resource) {
        return new UpdateCollectionCommand(
                collectionId,
                resource.title(),
                resource.isInTrash(),
                resource.idParentCollection()
        );
    }
}
