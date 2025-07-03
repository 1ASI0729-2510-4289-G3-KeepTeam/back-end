package com.acme.keeplo.platform.wishlist.interfaces.rest.transform;

import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateCollectionCommand;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.CreateCollectionResource;

/**
 * Transforma un recurso REST a un comando del dominio.
 */
public class CreateCollectionCommandFromResourceAssembler {

    public static CreateCollectionCommand toCommandFromResource(CreateCollectionResource resource) {
        return new CreateCollectionCommand(
                resource.title(),
                resource.isInTrash(),
                resource.idUser(),
                resource.idParentCollection()
        );
    }
}
