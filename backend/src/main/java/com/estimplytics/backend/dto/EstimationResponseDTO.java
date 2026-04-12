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
public class EstimationResponseDTO {
    @Schema(description = "Identificador único de la estimación", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;
    @Schema(description = "Identificador del análisis asociado", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID analysisId;
    @Schema(description = "Número de versión", example = "1")
    private Integer versionNumber;
    @Schema(description = "Porcentaje de fiabilidad", example = "85")
    private Integer fiability;
    @Schema(description = "Horas estimadas para análisis", example = "10")
    private Integer hoursAn;
    @Schema(description = "Horas estimadas para desarrollo", example = "20")
    private Integer hoursAs;
    @Schema(description = "Horas estimadas para despliegue", example = "5")
    private Integer hoursDe;
    @Schema(description = "Horas totales", example = "35")
    private Integer totalHours;
    @Schema(description = "Feedback de horas reales", example = "32")
    private Integer actualHoursFeedback;
    @Schema(description = "Justificación de la estimación", example = "Se reutiliza infraestructura existente")
    private String justification;
    @Schema(description = "Fecha de creación", example = "2026-05-08T08:00:00")
    private LocalDateTime createdAt;
    @Schema(description = "Fecha de actualización", example = "2026-05-08T09:00:00")
    private LocalDateTime updatedAt;
}