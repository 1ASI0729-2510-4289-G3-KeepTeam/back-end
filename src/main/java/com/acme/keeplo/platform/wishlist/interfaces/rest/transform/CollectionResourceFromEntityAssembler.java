package com.acme.keeplo.platform.wishlist.interfaces.rest.transform;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.CollectionResource;

/**
 * Transforma un recurso REST a un comando del dominio.
 */
/**
 * Transforma una entidad del dominio a un recurso REST.
 */
public class CollectionResourceFromEntityAssembler {

    public static CollectionResource toResourceFromEntity(Collection entity) {
        return new CollectionResource(
                entity.getId(),
                entity.getTitle(),
                entity.isInTrash(),
                entity.getIdUser(),
                entity.getIdParentCollection()
        );
    }
}
//String id,
//        String title,
//        String isInTrash,
//        boolean idUser