package com.tasklab.taskservice.dto.request;

import com.tasklab.taskservice.dto.validator.hibernate.annotation.AtLeastOneNotNull;
import com.tasklab.taskservice.dto.validator.hibernate.annotation.NamedEnumString;
import com.tasklab.taskservice.enumeration.GroupJoinAccess;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
@AtLeastOneNotNull
public class GroupPatchRequest {

    @Pattern(regexp = "[\\w]{2,60}", message = "{messages.default.name}")
    private String name;

    @Range(min = 2, max = 200)
    private Integer maximumSize;

    @NamedEnumString(GroupJoinAccess.class)
    private String joinAccess;

    public GroupJoinAccess getJoinAccess() {
        return joinAccess == null ? null : GroupJoinAccess.getByName(joinAccess);
    }
}
