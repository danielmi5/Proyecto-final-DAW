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

    @Schema(description = "Error timestamp", example = "2026-05-08T08:00:00")
    private LocalDateTime timestamp;

    @Schema(description = "Numeric HTTP status code", example = "400")
    private int stateNum;

    @Schema(description = "Error name", example = "Bad Request")
    private String error;

    @Schema(description = "Descriptive message", example = "Validation failed")
    private String message;

    @Schema(description = "Request description", example = "uri=/api/users")
    private String description;

    @Schema(description = "Path that produced the error", example = "/api/users")
    private String path;
}
