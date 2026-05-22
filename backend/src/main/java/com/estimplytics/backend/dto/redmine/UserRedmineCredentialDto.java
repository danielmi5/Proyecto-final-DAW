package com.estimplytics.backend.dto.redmine;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record UserRedmineCredentialDto(
        @Schema(description = "Credential unique identifier", example = "1")
        Long id,

        @Schema(description = "Associated Redmine instance identifier", example = "1")
        Long redmineInstanceId,

        @Schema(description = "Redmine instance display name", example = "Production Redmine")
        String instanceName,

        @Schema(description = "Masked API key", example = "********")
        String apiKeyMasked,

        @Schema(description = "Last successful synchronization timestamp", example = "2026-05-22T10:30:00")
        LocalDateTime lastSyncAt
) {}
