package com.acme.keeplo.platform.iam.domain.services;

import com.acme.keeplo.platform.iam.domain.model.aggregates.User;
import com.acme.keeplo.platform.iam.domain.model.commands.SignInCommand;
import com.acme.keeplo.platform.iam.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

/**
 * Command service interface for user management within the IAM module.
 * Defines operations related to modifying the state (creation, authentication) of users.
 */
public interface UserCommandService {
    /**
     * Handles the user sign-in command.
     * @param command The {@link SignInCommand} containing the sign-in credentials.
     * @return An {@link Optional} containing an {@link ImmutablePair} of the authenticated {@link User}
     * and a {@link String} (which will typically be the JWT token), or empty if authentication fails.
     */
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);

    /**
     * Handles the sign-up command for a new user.
     * @param command The {@link SignUpCommand} containing the user registration data.
     * @return An {@link Optional} containing the created {@link User} entity if the operation was successful, or empty if it failed (e.g., email already exists).
     */
    Optional<User> handle(SignUpCommand command);
}