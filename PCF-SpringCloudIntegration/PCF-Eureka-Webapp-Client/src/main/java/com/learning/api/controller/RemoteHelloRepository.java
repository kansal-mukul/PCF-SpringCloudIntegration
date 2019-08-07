package com.learning.api.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


public class RemoteHelloRepository implements HelloRepository {
 
 @Autowired
 protected RestTemplate restTemplate;
 
	
	  protected String serviceUrl;
	  
	  public RemoteHelloRepository(String serviceUrl) { this.serviceUrl =
	  serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl; }
	 

	  @Override
	  @HystrixCommand(fallbackMethod = "getHello_FallBack")
	  public String getHello() {
	  String result = restTemplate.getForObject(serviceUrl+"/hello", String.class);
	   return result;
	  }
	  
	  @SuppressWarnings("unused")
	  private String getHello_FallBack() {

	      System.out.println("Hello Service is down!!! fallback route enabled...");

	      return "CIRCUIT BREAKER ENABLED!!! No Response From Hello Service at this moment. " +
	                  " Service will be back shortly - " + new Date();
	  }


}