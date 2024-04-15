package com.xufei.controller.common;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.xufei.common.constants.CacheConstants;
import com.xufei.common.constants.CommonConstants;
import com.xufei.common.core.R;
import com.xufei.common.enums.CaptchaType;
import com.xufei.common.utils.RedisUtil;
import com.xufei.common.utils.StringUtil;
import com.xufei.framework.properties.CaptchaProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@SaIgnore
@Slf4j
@RestController
@RequiredArgsConstructor
public class CaptchaController {

    private final CaptchaProperties captchaProperties;

    @GetMapping("/captchaImage")
    public R<Map<String, Object>> getCode() {
        Map<String, Object> ajax = new HashMap<>();
        Boolean captchaEnabled = Convert.toBool(captchaProperties.getEnabled());
        ajax.put("captchaEnabled", captchaEnabled);
        if (!captchaEnabled) {
            return R.ok(ajax);
        }

        String uuid = IdUtil.simpleUUID();
        String redisKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

        CaptchaType captchaType = captchaProperties.getType();
        boolean isMath = CaptchaType.MATH == captchaType;

        Integer length = isMath ? captchaProperties.getNumberLength() : captchaProperties.getCharLength();
        CodeGenerator codeGenerator = ReflectUtil.newInstance(captchaType.getClazz(), length);

        AbstractCaptcha captcha = SpringUtil.getBean(captchaProperties.getCategory().getClazz());
        captcha.setGenerator(codeGenerator);
        captcha.createCode();

        String code = captcha.getCode();

        if(isMath){
            ExpressionParser parser = new SpelExpressionParser();
            Expression exp = parser.parseExpression(StringUtil.remove(code, "="));
            code = exp.getValue(String.class);
        }

        RedisUtil.setCacheObject(redisKey, code, Duration.ofMinutes(CommonConstants.CAPTCHA_EXPIRATION));
        ajax.put("uuid", uuid);
        ajax.put("img", captcha.getImageBase64());

        return R.ok(ajax);
    }
}
