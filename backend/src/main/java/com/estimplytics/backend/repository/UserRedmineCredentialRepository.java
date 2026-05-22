package com.estimplytics.backend.repository;

import com.estimplytics.backend.entity.User;
import com.estimplytics.backend.entity.UserRedmineCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRedmineCredentialRepository extends JpaRepository<UserRedmineCredential, Long> {
    List<UserRedmineCredential> findByUser(User user);
}
