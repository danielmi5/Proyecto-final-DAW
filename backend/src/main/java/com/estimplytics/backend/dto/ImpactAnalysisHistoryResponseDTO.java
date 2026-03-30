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
public class ImpactAnalysisHistoryResponseDTO {
    private Long id;
    private UUID analysisId;
    private UUID requestId;
    private UUID analystId;
    private String version;
    private LocalDateTime createdAt;
}
