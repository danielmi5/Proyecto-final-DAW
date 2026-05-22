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
public class ComponentResponseDTO {
    @Schema(description = "Component unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;
    @Schema(description = "Component name", example = "API Gateway")
    private String name;
    @Schema(description = "Component category", example = "INFRASTRUCTURE")
    private String category;
    @Schema(description = "Indicates whether the component is active", example = "true")
    private Boolean active;
    @Schema(description = "Component creation date and time", example = "2026-05-08T08:00:00")
    private LocalDateTime createdAt;
}