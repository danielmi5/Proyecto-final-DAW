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
public class EstimationHistoryResponseDTO {
    @Schema(description = "Identificador del historial", example = "1")
    private Long id;
    @Schema(description = "Identificador de la estimación", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID estimationId;
    @Schema(description = "Versión congelada", example = "1")
    private Integer frozenVersion;
    @Schema(description = "Datos históricos de la estimación", example = "{\"hoursAn\":10,\"hoursAs\":20}")
    private Map<String, Object> snapshotData;
    @Schema(description = "Fecha y hora de modificación", example = "2026-05-08T08:30:00")
    private LocalDateTime modifiedAt;
}
