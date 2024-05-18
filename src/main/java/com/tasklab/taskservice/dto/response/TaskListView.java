package com.tasklab.taskservice.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class TaskListView {

    private UUID id;
    private String title;
    private String description;
    private UUID publishedBy;
}
