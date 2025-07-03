package com.acme.keeplo.platform.iam.application.internal.commandservices;

import com.acme.keeplo.platform.iam.domain.model.aggregates.User;
import com.acme.keeplo.platform.iam.domain.model.commands.SignInCommand;
import com.acme.keeplo.platform.iam.domain.model.commands.SignUpCommand;
import com.acme.keeplo.platform.iam.domain.model.commands.UpdateUserCommand;
import com.acme.keeplo.platform.iam.domain.model.entities.Role;
import com.acme.keeplo.platform.iam.domain.model.valueobjects.Roles;
import com.acme.keeplo.platform.iam.domain.services.UserCommandService;
import com.acme.keeplo.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.acme.keeplo.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.acme.keeplo.platform.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import com.acme.keeplo.platform.iam.infrastructure.tokens.jwt.BearerTokenService;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service that handles user-related commands such as sign-in and sign-up.
 * This implementation manages user authentication and registration logic.
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptHashingService hashingService;
    private final BearerTokenService tokenService;

    /**
     * Creates an instance of UserCommandServiceImpl with required dependencies.
     *
     * @param userRepository repository for user data
     * @param roleRepository repository for role data
     * @param hashingService service for password hashing
     * @param tokenService service for generating JWT tokens
     */
    public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptHashingService hashingService, BearerTokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
    }
    /**
     * Handles the sign-in process by verifying credentials and generating a JWT token.
     *
     * @param command contains the username and password for sign-in
     * @return an optional containing the authenticated user and token if successful
     */
    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.username());
        if (user.isEmpty()) {
            return Optional.empty();
        }

        if (!hashingService.matches(command.password(), user.get().getPassword())) {
            return Optional.empty();
        }


        var token = tokenService.generateToken(user.get().getEmail(), user.get().getRoles());

        return Optional.of(ImmutablePair.of(user.get(), token));
    }
    /**
     * Handles user registration by creating a new user and assigning the default role.
     *
     * @param command contains the user's registration details
     * @return the newly created user if registration is successful
     */
    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException(String.format("User with email %s already exists", command.email()));
        }

        var hashedPassword = hashingService.encode(command.password());

        var defaultRole = roleRepository.findByName(Roles.ROLE_USER)
                .orElseThrow(() -> new IllegalStateException("Default role ROLE_USER not found. Please seed roles."));

        var user = new User(command.email(), hashedPassword, command.name(), command.profilePicture());
        user.addRole(defaultRole);

        var createdUser = userRepository.save(user);

        return Optional.of(createdUser);
    }

    @Override
    public User updateUser(UpdateUserCommand command) {
        User user = userRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException(String.format("User", command.id())));

        // Aquí actualizas los campos
        user.setName(command.name());
        user.setEmail(command.email());
        user.setProfilePicture(command.profilePicture());

        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User", id)));
        userRepository.delete(user);
    }
}