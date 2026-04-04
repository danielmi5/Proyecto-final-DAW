package com.estimplytics.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImpactAnalysisHistoryResponseDTO {
    private Long id;
    private UUID analysisId;
    private Integer frozenVersion;
    private Map<String, Object> snapshotData;
    private Map<String, Object> componentsSnapshot;
    private LocalDateTime modifiedAt;
}
