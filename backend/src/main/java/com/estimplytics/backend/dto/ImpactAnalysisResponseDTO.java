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
public class ImpactAnalysisResponseDTO {
    private UUID id;
    private UUID requestId;
    private UUID userId;
    private Integer versionNumber;
    private String complexity;
    private Map<String, Object> documentData;
    private LocalDateTime updatedAt;
}