package com.task.catalogMicroservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@OpenAPIDefinition(info = @Info(title = "Product Catalog", description = "Available Products"))
public class CatalogMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogMicroserviceApplication.class, args);
	}

}
