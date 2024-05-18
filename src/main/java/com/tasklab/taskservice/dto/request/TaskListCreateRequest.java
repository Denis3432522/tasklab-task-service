package com.tasklab.taskservice.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.UUID;

@Getter
public class TaskListCreateRequest {

    @NotNull
    @Pattern(regexp = "[\\w\\s]{2,60}", message = "{messages.default.title}")
    private String title;

    @NotNull
    @Size(min = 20, max = 600, message = "must be between {min} and {max} symbols")
    private String description;

    @NotNull
    private UUID groupId;
}
