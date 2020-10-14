package com.inventory.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * This class used to create data source object for BackOffice swagger-ui related
 * properties.
 * 
 * @author kavin p
 */

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Inventory Management Micro Service")
        		.apiInfo(apiInfo()).select()
               .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
               .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.cloud")))
               .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.data.rest.webmvc")))
               .build();
    }

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Inventory Management Micro Service")
				.description(
				"Inventory Management REST API's micro service developed in spring boot. "
				+"This is the centralized micro service. Objective of this application is to "
				+"expose end points for all inventory operations.")
				.contact(new Contact("kavin p", "", "kavinperiyasamy24@gmail.com"))
				.license("Apache License Version 2.0").version("1.1.0").build();
	}

}
