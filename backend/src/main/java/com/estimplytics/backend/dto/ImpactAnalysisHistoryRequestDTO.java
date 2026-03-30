package com.estimplytics.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImpactAnalysisHistoryRequestDTO {
    private UUID analysisId;
    private UUID requestId;
    private UUID analystId;
    private String version;
}
