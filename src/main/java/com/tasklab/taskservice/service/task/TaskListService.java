package com.tasklab.taskservice.service.task;

import com.tasklab.taskservice.dto.request.TaskListCreateRequest;
import com.tasklab.taskservice.dto.response.TaskListView;
import com.tasklab.taskservice.dto.response.TaskListWithTasksView;
import com.tasklab.taskservice.dto.response.TaskView;
import com.tasklab.taskservice.entity.TaskList;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TaskListService {

    UUID createTaskList(TaskListCreateRequest request, UUID userId);

    void assertTaskListTitleUniqueInGroup(String title, UUID groupId);

    void assertTaskListExistsInGroup(UUID taskListId, UUID groupId);

    TaskList getReferenceById(UUID taskListId);

    TaskListWithTasksView getTaskListView(UUID id, UUID groupId);

    List<TaskListWithTasksView> getTaskListViews(UUID groupId, Pageable pageable);
}
