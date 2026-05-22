package com.estimplytics.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImpactAnalysisResponseDTO {
    @Schema(description = "Impact analysis unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;
    @Schema(description = "Base request identifier", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID requestId;
    @Schema(description = "User identifier", example = "550e8400-e29b-41d4-a716-446655440111")
    private UUID userId;
    @Schema(description = "Version number", example = "1")
    private Integer versionNumber;
    @Schema(description = "Complexity level", example = "HIGH")
    private String complexity;
    @Schema(description = "Analysis document data", example = "{\"title\":\"Base analysis\",\"notes\":\"Includes dependencies\"}")
    private Map<String, Object> documentData;
    @Schema(description = "Update date", example = "2026-05-08T09:00:00")
    private LocalDateTime updatedAt;
}