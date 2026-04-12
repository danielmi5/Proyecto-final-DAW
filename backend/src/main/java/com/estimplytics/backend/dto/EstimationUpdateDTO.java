package com.estimplytics.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstimationUpdateDTO {
    @NotNull
    @Schema(description = "Número de versión", example = "2")
    private Integer versionNumber;

    @Schema(description = "Porcentaje de fiabilidad", example = "90")
    private Integer fiability;
    @Schema(description = "Horas estimadas para análisis", example = "8")
    private Integer hoursAn;
    @Schema(description = "Horas estimadas para desarrollo", example = "18")
    private Integer hoursAs;
    @Schema(description = "Horas estimadas para despliegue", example = "4")
    private Integer hoursDe;
    @Schema(description = "Horas totales", example = "30")
    private Integer totalHours;
    @Schema(description = "Feedback de horas reales", example = "29")
    private Integer actualHoursFeedback;
    @Schema(description = "Justificación de la estimación", example = "Ajuste tras revisión técnica")
    private String justification;

    @NotNull
    @Schema(description = "Fecha y hora de actualización", example = "2026-05-08T09:00:00")
    private LocalDateTime updatedAt;
}