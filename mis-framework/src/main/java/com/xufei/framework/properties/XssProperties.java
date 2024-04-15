package com.xufei.framework.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "xss")
public class XssProperties {

    private String enabled;
    private String excludes;
    private String urlPatterns;
}
