package com.tasklab.taskservice.dto.request;

import com.tasklab.taskservice.dto.validator.hibernate.annotation.NamedEnumString;
import com.tasklab.taskservice.enumeration.GroupJoinAccess;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class GroupCreateRequest {

    @NotNull
    @Pattern(regexp = "[\\w]{2,60}", message = "{messages.default.name}")
    private String name;

    @Range(min = 2, max = 200)
    private int maximumSize;

    @NotNull
    @NamedEnumString(GroupJoinAccess.class)
    private String joinAccess;

    public GroupJoinAccess getJoinAccess() {
        return GroupJoinAccess.getByName(joinAccess);
    }
}
