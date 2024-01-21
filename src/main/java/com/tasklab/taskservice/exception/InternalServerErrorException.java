package com.tasklab.taskservice.exception;

import com.tasklab.taskservice.dto.response.error.ErrorResponse;

import java.util.Optional;

public class InternalServerErrorException extends RuntimeException implements DetailedException {

    private ErrorResponse errorResponse;

    public InternalServerErrorException() {
    }

    public InternalServerErrorException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public InternalServerErrorException(ErrorResponse errorResponse, Throwable cause) {
        super(cause);
        this.errorResponse = errorResponse;
    }

    public Optional<ErrorResponse> getErrorResponse() {
        return Optional.ofNullable(errorResponse);
    }
}
