package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;

import java.util.List;
/**
 * Resource object representing the request payload for adding tags to a wish.
 *
 * @param tags A list of {@link TagResource} objects to be assigned to the wish.
 */
public record AddTagsToWishResource(List<TagResource> tags) {}