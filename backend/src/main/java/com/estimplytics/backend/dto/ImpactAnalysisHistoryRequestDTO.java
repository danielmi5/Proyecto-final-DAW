package com.estimplytics.backend.dto;

import jakarta.validation.constraints.NotNull;
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
public class ImpactAnalysisHistoryRequestDTO {
    @NotNull
    private UUID analysisId;

    @NotNull
    private Integer frozenVersion;

    @NotNull
    private Map<String, Object> snapshotData;

    @NotNull
    private Map<String, Object> componentsSnapshot;

    @NotNull
    private LocalDateTime modifiedAt;
}
