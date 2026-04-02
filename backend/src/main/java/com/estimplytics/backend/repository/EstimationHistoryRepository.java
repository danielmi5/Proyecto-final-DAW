package com.estimplytics.backend.repository;

import com.estimplytics.backend.entity.EstimationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstimationHistoryRepository extends JpaRepository<EstimationHistory, Long> {
}