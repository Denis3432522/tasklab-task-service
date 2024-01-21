package com.tasklab.taskservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class GroupView {

    private UUID id;
    private String name;
    private int maximumSize;
    private int size;
}
