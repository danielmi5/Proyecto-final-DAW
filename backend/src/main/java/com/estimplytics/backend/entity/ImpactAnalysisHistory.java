package com.estimplytics.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;

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

    @Column(name = "frozen_version", nullable = false)
    private Integer frozenVersion;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "snapshot_data", nullable = false, columnDefinition = "jsonb")
    private Map<String, Object> snapshotData;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "components_snapshot", nullable = false, columnDefinition = "jsonb")
    private Map<String, Object> componentsSnapshot;

    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @PrePersist
    protected void onCreate() {
        this.modifiedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedAt = LocalDateTime.now();
    }
}
