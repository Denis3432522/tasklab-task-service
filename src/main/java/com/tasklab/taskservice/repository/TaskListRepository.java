package com.tasklab.taskservice.repository;

import com.tasklab.taskservice.dto.response.TaskListView;
import com.tasklab.taskservice.dto.response.TaskListWithTasksView;
import com.tasklab.taskservice.dto.response.TaskView;
import com.tasklab.taskservice.entity.TaskList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListRepository extends JpaRepository<TaskList, UUID> {

    boolean existsByTitleAndGroupId(String title, UUID groupId);

    boolean existsByIdAndGroupId(UUID taskListId, UUID groupId);

    @EntityGraph(attributePaths = {"tasks", "tasks.taskProgress"})
    Optional<TaskListWithTasksView> findViewByIdAndGroupId(UUID id, UUID groupId);

   //
    List<TaskListId> findAllByGroupId(UUID groupId, Pageable pageable);

    @EntityGraph(attributePaths = {"tasks", "tasks.taskProgress"})
    List<TaskListWithTasksView> findAllViewsByIdIn(List<UUID> ids);

    interface TaskListId {
        UUID getId();
    }
}
