package com.acme.keeplo.platform.iam.domain.model.commands;

public record ChangePasswordCommand(Long userId, String currentPassword, String newPassword) {}
