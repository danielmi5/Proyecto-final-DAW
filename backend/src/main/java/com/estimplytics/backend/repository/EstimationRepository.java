package com.estimplytics.backend.repository;

import com.estimplytics.backend.entity.Estimation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstimationRepository extends JpaRepository<Estimation, UUID> {
}