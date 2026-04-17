package com.estimplytics.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestResponseDTO {
    @Schema(description = "Identificador único de la petición", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;
    @Schema(description = "Identificador Redmine de la petición", example = "12345")
    private Integer redmineId;
    @Schema(description = "Código de la petición de redmine", example = "REQ-001")
    private String originRequestCode;
    @Schema(description = "Nombre del proyecto", example = "Portal Web")
    private String projectName;
    @Schema(description = "Tipo de demanda", example = "FEATURE")
    private String demandType;
    @Schema(description = "Título de la petición", example = "Implementar login social")
    private String title;
    @Schema(description = "Descripción detallada", example = "Permitir acceso mediante Google y GitHub")
    private String description;
    @Schema(description = "Estado de la petición", example = "OPEN")
    private String status;
    @Schema(description = "Prioridad de la petición", example = "HIGH")
    private String priority;
    @Schema(description = "Nombre del responsable asignado", example = "Daniel")
    private String assigneeName;
    @Schema(description = "Nombre del autor", example = "Daniel")
    private String authorName;
    @Schema(description = "Fecha de inicio planificada", example = "2026-05-10")
    private LocalDate startDate;
    @Schema(description = "Fecha de fin planificada", example = "2026-05-20")
    private LocalDate endDate;
    @Schema(description = "Fecha de creación en el sistema de redmine", example = "2026-05-08T08:00:00")
    private LocalDateTime redmineCreatedDate;
    @Schema(description = "Fecha de última actualización en el sistema de redmine", example = "2026-05-09T10:00:00")
    private LocalDateTime redmineUpdatedDate;
    @Schema(description = "Fecha de cierre en el sistema de redmine", example = "2026-05-15T18:00:00")
    private LocalDateTime redmineClosedDate;
    @Schema(description = "Porcentaje de avance (0-100)", example = "50")
    private Integer doneRatio;
    @Schema(description = "Horas estimadas", example = "40.5")
    private Double estimatedHours;
    @Schema(description = "Horas imputadas/gastadas", example = "10.0")
    private Double spentHours;

    @Schema(description = "Fecha y hora de creación", example = "2026-05-08T08:00:00")
    private LocalDateTime createdDate;
}
