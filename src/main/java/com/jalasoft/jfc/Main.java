/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 *  Run Sprint Boot to Web Application
 *
 * @version 0.1 13 Dic 2019
 *
 * @author Enrique Carrizales
 */
@SpringBootApplication
@EnableSwagger2
public class Main {

    /**
     * Main method executes SprintApplication
     * @param args receives String array
     */
    public static void main(String[] args) {
        System.out.println("Welcome to JFC");
        SpringApplication.run(Main.class, args);
    }

    /**
     * Allows to configure swagger.
     * @return swagger configuration.
     */
    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/api/*"))
                .apis(RequestHandlerSelectors.basePackage("com.jalasoft.jfc")).build().apiInfo(apiDetails());
    }

    /**
     * Generates details of ApiInfo.
     * @return ApiInfo description.
     */
    private ApiInfo apiDetails() {
        return new ApiInfo("JFC Converter API", "Prog-102", "0.1",
                "Free to use", new Contact("AT-11", "http://fundacion-jala.org",
                "at-11@fundacion-jala.org"), "API license", "http://fundacion-jala.org",
                Collections.emptyList());
    }
}
