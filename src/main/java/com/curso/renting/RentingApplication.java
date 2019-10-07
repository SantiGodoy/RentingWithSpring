package com.curso.renting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RentingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentingApplication.class, args);
	}
	
	@Bean
    public Docket produceApi(){
	    return new Docket(DocumentationType.SWAGGER_2)
		    .apiInfo(apiInfo())
		    .select()
		    .apis(RequestHandlerSelectors.basePackage("com.curso.renting.controller"))
		    .paths(PathSelectors.any())
		    .build();
	}

	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	    .title("Rest API - Car renting with Spring Boot")
	    .description("Santiago Godoy Poce")
	    .build();
	}
}
