package com.tasklab.taskservice.dto.validator;

import com.tasklab.taskservice.entity.Group;
import com.tasklab.taskservice.enumeration.GroupJoinAccess;

public interface GroupValidator {
    void validateMaximumSize(Group group, Integer newMaximumSize);

    void validateName(String oldName, String newName);

    void validateJoinAccess(GroupJoinAccess oldJoinAccess, GroupJoinAccess newJoinAccess);
}
