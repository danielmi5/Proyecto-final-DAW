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
    @Schema(description = "Identificador único del análisis de impacto", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;
    @Schema(description = "Identificador de la petición base", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID requestId;
    @Schema(description = "Identificador del usuario", example = "550e8400-e29b-41d4-a716-446655440111")
    private UUID userId;
    @Schema(description = "Número de versión", example = "1")
    private Integer versionNumber;
    @Schema(description = "Nivel de complejidad", example = "HIGH")
    private String complexity;
    @Schema(description = "Datos del documento de análisis", example = "{\"title\":\"Análisis base\",\"notes\":\"Incluye dependencias\"}")
    private Map<String, Object> documentData;
    @Schema(description = "Fecha de actualización", example = "2026-05-08T09:00:00")
    private LocalDateTime updatedAt;
}