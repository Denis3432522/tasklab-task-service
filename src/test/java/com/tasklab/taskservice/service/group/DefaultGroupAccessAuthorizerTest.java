package com.tasklab.taskservice.service.group;

import com.tasklab.taskservice.entity.GroupUserDetails;
import com.tasklab.taskservice.enumeration.GroupRole;
import com.tasklab.taskservice.repository.GroupUserDetailsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultGroupAccessAuthorizerTest {

    @Spy
    GroupUserDetailsRepository detailsRepository;
    @InjectMocks
    DefaultGroupAccessAuthorizer accessAuthorizer;
    @Captor
    ArgumentCaptor<UUID> groupIdCaptor;
    @Captor
    ArgumentCaptor<UUID> userIdCaptor;

    @Test
    void authorizeUserForGroup() {
        when(detailsRepository.findByGroupIdAndUserId(any(), any()))
                .thenReturn(Optional.of(GroupUserDetails.builder()
                        .role(GroupRole.MANAGER)
                        .build()));

        UUID groupId = UUID.fromString("b9736776-8ffd-4aaf-8c5b-530b233b2c35");
        UUID userId = UUID.fromString("89ed9af0-ff86-4278-89be-e0e3d755fcf1");

        assertDoesNotThrow(() -> accessAuthorizer.authorizeUserForGroup(
                groupId,
                userId,
                List.of(GroupRole.OWNER, GroupRole.MANAGER)
        ));

        verify(detailsRepository, times(1))
                .findByGroupIdAndUserId(groupIdCaptor.capture(), userIdCaptor.capture());

        assertThat(groupIdCaptor.getValue()).isEqualTo(groupId);
        assertThat(userIdCaptor.getValue()).isEqualTo(userId);
    }
}