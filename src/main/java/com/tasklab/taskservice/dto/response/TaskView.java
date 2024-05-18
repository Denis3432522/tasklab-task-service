package com.tasklab.taskservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tasklab.taskservice.enumeration.TaskDifficulty;
import com.tasklab.taskservice.enumeration.TaskPriority;
import com.tasklab.taskservice.enumeration.TaskStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class TaskView {

    private UUID id;

    private String title;

    private String content;

    private TaskPriority priority;

    private TaskDifficulty difficulty;

    private int requiredSolverCount;

    private UUID publishedBy;

    private UUID taskListId;

    @JsonProperty("time_to_solve")
    private Duration taskProgressTimeToSolve;

    @JsonProperty("task_status")
    private TaskStatus taskProgressStatus;

    @JsonProperty("started_at")
    private ZonedDateTime taskProgressStartedAt;

    public long getTaskProgressTimeToSolve() {
        return taskProgressTimeToSolve.toSeconds();
    }
}
