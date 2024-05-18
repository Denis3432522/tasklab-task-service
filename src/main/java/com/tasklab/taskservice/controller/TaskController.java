package com.tasklab.taskservice.controller;

import com.tasklab.taskservice.config.props.TaskProps;
import com.tasklab.taskservice.dto.request.TaskCreateRequest;
import com.tasklab.taskservice.dto.response.GroupView;
import com.tasklab.taskservice.dto.response.IdResponse;
import com.tasklab.taskservice.dto.response.TaskView;
import com.tasklab.taskservice.enumeration.GroupRole;
import com.tasklab.taskservice.service.group.GroupAccessAuthorizer;
import com.tasklab.taskservice.service.task.DefaultTaskListService;
import com.tasklab.taskservice.service.task.DefaultTaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@Validated
@RequiredArgsConstructor
public class TaskController {

    private final DefaultTaskService taskService;
    private final DefaultTaskListService taskListService;
    private final GroupAccessAuthorizer groupAccessAuthorizer;
    private final TaskProps taskProps;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponse createTask(@RequestBody @Valid TaskCreateRequest request, @RequestHeader("Subject") UUID userId) {
        groupAccessAuthorizer.authorizeUserForGroup(request.getGroupId(), userId,
                List.of(GroupRole.OWNER, GroupRole.ADMIN, GroupRole.MANAGER));

        taskService.assertTaskTitleUniqueInGroup(request.getTitle(), request.getGroupId());

        if(request.getTaskListId() != null)
            taskListService.assertTaskListExistsInGroup(request.getTaskListId(), request.getGroupId());

        return IdResponse.of(taskService.createTaskAggregate(request, userId));
    }

    @GetMapping
    public List<TaskView> getTasks(@RequestParam("group_id") UUID groupId, @RequestHeader("Subject") UUID userId,
                                   @RequestParam(value = "page", defaultValue = "1") @Min(1) int pageNumber) {
        groupAccessAuthorizer.assertUserInGroup(groupId, userId);

        Pageable pageable = PageRequest.of(pageNumber-1, taskProps.getPageSize());
        return taskService.getTaskViews(groupId, pageable);
    }

    @GetMapping("/{id}")
    public TaskView getTask(@PathVariable UUID id, @RequestParam("group_id") UUID groupId,
                            @RequestHeader("Subject") UUID userId) {
        groupAccessAuthorizer.assertUserInGroup(groupId, userId);

        return taskService.getTaskView(id, groupId);
    }
}