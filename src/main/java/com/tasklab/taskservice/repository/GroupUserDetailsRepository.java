package com.tasklab.taskservice.repository;

import com.tasklab.taskservice.entity.GroupUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GroupUserDetailsRepository extends JpaRepository<GroupUserDetails, UUID> {

    Optional<GroupUserDetails> findByGroupIdAndUserId(UUID groupId, UUID userId);

    boolean existsByGroupIdAndUserId(UUID groupId, UUID userId);
}
