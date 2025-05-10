package com.santander.processinstancevisualizer.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "process-paas-api")
public class ApiProperties {

    private String baseUrl;
    private String processInstanceEndpoint;
    private String processInstanceDetailsEndpoint;
    private String defaultDateRange;
}
