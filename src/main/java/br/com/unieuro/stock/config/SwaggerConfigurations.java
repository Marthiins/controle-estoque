package br.com.unieuro.stock.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfigurations {
	
	/* @Bean
	  //  public GroupedOpenApi publicApi() { // Build

	        return GroupedOpenApi.builder()
	                .group("Control-in-stock")
	                .pathsToMatch("/**")
	                .build();

	    } */
	
	
	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
		.info(new Info()
				.title("Control-in-stock ")
				.version("v1")
				.description("Documentation of control-in-stock Service API")
				.termsOfService("")
				.license(new License().name("Spring Doc")
						.url("http://springdoc.org")));
		
		/*.components(new Components().addSecuritySchemes("bearer-key", new SecurityScheme().type(SecurityScheme.Type.HTTP)
               .scheme("bearer")
               .bearerFormat("JWT")))
        .externalDocs(new ExternalDocumentation()); */
		
	}

}

	

