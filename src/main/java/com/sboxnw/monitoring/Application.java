package com.sboxnw.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Copyright (C) Margo Networks Private Limited - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Tejas Gowda <tejas.gowda@sugarboxnetworks.com>, March 2018.
 */

@EnableSwagger2
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class Application {
    public static void main(String[] args) throws Exception{
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public Docket ImsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sboxnw.monitoring.controller"))
                .build()
                .apiInfo(metaData()).ignoredParameterTypes(Pageable.class);
    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Monitoring Service API",
                "Monitoring Service will collate all activity and used as a real time update.",
                "1.0.0",
                "http://margocdn.com/terms/",
                new Contact("", "", "apiteam@margocdn.com"),
                "Margonetworks Copyright",
                "http://margocdn.com/licenses/LICENSE-2.0.html");

        return apiInfo;
    }
}

