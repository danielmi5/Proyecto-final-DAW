package com.estimplytics.backend.dto;

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
    private UUID id;
    private UUID analysisId;
    private Integer versionNumber;
    private Integer fiability;
    private Integer hoursAn;
    private Integer hoursAs;
    private Integer hoursDe;
    private Integer totalHours;
    private Integer actualHoursFeedback;
    private String justification;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}