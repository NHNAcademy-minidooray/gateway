package com.nhnacademy.minidooray.gateway.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.rest.template.task")
@Getter
@Setter
public class TaskProperties {
    private String host;
    private String port;
}
