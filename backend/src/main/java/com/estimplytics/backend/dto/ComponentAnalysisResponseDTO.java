package com.estimplytics.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComponentAnalysisResponseDTO {
    @Schema(description = "Unique identifier", example = "550e8400-e29b-41d4-a716-446655440222")
    private UUID id;
    @Schema(description = "Analysis identifier", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID analysisId;
    @Schema(description = "Component identifier", example = "550e8400-e29b-41d4-a716-446655440111")
    private UUID componentId;
    @Schema(description = "Creation date and time", example = "2026-05-08T08:00:00")
    private LocalDateTime createdAt;
}