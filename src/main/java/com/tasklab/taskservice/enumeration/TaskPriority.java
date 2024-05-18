package com.tasklab.taskservice.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import com.tasklab.taskservice.util.EnumUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TaskPriority implements Named {
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high");

    private final String name;

    @Override
    @JsonValue
    public String getName() {
        return name;
    }

    public static TaskPriority getByName(String srcName) {
        return EnumUtil.getByName(srcName, values());
    }
}
