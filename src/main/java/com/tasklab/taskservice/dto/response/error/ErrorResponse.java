package com.tasklab.taskservice.dto.response.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class ErrorResponse {

    @JsonProperty("error_message")
    private String errorMessage;

    @JsonProperty("details")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> details;

    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}