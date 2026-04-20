package com.estimplytics.backend.dto.redmine;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCredentialCmd(
        @NotNull
        @Schema(description = "Redmine instance identifier", example = "1")
        Long redmineInstanceId,

        @NotBlank
        @Schema(description = "Plain Redmine API key to encrypt and store", example = "your-redmine-api-key")
        String plainApiKey
) {}
