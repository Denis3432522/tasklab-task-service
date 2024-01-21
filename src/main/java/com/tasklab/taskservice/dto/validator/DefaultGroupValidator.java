package com.tasklab.taskservice.dto.validator;

import com.tasklab.taskservice.dto.response.error.ErrorResponse;
import com.tasklab.taskservice.dto.response.error.ValueEqualsToOldValueResponse;
import com.tasklab.taskservice.dto.response.error.ValueNotUniqueResponse;
import com.tasklab.taskservice.entity.Group;
import com.tasklab.taskservice.enumeration.GroupJoinAccess;
import com.tasklab.taskservice.exception.BadRequestException;
import com.tasklab.taskservice.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultGroupValidator implements GroupValidator {

    private final GroupRepository groupRepository;

    public void validateMaximumSize(Group group, Integer newMaximumSize) {
        if(group.getMaximumSize() == newMaximumSize)
            throw new BadRequestException(ValueEqualsToOldValueResponse.of("maximum size"));

        if(group.getSize() > newMaximumSize)
            throw new BadRequestException(new ErrorResponse("maximum size must be lower then current group size"));
    }

    public void validateName(String oldName, String newName) {
        if(oldName.equals(newName))
            throw new BadRequestException(ValueEqualsToOldValueResponse.of("name"));

        if(groupRepository.existsByName(newName))
            throw new BadRequestException(ValueNotUniqueResponse.of(newName));
    }

    public void validateJoinAccess(GroupJoinAccess oldJoinAccess, GroupJoinAccess newJoinAccess) {
        if(oldJoinAccess.equals(newJoinAccess))
            throw new BadRequestException(ValueEqualsToOldValueResponse.of("join access"));
    }
}
