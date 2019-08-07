package com.learning.api.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
	
	
	
	  @Autowired 
	  private AccountRepository accountRepository;
	  @Autowired 
	  private HelloRepository helloRepository;
	 /* 
	 * @PostMapping("/submitReview") public String
	 * addReview(@ModelAttribute("review") Review review, Model model) { //
	 * repository.save(review); //model.addAttribute("status", "Hi " +
	 * review.getName() + " your Review Submitted successfully"); return "home"; }
	 *
	 * 
	 * */
	  @GetMapping("/getAccounts") public String getAccounts(Model model) {
	 model.addAttribute("account", accountRepository.getAllAccounts());
	 return "account"; }
	
	  @GetMapping("/getHello") public String getHello(Model model) {
		  String result =""+ helloRepository.getHello();
		  model.addAttribute("results",result);
			
		  return "hello";
	  }
}
