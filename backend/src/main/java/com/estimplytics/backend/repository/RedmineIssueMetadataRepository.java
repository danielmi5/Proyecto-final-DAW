package com.estimplytics.backend.repository;

import com.estimplytics.backend.entity.RedmineIssueMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RedmineIssueMetadataRepository extends JpaRepository<RedmineIssueMetadata, Long> {
    Optional<RedmineIssueMetadata> findByRedmineIdAndRedmineInstanceId(Integer redmineId, Long instanceId);
    Optional<RedmineIssueMetadata> findByRequestId(UUID requestId);
}
