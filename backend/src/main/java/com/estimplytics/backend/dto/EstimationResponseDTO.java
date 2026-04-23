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
    @Schema(description = "Estimation unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;
    @Schema(description = "Associated analysis identifier", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID analysisId;
    @Schema(description = "Version number", example = "1")
    private Integer versionNumber;
    @Schema(description = "Confidence percentage", example = "85")
    private Integer fiability;
    @Schema(description = "Estimated hours for analysis", example = "10")
    private Integer hoursAn;
    @Schema(description = "Estimated hours for development", example = "20")
    private Integer hoursAs;
    @Schema(description = "Estimated hours for deployment", example = "5")
    private Integer hoursDe;
    @Schema(description = "Total hours", example = "35")
    private Integer totalHours;
    @Schema(description = "Actual hours feedback", example = "32")
    private Integer actualHoursFeedback;
    @Schema(description = "Justification for the estimation", example = "Existing infrastructure reuse")
    private String justification;
    @Schema(description = "Creation date", example = "2026-05-08T08:00:00")
    private LocalDateTime createdAt;
    @Schema(description = "Update date", example = "2026-05-08T09:00:00")
    private LocalDateTime updatedAt;
}