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
public class ComponentAnalysisResponseDTO {
    private UUID id;
    private UUID analysisId;
    private UUID componentId;
    private LocalDateTime createdAt;
}