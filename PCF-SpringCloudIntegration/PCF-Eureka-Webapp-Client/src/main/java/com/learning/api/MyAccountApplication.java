package com.learning.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.learning.api.controller.AccountRepository;
import com.learning.api.controller.HelloRepository;
import com.learning.api.controller.RemoteAccountRepository;
import com.learning.api.controller.RemoteHelloRepository;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@EnableDiscoveryClient
@EnableCircuitBreaker
public class MyAccountApplication implements WebMvcConfigurer {

	 public static final String ACCOUNTS_SERVICE_URL = "http://PCF-Eureka-Accounts-Backend-Client";
		
	 public static final String HELLO_SERVICE_URL = "http://PCF-Eureka-Hello-Client";
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
	//	registry.addViewController("/addReview").setViewName("review");
	}
	public static void main(String[] args) {
		SpringApplication.run(MyAccountApplication.class, args);
	}
	
	@Bean
	 @LoadBalanced
	 public RestTemplate restTemplate() {
	  return new RestTemplate();
	 }
	
	  @Bean 
	  public AccountRepository accountRepository(){ return new
	  RemoteAccountRepository(ACCOUNTS_SERVICE_URL); }
	 
	  @Bean 
	  public HelloRepository homeRepository(){ return new
	  RemoteHelloRepository(HELLO_SERVICE_URL); }
	 

}
