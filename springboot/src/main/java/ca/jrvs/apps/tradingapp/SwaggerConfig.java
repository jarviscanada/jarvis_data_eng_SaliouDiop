package ca.jrvs.apps.tradingapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.function.Predicate;

import static springfox.documentation.builders.PathSelectors.any;
@Configuration
@EnableSwagger2

public class SwaggerConfig {
    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(Predicate.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .paths(any())
                .build()
                .pathMapping("/")
                .apiInfo(apiInfo());
        return docket;

    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Jae", "www.jrvs.ca", "diopsaliou45@gmail.com");
        return new ApiInfo("Trading App", "Trading App", "1.0", "", contact, "", "", new ArrayList<>());
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.MODEL)
                .displayRequestDuration(true)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.METHOD)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }
}
