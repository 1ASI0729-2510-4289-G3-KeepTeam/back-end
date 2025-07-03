package com.acme.keeplo.platform.iam.domain.model.commands;

/**
 * Command for updating user details.
 * This command is used to encapsulate the data required to update a user's information.
 *
 * @param id the unique identifier of the user
 * @param name the new name of the user
 * @param email the new email address of the user
 * @param profilePicture the URL or path to the user's new profile picture
 */
public record UpdateUserCommand(
        Long id,
        String name,
        String email,
        String profilePicture
) {}
