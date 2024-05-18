package com.tasklab.taskservice.service.task;

import com.tasklab.taskservice.dto.request.TaskCreateRequest;
import com.tasklab.taskservice.dto.response.TaskView;
import com.tasklab.taskservice.dto.response.error.ErrorResponse;
import com.tasklab.taskservice.dto.response.error.ResourceNotFoundResponse;
import com.tasklab.taskservice.entity.Task;
import com.tasklab.taskservice.entity.TaskProgress;
import com.tasklab.taskservice.enumeration.TaskStatus;
import com.tasklab.taskservice.exception.BadRequestException;
import com.tasklab.taskservice.exception.ResourceNotFoundException;
import com.tasklab.taskservice.repository.TaskRepository;
import com.tasklab.taskservice.service.group.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService {

    private final DefaultTaskListService taskListService;
    private final TaskRepository taskRepository;
    private final GroupService groupService;

    public void assertTaskTitleUniqueInGroup(String title, UUID groupId) {
        if(taskRepository.existsByTitleAndGroupId(title, groupId))
            throw new BadRequestException(ErrorResponse.builder()
                    .errorMessage("Task with title " + title + " already exists in the group")
                    .build());
    }

    @Transactional
    public UUID createTaskAggregate(TaskCreateRequest request, UUID userId) {
        Task task = createTask(request, userId);

        if(request.getTaskListId() != null) {
            task.setTaskList(taskListService.getReferenceById(request.getTaskListId()));
        }

        TaskProgress taskProgress = createTaskProgress(request, task);
        task.setTaskProgress(taskProgress);

        return taskRepository.save(task).getId();
    }

    private Task createTask(TaskCreateRequest request, UUID userId) {
        return Task.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .priority(request.getPriority())
                .difficulty(request.getDifficulty())
                .requiredSolverCount(request.getRequiredSolverCount())
                .publishedBy(userId)
                .group(groupService.getGroupReferenceById(request.getGroupId()))
                .build();
    }

    private TaskProgress createTaskProgress(TaskCreateRequest request, Task task) {
        return TaskProgress.builder()
                .timeToSolve(request.getTimeToSolve())
                .status(TaskStatus.PUBLISHED)
                .task(task)
                .build();
    }

    public TaskView getTaskView(UUID id, UUID groupId) {
        return taskRepository.findViewByIdAndGroupId(id, groupId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundResponse.of("Task with ID" + id)));
    }

    public List<TaskView> getTaskViews(UUID groupId, Pageable pageable) {
        return taskRepository.findAllViewsByGroupId(groupId, pageable);
    }
}
