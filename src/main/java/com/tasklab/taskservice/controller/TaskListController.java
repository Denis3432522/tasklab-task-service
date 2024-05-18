package com.tasklab.taskservice.controller;

import com.tasklab.taskservice.config.props.DefaultProps;
import com.tasklab.taskservice.dto.request.TaskListCreateRequest;
import com.tasklab.taskservice.dto.response.IdResponse;
import com.tasklab.taskservice.dto.response.TaskListView;
import com.tasklab.taskservice.dto.response.TaskListWithTasksView;
import com.tasklab.taskservice.dto.response.TaskView;
import com.tasklab.taskservice.enumeration.GroupRole;
import com.tasklab.taskservice.service.group.GroupAccessAuthorizer;
import com.tasklab.taskservice.service.group.GroupService;
import com.tasklab.taskservice.service.task.TaskListService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task-lists")
@RequiredArgsConstructor
public class TaskListController {

    private final TaskListService taskListService;
    private final GroupAccessAuthorizer groupAccessAuthorizer;
    private final DefaultProps defaultProps;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponse createTaskList(@RequestBody @Valid TaskListCreateRequest request, @RequestHeader("Subject") UUID userId) {
        groupAccessAuthorizer.authorizeUserForGroup(request.getGroupId(), userId,
                List.of(GroupRole.OWNER, GroupRole.ADMIN, GroupRole.MANAGER));

        taskListService.assertTaskListTitleUniqueInGroup(request.getTitle(), request.getGroupId());

        return IdResponse.of(taskListService.createTaskList(request, userId));
    }

    @GetMapping
    public List<TaskListWithTasksView> getTaskLists(@RequestParam("group_id") UUID groupId, @RequestHeader("Subject") UUID userId,
                                   @RequestParam(value = "page", defaultValue = "1") @Min(1) int pageNumber) {
        groupAccessAuthorizer.assertUserInGroup(groupId, userId);

        Pageable pageable = PageRequest.of(pageNumber-1, defaultProps.getPageSize());
        return taskListService.getTaskListViews(groupId, pageable);
    }

    @GetMapping("/{id}")
    public TaskListWithTasksView getTaskList(@PathVariable UUID id, @RequestParam("group_id") UUID groupId,
                                             @RequestHeader("Subject") UUID userId) {
        groupAccessAuthorizer.assertUserInGroup(groupId, userId);

        return taskListService.getTaskListView(id, groupId);
    }
}
