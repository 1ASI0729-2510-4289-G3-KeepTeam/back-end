package com.acme.keeplo.platform.wishlist.interfaces.rest.transform;
import com.acme.keeplo.platform.wishlist.domain.model.commands.AddTagToWishCommand;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.AddTagToCollectionResource;

public class AddTagToCollectionCommandFromResourceAssembler {
    public static AddTagToWishCommand toCommand(Long collectionId, AddTagToCollectionResource resource) {
        return new AddTagToWishCommand(collectionId, resource.name(), resource.color());
    }
}
