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
public class EstimationAlgorithmResultDTO {
    @Schema(description = "Horas totales sugeridas por el algoritmo", example = "35")
    private Integer suggestedTotalHours;
    @Schema(description = "Porcentaje de fiabilidad sugerido", example = "85")
    private Integer fiabilityPercentage;
}
