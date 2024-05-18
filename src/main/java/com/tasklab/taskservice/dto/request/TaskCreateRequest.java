package com.tasklab.taskservice.dto.request;

import com.tasklab.taskservice.dto.validator.hibernate.annotation.NamedEnumString;
import com.tasklab.taskservice.enumeration.TaskDifficulty;
import com.tasklab.taskservice.enumeration.TaskPriority;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import java.time.Duration;
import java.util.UUID;

@Getter
public class TaskCreateRequest {

    @NotNull
    @Pattern(regexp = "[\\w\\s]{2,60}", message = "{messages.default.name}")
    private String title;

    @NotNull
    @Size(min = 20, max = 2500, message = "must be between {min} and {max} symbols")
    private String content;

    @NotNull
    @NamedEnumString(TaskPriority.class)
    private String priority;

    @NotNull
    @NamedEnumString(TaskDifficulty.class)
    private String difficulty;

    @NotNull
    @Range(min = 1, max = 20)
    private Integer requiredSolverCount;

    @NotNull
    private Duration timeToSolve;

    @NotNull
    private UUID groupId;

    private UUID taskListId;

    public TaskPriority getPriority() {
        return TaskPriority.getByName(priority);
    }

    public TaskDifficulty getDifficulty() {
        return TaskDifficulty.getByName(difficulty);
    }
}
