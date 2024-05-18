package com.tasklab.taskservice.repository;

import com.tasklab.taskservice.dto.response.TaskView;
import com.tasklab.taskservice.entity.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    boolean existsByTitleAndGroupId(String title, UUID groupId);

    Optional<TaskView> findViewByIdAndGroupId(UUID id, UUID groupId);

    List<TaskView> findAllViewsByGroupId(UUID groupId, Pageable pageable);
}
