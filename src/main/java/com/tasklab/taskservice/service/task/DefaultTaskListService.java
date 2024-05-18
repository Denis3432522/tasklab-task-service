package com.tasklab.taskservice.service.task;

import com.tasklab.taskservice.dto.request.TaskListCreateRequest;
import com.tasklab.taskservice.dto.response.TaskListView;
import com.tasklab.taskservice.dto.response.TaskListWithTasksView;
import com.tasklab.taskservice.dto.response.TaskView;
import com.tasklab.taskservice.dto.response.error.ErrorResponse;
import com.tasklab.taskservice.dto.response.error.ResourceNotFoundResponse;
import com.tasklab.taskservice.entity.TaskList;
import com.tasklab.taskservice.exception.BadRequestException;
import com.tasklab.taskservice.exception.ResourceNotFoundException;
import com.tasklab.taskservice.repository.TaskListRepository;
import com.tasklab.taskservice.service.group.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultTaskListService implements TaskListService {

    private final TaskListRepository taskListRepository;
    private final GroupService groupService;

    public UUID createTaskList(TaskListCreateRequest request, UUID userId) {
        return taskListRepository.save(TaskList.builder()
                    .title(request.getTitle())
                    .description(request.getDescription())
                    .publishedBy(userId)
                    .group(groupService.getGroupReferenceById(request.getGroupId()))
                    .build())
                .getId();
    }

    public void assertTaskListTitleUniqueInGroup(String title, UUID groupId) {
        if(taskListRepository.existsByTitleAndGroupId(title, groupId))
            throw new BadRequestException(ErrorResponse.builder()
                    .errorMessage("Task list with title " + title + " already exists in the group")
                    .build());
    }

    public void assertTaskListExistsInGroup(UUID taskListId, UUID groupId) {
        if(!taskListRepository.existsByIdAndGroupId(taskListId, groupId))
            throw new ResourceNotFoundException(
                    ResourceNotFoundResponse.of("Task list with ID " + taskListRepository)
            );
    }

    public TaskList getReferenceById(UUID taskListId) {
        return taskListRepository.getReferenceById(taskListId);
    }

    public TaskListWithTasksView getTaskListView(UUID id, UUID groupId) {
        return taskListRepository.findViewByIdAndGroupId(id, groupId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundResponse.of("Task with ID" + id)));
    }

    public List<TaskListWithTasksView> getTaskListViews(UUID groupId, Pageable pageable) {
        List<TaskListRepository.TaskListId> ids = taskListRepository.findAllByGroupId(groupId, pageable);
        return taskListRepository.findAllViewsByIdIn(ids.stream().map(TaskListRepository.TaskListId::getId).toList());
    }
}
