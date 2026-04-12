package com.estimplytics.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestRequestDTO {
    @Schema(description = "Identificador Redmine de la petición", example = "12345")
    private Integer redmineId;
    @Schema(description = "Código de la petición de origen", example = "REQ-001")
    private String originRequestCode;

    @NotBlank
    @Schema(description = "Nombre del proyecto", example = "Portal Web")
    private String projectName;

    @Schema(description = "Tipo de demanda", example = "FEATURE")
    private String demandType;

    @NotBlank
    @Schema(description = "Título de la petición", example = "Implementar login social")
    private String title;

    @Schema(description = "Descripción detallada", example = "Permitir acceso mediante Google y GitHub")
    private String description;

    @NotBlank
    @Schema(description = "Estado de la petición", example = "OPEN")
    private String status;

    @NotBlank
    @Schema(description = "Prioridad de la petición", example = "HIGH")
    private String priority;

    @Schema(description = "Nombre del responsable asignado", example = "Daniel")
    private String assigneeName;
    @Schema(description = "Fecha de inicio planificada", example = "2026-05-10")
    private LocalDate startDate;
    @Schema(description = "Fecha de fin planificada", example = "2026-05-20")
    private LocalDate endDate;

    @NotNull
    @Schema(description = "Fecha y hora de creación", example = "2026-05-08T08:00:00")
    private LocalDateTime createdDate;
}
