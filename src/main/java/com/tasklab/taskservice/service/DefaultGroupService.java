package com.tasklab.taskservice.service;

import com.tasklab.taskservice.dto.request.GroupCreateRequest;
import com.tasklab.taskservice.dto.request.GroupPatchRequest;
import com.tasklab.taskservice.dto.response.GroupView;
import com.tasklab.taskservice.dto.response.error.ResourceNotFoundResponse;
import com.tasklab.taskservice.dto.response.error.ValueNotUniqueResponse;
import com.tasklab.taskservice.dto.validator.GroupValidator;
import com.tasklab.taskservice.entity.Group;
import com.tasklab.taskservice.enumeration.GroupJoinAccess;
import com.tasklab.taskservice.exception.BadRequestException;
import com.tasklab.taskservice.exception.ResourceNotFoundException;
import com.tasklab.taskservice.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultGroupService implements GroupService {

    private final GroupValidator groupValidator;
    private final GroupRepository groupRepository;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Group persistGroup(GroupCreateRequest request) {
        return groupRepository.save(Group.builder()
                .name(request.getName())
                .maximumSize(request.getMaximumSize())
                .joinAccess(request.getJoinAccess())
                .size(1)
                .build());
    }

    @Override
    public void assertGroupNameUnique(String name) {
        if(groupRepository.existsByName(name))
            throw new BadRequestException(ValueNotUniqueResponse.of(name));
    }

    @Override
    public List<GroupView> getPublicGroupViews(Pageable pageable) {
        return groupRepository.findAllViewsByJoinAccess(GroupJoinAccess.PUBLIC, pageable);
    }

    @Override
    public GroupView getPublicGroupView(String id) {
        Optional<GroupView> groupView;

        try {
            groupView = groupRepository.findViewByIdAndJoinAccess(UUID.fromString(id), GroupJoinAccess.PUBLIC);
        } catch (IllegalArgumentException e) {
            groupView = groupRepository.findViewByNameAndJoinAccess(id, GroupJoinAccess.PUBLIC);
        }

        return groupView.orElseThrow(() ->
                new ResourceNotFoundException(ResourceNotFoundResponse.of("group"))
        );
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void changeGroup(GroupPatchRequest request, UUID groupId) {
        Group group = getGroup(groupId);

        if(request.getName() != null)
            changeGroupName(group, request.getName());

        if(request.getMaximumSize() != null)
            changeGroupMaximumSize(group, request.getMaximumSize());

        if(request.getJoinAccess() != null)
            changeGroupJoinAccess(group, request.getJoinAccess());
    }

    private Group getGroup(UUID groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundResponse.of("group")));
    }

    private void changeGroupName(Group group, String newName) {
        groupValidator.validateName(group.getName(), newName);
        group.setName(newName);
    }

    private void changeGroupMaximumSize(Group group, Integer newMaximumSize) {
        groupValidator.validateMaximumSize(group, newMaximumSize);
        group.setMaximumSize(newMaximumSize);
    }

    private void changeGroupJoinAccess(Group group, GroupJoinAccess newJoinAccess) {
        groupValidator.validateJoinAccess(group.getJoinAccess(), newJoinAccess);
        group.setJoinAccess(newJoinAccess);
    }
}
