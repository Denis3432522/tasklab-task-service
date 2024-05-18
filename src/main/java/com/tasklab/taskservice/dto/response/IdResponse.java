package com.tasklab.taskservice.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class IdResponse {

    private final String id;

    public static IdResponse of(String id) {
        return new IdResponse(id);
    }

    public static IdResponse of(UUID id) {
        return new IdResponse(id.toString());
    }
}

