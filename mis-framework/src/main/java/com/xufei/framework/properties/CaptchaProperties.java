package com.xufei.framework.properties;

import com.xufei.common.enums.CaptchaCategory;
import com.xufei.common.enums.CaptchaType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "captcha")
public class CaptchaProperties {

    private CaptchaType type;
    private CaptchaCategory category;
    private Integer numberLength;
    private Integer charLength;
}
