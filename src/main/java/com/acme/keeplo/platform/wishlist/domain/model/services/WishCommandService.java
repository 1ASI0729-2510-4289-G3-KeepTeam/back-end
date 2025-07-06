package com.acme.keeplo.platform.wishlist.domain.model.services;

import com.acme.keeplo.platform.wishlist.domain.model.commands.*;
import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;

import java.util.Optional;

public interface WishCommandService {
    Optional<Wish> handle(CreateWishCommand command);
    boolean handle(DeleteWishCommand command);
    boolean handle(CreateTagToWishCommand command);
    boolean handle(DeleteTagsOfWishCommand command);
    Optional<Wish> handle(UpdateWishCommand command);
}