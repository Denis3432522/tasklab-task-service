package com.tasklab.taskservice.service.task;

import com.tasklab.taskservice.dto.request.TaskCreateRequest;

import java.util.UUID;

public interface TaskService {

    UUID createTaskAggregate(TaskCreateRequest request, UUID userId);

    void assertTaskTitleUniqueInGroup(String title, UUID groupId);
}
