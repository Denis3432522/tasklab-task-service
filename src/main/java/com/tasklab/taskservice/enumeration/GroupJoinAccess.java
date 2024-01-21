package com.tasklab.taskservice.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import com.tasklab.taskservice.util.EnumUtil;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GroupJoinAccess implements Named {
    PUBLIC("public"),
    PRIVATE("private"),
    CLOSED("closed");

    private final String name;

    @JsonValue
    public String getName() {
        return name;
    }

    public static GroupJoinAccess getByName(String srcName) {
        return EnumUtil.getByName(srcName, values());
    }
}
