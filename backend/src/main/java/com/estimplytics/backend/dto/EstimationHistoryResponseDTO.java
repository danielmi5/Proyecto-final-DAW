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
public class EstimationHistoryResponseDTO {
    private Long id;
    private UUID estimationId;
    private UUID analysisId;
    private Integer totalHours;
    private String version;
    private LocalDateTime createdAt;
}
