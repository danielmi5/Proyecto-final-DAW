package com.estimplytics.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "estimation_histories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstimationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estimation_id", nullable = false)
    private Estimation estimation;

    @ManyToOne
    @JoinColumn(name = "analysis_id", nullable = false)
    private ImpactAnalysis analysis;

    @Column(name = "total_hours")
    private Integer totalHours;

    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
