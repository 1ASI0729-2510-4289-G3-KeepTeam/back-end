package com.acme.keeplo.platform.wishlist.domain.model.services;

import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateTagToWishCommand;
import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateWishCommand;
import com.acme.keeplo.platform.wishlist.domain.model.commands.UpdateWishCommand;
import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;

import java.util.Optional;

public interface WishCommandService {
    Optional<Wish> handle(CreateWishCommand command);
    boolean deleteById(Long id);
    boolean addTagToWish(CreateTagToWishCommand command);
    Optional<Wish> handle(UpdateWishCommand command);
}