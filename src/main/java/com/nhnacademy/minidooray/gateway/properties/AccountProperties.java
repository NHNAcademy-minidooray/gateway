package com.nhnacademy.minidooray.gateway.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.rest.template.account")
@Getter
@Setter
public class AccountProperties {
    private String host;
    private String port;
}
