package com.estimplytics.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestUpdateDTO {
    @Schema(description = "Request title", example = "Implement social login")
    private String title;
    @Schema(description = "Detailed description", example = "Allow access via Google and GitHub")
    private String description;
    @Schema(description = "Request status", example = "IN_PROGRESS")
    private String status;
}
