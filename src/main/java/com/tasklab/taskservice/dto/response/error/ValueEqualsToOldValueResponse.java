package com.tasklab.taskservice.dto.response.error;

public class ValueEqualsToOldValueResponse extends ErrorResponse {
    private static final String DEFAULT_MESSAGE_PATTERN = "? must not equal to old ?";
    private static final String DEFAULT_MESSAGE = DEFAULT_MESSAGE_PATTERN.replaceAll("\\?", "value");

    private ValueEqualsToOldValueResponse() {
        super(DEFAULT_MESSAGE, null);
    }

    private ValueEqualsToOldValueResponse(String valueName) {
        super(DEFAULT_MESSAGE_PATTERN.replaceAll("\\?", valueName), null);
    }

    public static ValueEqualsToOldValueResponse of(String valueName) {
        if(valueName == null)
            return new ValueEqualsToOldValueResponse();

        return new ValueEqualsToOldValueResponse(valueName);
    }
}
