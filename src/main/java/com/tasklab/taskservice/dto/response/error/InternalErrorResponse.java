package com.tasklab.taskservice.dto.response.error;

public class InternalErrorResponse extends ErrorResponse {

    private static final String DEFAULT_MESSAGE = "Internal server error occurred, please try later";

    public static final InternalErrorResponse DEFAULT = new InternalErrorResponse();

    private InternalErrorResponse() {
        super(DEFAULT_MESSAGE, null);
    }
}
