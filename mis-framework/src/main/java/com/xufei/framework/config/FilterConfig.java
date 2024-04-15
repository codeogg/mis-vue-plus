package com.xufei.framework.config;

import com.xufei.common.filter.RepeatableFilter;
import com.xufei.common.filter.XssFilter;
import com.xufei.common.utils.StringUtil;
import com.xufei.framework.properties.XssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.DispatcherType;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class FilterConfig {

    @Autowired
    private XssProperties xssProperties;

    @Bean
    @ConditionalOnProperty(value = "xss.enabled", havingValue = "true")
    public FilterRegistrationBean xssFilterRegistration(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        registrationBean.setFilter(new XssFilter());

        String[] urlPatterns = StringUtil.split(xssProperties.getUrlPatterns(), StringUtil.SEPARATOR);
        registrationBean.addUrlPatterns(urlPatterns);

        registrationBean.setName("xssFilter");
        registrationBean.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);

        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("excludes", xssProperties.getExcludes());
        registrationBean.setInitParameters(initParameters);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean repeatableFilterRegistration(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new RepeatableFilter());

        registrationBean.addUrlPatterns("/**");
        registrationBean.setName("repeatableFilter");
        registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);

        return registrationBean;
    }
}
