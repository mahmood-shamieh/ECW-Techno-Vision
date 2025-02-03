package com.example.momoPlans.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
//@ConfigurationProperties(prefix = "auth")
public class Config {
    @Value("${soap.api.endpoint}")
    public String ENDPOINT;
    @Value("${soap.api.action}")
    public String ACTION;
    @Value("${soap.api.auth}")
    public String AUTH_HEADER;


}
