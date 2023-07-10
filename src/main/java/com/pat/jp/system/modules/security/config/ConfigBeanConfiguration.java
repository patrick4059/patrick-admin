package com.pat.jp.system.modules.security.config;

import com.pat.jp.system.modules.security.config.bean.LoginProperties;
import com.pat.jp.system.modules.security.config.bean.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: huangwei
 * @date: 2023-07-10
 * @time: 17:07
 **/
@Configuration
public class ConfigBeanConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "login")
    public LoginProperties loginProperties() {
        return new LoginProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "jwt")
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }
}
