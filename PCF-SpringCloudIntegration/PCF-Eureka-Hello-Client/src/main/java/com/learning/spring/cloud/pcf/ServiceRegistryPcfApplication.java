package com.learning.spring.cloud.pcf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@RestController
@EnableDiscoveryClient
public class ServiceRegistryPcfApplication {

	@GetMapping("/hello")
	public String getMessage() {
		return "Hello microservice : you app is running fine.";
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryPcfApplication.class, args);
	}

}
