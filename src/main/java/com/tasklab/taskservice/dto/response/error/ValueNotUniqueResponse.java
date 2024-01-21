package com.tasklab.taskservice.dto.response.error;

public class ValueNotUniqueResponse extends ErrorResponse {

    private static final String MESSAGE_POSTFIX = " already taken";
    private static final String DEFAULT_MESSAGE = "value" + MESSAGE_POSTFIX;
    private static final ValueNotUniqueResponse DEFAULT = new ValueNotUniqueResponse();

    private ValueNotUniqueResponse() {
        super(DEFAULT_MESSAGE, null);
    }

    private ValueNotUniqueResponse(String valueName) {
        super(valueName + MESSAGE_POSTFIX, null);
    }

    public static ValueNotUniqueResponse of(String valueName) {
        return new ValueNotUniqueResponse(valueName);
    }
}

