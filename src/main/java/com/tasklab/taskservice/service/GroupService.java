package com.tasklab.taskservice.service;

import com.tasklab.taskservice.dto.request.GroupCreateRequest;
import com.tasklab.taskservice.dto.request.GroupPatchRequest;
import com.tasklab.taskservice.dto.response.GroupView;
import com.tasklab.taskservice.entity.Group;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface GroupService {

    Group persistGroup(GroupCreateRequest request);

    void assertGroupNameUnique(String name);

    List<GroupView> getPublicGroupViews(Pageable pageable);

    GroupView getPublicGroupView(String id);

    void changeGroup(GroupPatchRequest request, UUID groupId);
}
