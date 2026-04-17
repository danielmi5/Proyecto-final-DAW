package com.estimplytics.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "redmine_id")
    private Integer redmineId;

    @Column(name = "origin_request_code", length = 50)
    private String originRequestCode;

    @Column(name = "project_name", length = 100, nullable = false)
    private String projectName;

    @Column(name = "demand_type", length = 50)
    private String demandType;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 50, nullable = false)
    private String status;

    @Column(length = 20, nullable = false)
    private String priority;

    @Column(name = "assignee_name", length = 100)
    private String assigneeName;

    @Column(name = "author_name", length = 100)
    private String authorName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "redmine_created_date")
    private LocalDateTime redmineCreatedDate;

    @Column(name = "redmine_updated_date")
    private LocalDateTime redmineUpdatedDate;

    @Column(name = "redmine_closed_date")
    private LocalDateTime redmineClosedDate;

    @Column(name = "done_ratio")
    private Integer doneRatio;

    @Column(name = "estimated_hours")
    private Double estimatedHours;

    @Column(name = "spent_hours")
    private Double spentHours;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }
}
