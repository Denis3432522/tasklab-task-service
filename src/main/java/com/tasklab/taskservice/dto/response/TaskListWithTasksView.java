package com.tasklab.taskservice.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tasklab.taskservice.enumeration.TaskDifficulty;
import com.tasklab.taskservice.enumeration.TaskPriority;
import com.tasklab.taskservice.enumeration.TaskStatus;
import lombok.Getter;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public interface TaskListWithTasksView {

    UUID getId();

    String getTitle();

    String getDescription();

    UUID getPublishedBy();

    List<TaskInTaskListView> getTasks();

    interface TaskInTaskListView {

        UUID getId();
        String getTitle();
        String getContent();
        TaskPriority getPriority();
        TaskDifficulty getDifficulty();
        int getRequiredSolverCount();
        UUID getPublishedBy();

        @JsonProperty("progress")
        TaskProgressView getTaskProgress();
    }

    interface TaskProgressView {

        @JsonIgnore
        Duration getTimeToSolve();

        @JsonProperty("task_status")
        TaskStatus getStatus();

        @JsonProperty("started_at")
        ZonedDateTime getStartedAt();

        @JsonProperty("time_to_solve")
        default long getTimeToSolveInSecs() {
            return getTimeToSolve().toSeconds();
        }
    }
}
