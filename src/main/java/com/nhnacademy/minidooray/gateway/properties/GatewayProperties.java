package com.nhnacademy.minidooray.gateway.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "gateway")
@Getter
@Setter
public class GatewayProperties {
    private String host;
    private String port;
}
