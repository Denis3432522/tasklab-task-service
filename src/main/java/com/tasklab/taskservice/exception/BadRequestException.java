package com.tasklab.taskservice.exception;


import com.tasklab.taskservice.dto.response.error.ErrorResponse;

import java.util.Optional;

public class BadRequestException extends RuntimeException implements DetailedException {

    private final ErrorResponse errorResponse;

    public BadRequestException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public Optional<ErrorResponse> getErrorResponse() {
        return Optional.ofNullable(errorResponse);
    }
}
