package com.learning.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.learning.api.model.Account;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


public class RemoteAccountRepository implements AccountRepository {
 
 @Autowired
 protected RestTemplate restTemplate;
 
	
	  protected String serviceUrl;
	  
	  public RemoteAccountRepository(String serviceUrl) { this.serviceUrl =
	  serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl; }
	 
 


 @Override
 @HystrixCommand(fallbackMethod = "getAllAccounts_FallBack")
 public List<Account> getAllAccounts() {
  Account[] accounts = restTemplate.getForObject(serviceUrl+"/accounts", Account[].class);
  System.out.println("accounts============>"+accounts.length);
  return Arrays.asList(accounts);
 }
 
 @SuppressWarnings("unused")
 private List<Account> getAllAccounts_FallBack() {

     System.out.println("Accounts Service is down!!! fallback route enabled...");

     List<Account> accounts = new ArrayList<Account>();
     return accounts;
    // return "CIRCUIT BREAKER ENABLED!!! No Response From Accounts Service at this moment. " +
     //            " Service will be back shortly - " + new Date();
 }
 
	/*
	 * @Override public Account getAccount(String number) { return
	 * restTemplate.getForObject(serviceUrl + "/accounts/{id}", Account.class,
	 * number); }
	 */

}