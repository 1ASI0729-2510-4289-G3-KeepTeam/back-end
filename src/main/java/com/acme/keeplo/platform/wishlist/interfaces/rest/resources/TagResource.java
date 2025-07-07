package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;
/**
 * Resource object representing a tag.
 * A tag consists of a name and a color and is used to categorize wishes or collections.
 *
 * @param name  The name of the tag (e.g., "Travel", "Books").
 * @param color The color code or label associated with the tag (e.g., "#FF5733").
 */

public record TagResource(String name, String color) {}