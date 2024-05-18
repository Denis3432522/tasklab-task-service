package com.tasklab.taskservice.service.group;

import com.tasklab.taskservice.enumeration.GroupRole;

import java.util.List;
import java.util.UUID;

public interface GroupAccessAuthorizer {

    void authorizeUserForGroup(UUID groupId, UUID userId, List<GroupRole> authorizedRoles);

    void assertUserInGroup(UUID groupId, UUID userId);
}
