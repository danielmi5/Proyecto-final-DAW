package com.estimplytics.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImpactAnalysisRequestDTO {
    @Schema(description = "Identificador de la petición base", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID requestId;
    @Schema(description = "Identificador del usuario", example = "550e8400-e29b-41d4-a716-446655440111")
    private UUID userId;

    @NotNull
    @Schema(description = "Número de versión", example = "1")
    private Integer versionNumber;

    @NotBlank
    @Schema(description = "Nivel de complejidad", example = "HIGH")
    private String complexity;

    @NotNull
    @Schema(description = "Datos del documento de análisis", example = "{\"title\":\"Análisis base\",\"notes\":\"Incluye dependencias\"}")
    private Map<String, Object> documentData;
}