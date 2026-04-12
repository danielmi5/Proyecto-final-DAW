package com.estimplytics.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiErrorDTO {

    @Schema(description = "Fecha y hora del error", example = "2026-05-08T08:00:00")
    private LocalDateTime timestamp;

    @Schema(description = "Código numérico del estado HTTP", example = "400")
    private int stateNum;

    @Schema(description = "Nombre del error", example = "Bad Request")
    private String error;

    @Schema(description = "Mensaje descriptivo", example = "Validación fallida")
    private String message;

    @Schema(description = "Descripción de la petición", example = "uri=/api/users")
    private String description;

    @Schema(description = "Ruta que produjo el error", example = "/api/users")
    private String path;
}
