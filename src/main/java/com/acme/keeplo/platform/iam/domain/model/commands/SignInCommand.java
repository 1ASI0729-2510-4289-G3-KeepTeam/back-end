package com.acme.keeplo.platform.iam.domain.model.commands;

/**
 * Sign-in command (SignInCommand).
 * This command encapsulates the credentials a user provides to authenticate with the system.
 *
 * @param email The username of the user attempting to sign in (in our case, the email).
 * @param password The password provided by the user.
 */

public record SignInCommand(String email, String password) {
    public SignInCommand {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
    }
}