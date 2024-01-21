package com.tasklab.taskservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tasklab.taskservice.dto.request.GroupCreateRequest;
import com.tasklab.taskservice.dto.request.GroupCreateRequestMock;
import com.tasklab.taskservice.entity.Group;
import com.tasklab.taskservice.enumeration.GroupJoinAccess;
import com.tasklab.taskservice.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GroupControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    GroupRepository groupRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void whenRequestBodyValidThenCreateUser() throws Exception {
        String groupName = "some221";
        GroupCreateRequest request = GroupCreateRequestMock.builder()
                .name(groupName)
                .joinAccess("public")
                .maximumSize(20)
                .build();


        mockMvc.perform(post("/groups")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(request))
                        .header("Subject", "db98890b-aa97-48c1-94cc-7047a80f2a0c")
                ).andDo(print())
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(groupRepository.existsByName(groupName));
    }

    @Test
    void getPublicGroups() throws Exception {
        groupRepository.saveAll(List.of(
                Group.builder()
                        .name("test1")
                        .joinAccess(GroupJoinAccess.PUBLIC)
                        .maximumSize(20)
                        .size(1)
                        .build(),

                Group.builder()
                        .name("test2")
                        .joinAccess(GroupJoinAccess.PUBLIC)
                        .maximumSize(40)
                        .size(1)
                        .build()
        ));

        String payload = mockMvc.perform(get("/groups")
                        .queryParam("page", "1")
                ).andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(payload.length()).isGreaterThan(50);

        groupRepository.deleteAll();
    }
}