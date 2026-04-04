package com.estimplytics.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestResponseDTO {
    private UUID id;
    private Integer redmineId;
    private String originRequestCode;
    private String projectName;
    private String demandType;
    private String title;
    private String description;
    private String status;
    private String priority;
    private String assigneeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdDate;
}
