package com.acme.keeplo.platform.wishlist.interfaces.rest.transform;
import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateTagToWishCommand;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.AddTagToWishResource;

public class AddTagToCollectionCommandFromResourceAssembler {
    public static CreateTagToWishCommand toCommand(Long collectionId, AddTagToWishResource resource) {
        return new CreateTagToWishCommand(collectionId, resource.name(), resource.color());
    }
}
