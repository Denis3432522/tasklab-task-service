package com.tasklab.taskservice.config.props;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "custom.group")
@Validated
@Getter
@Setter
public class GroupProps {

    @Min(1)
    private int pageSize;
}
