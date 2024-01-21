package com.tasklab.taskservice.repository;

import com.tasklab.taskservice.dto.response.GroupView;
import com.tasklab.taskservice.entity.Group;
import com.tasklab.taskservice.enumeration.GroupJoinAccess;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {

    boolean existsByName(String name);

    Optional<GroupView> findViewByIdAndJoinAccess(UUID id, GroupJoinAccess joinAccess);

    Optional<GroupView> findViewByNameAndJoinAccess(String name, GroupJoinAccess joinAccess);

    List<GroupView> findAllViewsByJoinAccess(GroupJoinAccess joinAccess, Pageable pageable);
}
