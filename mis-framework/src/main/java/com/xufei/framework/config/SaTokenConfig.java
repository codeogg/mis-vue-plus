package com.xufei.framework.config;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.xufei.framework.handler.AllUrlHandler;
import com.xufei.framework.properties.SecurityProperties;
import com.xufei.framework.satoken.SaTokenDaoImpl;
import com.xufei.framework.satoken.StpInterfaceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SaTokenConfig implements WebMvcConfigurer {

    private final SecurityProperties securityProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handler -> {
            AllUrlHandler allUrlHandler = SpringUtil.getBean(AllUrlHandler.class);
            SaRouter.match(allUrlHandler.getUrls())
                    .check(() -> {
                        StpUtil.checkLogin();
                    });
        })).addPathPatterns("/**")
                .excludePathPatterns(securityProperties.getExcludes());
    }

    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }

    @Bean
    public StpInterface stpInterface(){
        return new StpInterfaceImpl();
    }

    @Bean
    public SaTokenDao saTokenDao(){
        return new SaTokenDaoImpl();
    }
}
