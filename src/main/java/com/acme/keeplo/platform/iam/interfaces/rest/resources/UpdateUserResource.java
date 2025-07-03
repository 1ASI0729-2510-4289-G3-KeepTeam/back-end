package com.acme.keeplo.platform.iam.interfaces.rest.resources;
import com.acme.keeplo.platform.iam.domain.model.aggregates.User;

/**
 * Resource representing the editable fields of a user.
 * Used for update requests.
 */
public record UpdateUserResource(
        String name,
        String email,
        String profilePicture
)
{}