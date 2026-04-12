package com.estimplytics.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestUpdateDTO {
    @Schema(description = "Título de la petición", example = "Implementar login social")
    private String title;
    @Schema(description = "Descripción detallada", example = "Permitir acceso mediante Google y GitHub")
    private String description;
    @Schema(description = "Estado de la petición", example = "IN_PROGRESS")
    private String status;
}
