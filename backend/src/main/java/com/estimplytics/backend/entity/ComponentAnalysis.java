package com.estimplytics.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "component_analyses", uniqueConstraints = {@UniqueConstraint(columnNames = {"analysis_id", "component_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComponentAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "analysis_id", nullable = false)
    private ImpactAnalysis analysis;

    @ManyToOne
    @JoinColumn(name = "component_id", nullable = false)
    private Component component;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
