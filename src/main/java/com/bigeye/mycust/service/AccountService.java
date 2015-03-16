package com.bigeye.mycust.service;

import java.util.List;

import com.bigeye.mycust.model.Account;

public interface AccountService extends BaseService<Account, Long>{
	
	Account findByAccountIdentifier(String uuid);

	List<Account> findAccountByUserId(Long userId);

}
