package com.estimplytics.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstimationHistoryUpdateDTO {
    @NotNull
    @Schema(description = "Versión congelada", example = "2")
    private Integer frozenVersion;

    @NotNull
    @Schema(description = "Datos históricos de la estimación", example = "{\"hoursAn\":8,\"hoursAs\":18}")
    private Map<String, Object> snapshotData;

    @NotNull
    @Schema(description = "Fecha y hora de modificación", example = "2026-05-08T09:00:00")
    private LocalDateTime modifiedAt;
}
