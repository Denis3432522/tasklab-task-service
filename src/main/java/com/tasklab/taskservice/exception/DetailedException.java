package com.tasklab.taskservice.exception;


import com.tasklab.taskservice.dto.response.error.ErrorResponse;

import java.util.Optional;

public interface DetailedException {

    Optional<ErrorResponse> getErrorResponse();
}
