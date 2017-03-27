package com.twogether.backend.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {

        ArrayList<SecurityScheme> auth = new ArrayList<SecurityScheme>(1);
        auth.add(new ApiKey("Authorization", "apiKey", "header"));

        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(auth)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.twogether.backend.api.web.rest"))
                .build();
    }
}


