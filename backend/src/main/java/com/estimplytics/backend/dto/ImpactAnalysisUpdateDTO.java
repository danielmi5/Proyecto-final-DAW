package com.estimplytics.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Identifier of the base request", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID requestId;
    @Schema(description = "Identifier of the user", example = "550e8400-e29b-41d4-a716-446655440111")
    private UUID userId;

    @NotNull
    @Schema(description = "Version number", example = "2")
    private Integer versionNumber;

    @NotBlank
    @Schema(description = "Complexity level", example = "MEDIUM")
    private String complexity;

    @NotNull
    @Schema(description = "Analysis document data", example = "{\"title\":\"Updated analysis\",\"notes\":\"Dependencies adjusted\"}")
    private Map<String, Object> documentData;
}