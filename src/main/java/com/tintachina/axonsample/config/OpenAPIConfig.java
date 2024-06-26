package com.tintachina.axonsample.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

  @Bean
  OpenAPI openAPI() {
    return new OpenAPI()
        .info(new Info().title("Axon framework API")
            .description("Axon Sample application")
            .version("v0.0.1")
            .license(new License().name("Apache 2.0").url("http://springdoc.org")))
        .externalDocs(new ExternalDocumentation()
            .description("About Application")
            .url("https://tintachina84.github.io/axon-sample"));
  }
}
