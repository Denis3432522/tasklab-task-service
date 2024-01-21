package com.tasklab.taskservice.exception;

import com.tasklab.taskservice.dto.response.error.ErrorResponse;

import java.util.Optional;

public class AccessDeniedException extends RuntimeException implements DetailedException  {

    private ErrorResponse errorResponse;

    public AccessDeniedException() {}

    public AccessDeniedException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public Optional<ErrorResponse> getErrorResponse() {
        return Optional.ofNullable(errorResponse);
    }
}
