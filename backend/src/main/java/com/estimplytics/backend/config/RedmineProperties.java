package com.estimplytics.backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "redmine.api")
public class RedmineProperties {
    private String url;
	private String key;
}
