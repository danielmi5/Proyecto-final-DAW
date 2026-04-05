package com.estimplytics.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstimationAlgorithmResultDTO {
    private Integer suggestedTotalHours;
    private Integer fiabilityPercentage;
}
