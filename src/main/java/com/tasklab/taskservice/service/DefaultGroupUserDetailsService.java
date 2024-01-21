package com.tasklab.taskservice.service;

import com.tasklab.taskservice.entity.Group;
import com.tasklab.taskservice.entity.GroupUserDetails;
import com.tasklab.taskservice.enumeration.GroupRole;
import com.tasklab.taskservice.repository.GroupUserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultGroupUserDetailsService implements GroupUserDetailsService {

    private final GroupUserDetailsRepository groupUserRepository;
    @Override
    @Transactional
    public void persistGroupUserDetails(Group group, GroupRole role, UUID userId) {
        groupUserRepository.save(GroupUserDetails.builder()
                .userId(userId)
                .role(role)
                .group(group)
                .build());
    }
}
