package com.financieraOh.crudEmpleado_webcore.infraestructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("API de Mi Aplicación").version("1.0")
				.description("Documentación de la API para el demo de CRUD de Empeado"));
	}
}