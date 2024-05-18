package com.tasklab.taskservice.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import com.tasklab.taskservice.util.EnumUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TaskDifficulty implements Named {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard");

    private final String name;

    @Override
    @JsonValue
    public String getName() {
        return name;
    }

    public static TaskDifficulty getByName(String srcName) {
        return EnumUtil.getByName(srcName, values());
    }
}
