//package com.medialog.angularjs.config.apidoc;
//
//import com.medialog.angularjs.config.AngularJSProperties;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.StopWatch;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger.web.UiConfiguration;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Date;
//
//import static springfox.documentation.builders.PathSelectors.regex;
//
///**
// * Created by ASH on 2016-05-03.
// */
//@Slf4j
//@Configuration
//@EnableSwagger2
//public class SwaggerConfiguration {
//
//    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
//
//    @Bean
//    public Docket swaggerSpringfoxDocket(AngularJSProperties angularJSProperties) {
//        log.debug("Starting Swagger");
//        StopWatch watch = new StopWatch();
//        watch.start();
//        Contact contact = new Contact(
//                angularJSProperties.getSwagger().getContactName(),
//                angularJSProperties.getSwagger().getContactUrl(),
//                angularJSProperties.getSwagger().getContactEmail());
//
//        ApiInfo apiInfo = new ApiInfo(
//                angularJSProperties.getSwagger().getTitle(),
//                angularJSProperties.getSwagger().getDescription(),
//                angularJSProperties.getSwagger().getVersion(),
//                angularJSProperties.getSwagger().getTermsOfServiceUrl(),
//                contact,
//                angularJSProperties.getSwagger().getLicense(),
//                angularJSProperties.getSwagger().getLicenseUrl());
//
//        Docket docket = new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo)
//                .forCodeGeneration(true)
//                .genericModelSubstitutes(ResponseEntity.class)
//                .ignoredParameterTypes(Pageable.class)
//                .ignoredParameterTypes(java.sql.Date.class)
//                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
//                .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
//                .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
//                .select()
//                .paths(regex(DEFAULT_INCLUDE_PATTERN))
//                .build();
//        watch.stop();
//        log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
//        return docket;
//    }
//}
