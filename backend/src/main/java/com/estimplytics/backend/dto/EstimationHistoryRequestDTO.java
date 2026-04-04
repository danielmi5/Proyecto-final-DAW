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
public class EstimationHistoryRequestDTO {
    @NotNull
    private UUID estimationId;

    @NotNull
    private Integer frozenVersion;

    @NotNull
    private Map<String, Object> snapshotData;

    @NotNull
    private LocalDateTime modifiedAt;
}
