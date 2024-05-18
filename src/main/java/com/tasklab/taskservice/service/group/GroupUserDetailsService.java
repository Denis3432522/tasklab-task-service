package com.tasklab.taskservice.service.group;

import com.tasklab.taskservice.entity.Group;
import com.tasklab.taskservice.enumeration.GroupRole;

import java.util.UUID;

public interface GroupUserDetailsService {
    void persistGroupUserDetails(Group group, GroupRole role, UUID userId);
}
