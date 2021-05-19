package com.springboot.atm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.springboot.atm"))
                .paths(PathSelectors.any())
                .build();
    }
    //generate apiInfo
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ATM-Cajero Automáticos API yyyyy")
                .description("Swagger2 build RESTful APIs")
                .version("1.0.0")
                .build();
    }
}

