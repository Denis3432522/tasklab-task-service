package com.tasklab.taskservice.service;

import com.tasklab.taskservice.dto.request.GroupCreateRequest;
import com.tasklab.taskservice.entity.Group;
import com.tasklab.taskservice.enumeration.GroupRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupFacadeService {

    private final GroupService groupService;
    private final GroupUserDetailsService groupUserDetailsService;

    @Transactional
    public void createGroup(GroupCreateRequest request, UUID userId) {
        groupService.assertGroupNameUnique(request.getName());
        Group group = groupService.persistGroup(request);
        groupUserDetailsService.persistGroupUserDetails(group, GroupRole.OWNER, userId);
    }
}

