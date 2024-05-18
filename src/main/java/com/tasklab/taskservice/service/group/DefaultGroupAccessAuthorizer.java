package com.tasklab.taskservice.service.group;

import com.tasklab.taskservice.enumeration.GroupRole;
import com.tasklab.taskservice.exception.AccessDeniedException;
import com.tasklab.taskservice.repository.GroupUserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultGroupAccessAuthorizer implements GroupAccessAuthorizer {

    private final GroupUserDetailsRepository groupUserRepository;

    public void authorizeUserForGroup(UUID groupId, UUID userId, List<GroupRole> authorizedRoles) {
        if(authorizedRoles.isEmpty())
            throw new AccessDeniedException();

        GroupRole groupRole = groupUserRepository.findByGroupIdAndUserId(groupId, userId)
                .orElseThrow(AccessDeniedException::new)
                .getRole();

        if(authorizedRoles.stream().noneMatch(groupRole::equals))
            throw new AccessDeniedException();
    }

    public void assertUserInGroup(UUID groupId, UUID userId) {
        if(!groupUserRepository.existsByGroupIdAndUserId(groupId, userId))
            throw new AccessDeniedException();
    }
}
