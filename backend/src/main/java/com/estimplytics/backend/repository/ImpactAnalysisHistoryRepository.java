package com.estimplytics.backend.repository;

import com.estimplytics.backend.entity.ImpactAnalysisHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpactAnalysisHistoryRepository extends JpaRepository<ImpactAnalysisHistory, Long> {
}