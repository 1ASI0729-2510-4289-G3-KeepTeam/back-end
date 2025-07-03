package com.acme.keeplo.platform.iam.interfaces.rest.resources;

public record ChangePasswordResource(String currentPassword, String newPassword) {}