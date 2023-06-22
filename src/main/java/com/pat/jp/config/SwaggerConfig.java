package com.pat.jp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author: huangwei
 * @date: 2023-06-22
 * @time: 13:31
 **/
@Configuration
@EnableSwagger2 // 开启swagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("帕特里克核心团队");
    }

    private ApiInfo apiInfo() {
        final Contact DEFAULT_CONTACT = new Contact("patrick", "www.patrick.com", "huangwei4059@gmail.com");
        return new ApiInfo("Api 接口文档",
                "Api 文档", "1.0",
                "www.patrickTeams.com",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
