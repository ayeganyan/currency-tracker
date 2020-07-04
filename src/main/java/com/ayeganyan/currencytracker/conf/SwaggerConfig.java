package com.ayeganyan.currencytracker.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ayeganyan.currencytracker"))
                .build()
                .apiInfo(details());
    }

    private static ApiInfo details() {
        return new ApiInfo(
                "Communa Rest API",
                "Represents an application for Communa application",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact(
                        "Armen Yeganyan",
                        "https://www.linkedin.com/in/ayeganyan/",
                        "ayeganyan92@gmail.com"),
                "",
                "");
    }
}
