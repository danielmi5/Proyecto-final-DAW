package com.estimplytics.backend.repository;

import com.estimplytics.backend.entity.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComponentRepository extends JpaRepository<Component, UUID> {
}