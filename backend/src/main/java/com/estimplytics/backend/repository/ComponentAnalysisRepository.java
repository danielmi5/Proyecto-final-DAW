package com.estimplytics.backend.repository;

import com.estimplytics.backend.entity.ComponentAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComponentAnalysisRepository extends JpaRepository<ComponentAnalysis, UUID> {
}