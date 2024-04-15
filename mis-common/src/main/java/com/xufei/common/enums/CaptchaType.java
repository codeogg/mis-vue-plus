package com.xufei.common.enums;

import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.captcha.generator.RandomGenerator;
import com.xufei.common.captcha.UnsignedMathGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CaptchaType {

    MATH(UnsignedMathGenerator.class),
    CHAR(RandomGenerator.class);

    private final Class<? extends CodeGenerator> clazz;

}
