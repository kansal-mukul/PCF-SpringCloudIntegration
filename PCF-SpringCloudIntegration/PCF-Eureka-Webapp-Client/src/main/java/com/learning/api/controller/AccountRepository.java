package com.learning.api.controller;

import java.util.List;

import com.learning.api.model.Account;


public interface AccountRepository {
	
	List<Account> getAllAccounts();
	

}