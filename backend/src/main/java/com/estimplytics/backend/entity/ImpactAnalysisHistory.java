package com.estimplytics.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "impact_analysis_histories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImpactAnalysisHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "analysis_id", nullable = false)
    private ImpactAnalysis analysis;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User analyst;

    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
