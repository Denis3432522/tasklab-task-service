package com.tasklab.taskservice.controller;

import com.tasklab.taskservice.config.props.GroupProps;
import com.tasklab.taskservice.dto.request.GroupCreateRequest;
import com.tasklab.taskservice.dto.request.GroupPatchRequest;
import com.tasklab.taskservice.dto.response.GroupView;
import com.tasklab.taskservice.dto.response.IdResponse;
import com.tasklab.taskservice.entity.Group;
import com.tasklab.taskservice.entity.GroupUserDetails;
import com.tasklab.taskservice.entity.Task;
import com.tasklab.taskservice.enumeration.GroupRole;
import com.tasklab.taskservice.repository.GroupRepository;
import com.tasklab.taskservice.repository.GroupUserDetailsRepository;
import com.tasklab.taskservice.repository.TaskRepository;
import com.tasklab.taskservice.service.group.GroupAccessAuthorizer;
import com.tasklab.taskservice.service.group.GroupFacadeService;
import com.tasklab.taskservice.service.group.GroupService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
@Validated
public class GroupController {

    private final GroupFacadeService groupFacadeService;
    private final GroupService groupService;
    private final GroupAccessAuthorizer groupAccessAuthorizer;
    private final GroupProps groupProps;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponse createGroup(@RequestBody @Valid GroupCreateRequest request, @RequestHeader("Subject") UUID userId) {
        var group = groupFacadeService.createGroup(request, userId);
        return IdResponse.of(group.getId());
    }

    @GetMapping
    public List<GroupView> getPublicGroups(@RequestParam(value = "page", defaultValue = "1") @Min(1) int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, groupProps.getPageSize());

        return groupService.getPublicGroupViews(pageable);
    }

    @GetMapping("/{id}")
    public GroupView getGroup(@PathVariable String id) {
        return groupService.getPublicGroupView(id);
    }

    @PatchMapping("/{id}")
    public void patchGroup(@RequestBody @Valid GroupPatchRequest request, @PathVariable UUID id, @RequestHeader("Subject") UUID userId) {
        groupAccessAuthorizer.authorizeUserForGroup(id, userId, List.of(GroupRole.OWNER, GroupRole.ADMIN));
        groupService.changeGroup(request, id);
    }
}
