package com.acme.keeplo.platform.iam.domain.model.commands;
/**
 * Command to request a password change for a specific user.
 *
 * @param userId          The ID of the user requesting the password change.
 * @param currentPassword The user's current password.
 * @param newPassword     The new password to be set.
 */
public record ChangePasswordCommand(Long userId, String currentPassword, String newPassword) {}
