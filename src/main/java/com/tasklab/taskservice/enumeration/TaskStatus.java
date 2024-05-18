package com.tasklab.taskservice.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import com.tasklab.taskservice.util.EnumUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TaskStatus implements Named {
    PUBLISHED("published"),
    IN_PROGRESS("in_progress"),
    FINISHED("finished");

    private final String name;

    @Override
    @JsonValue
    public String getName() {
        return name;
    }

    public static TaskStatus getByName(String srcName) {
        return EnumUtil.getByName(srcName, values());
    }
}
