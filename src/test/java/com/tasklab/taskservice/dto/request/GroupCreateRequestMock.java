package com.tasklab.taskservice.dto.request;

import com.tasklab.taskservice.enumeration.GroupJoinAccess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class GroupCreateRequestMock extends GroupCreateRequest {

    private String name;
    private int maximumSize;
    private String joinAccess;

    public GroupJoinAccess getJoinAccess() {
        return GroupJoinAccess.getByName(joinAccess);
    }
}