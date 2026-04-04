package com.estimplytics.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImpactAnalysisUpdateDTO {
    private UUID requestId;
    private UUID userId;

    @NotNull
    private Integer versionNumber;

    @NotBlank
    private String complexity;

    @NotNull
    private Map<String, Object> documentData;
}