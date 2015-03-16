package com.bigeye.mycust.facade.impl;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bigeye.mycust.dozer.beans.ListAccounts;
import com.bigeye.mycust.dozer.beans.ListAccountsJson;
import com.bigeye.mycust.facade.AccountFacade;
import com.bigeye.mycust.service.AccountService;
import com.bigeye.mycust.web.view.json.AccountJson;

@Component
public class AccountFacadeImpl implements AccountFacade{
	@Autowired
	AccountService accountService;
	
	@Autowired
	DozerBeanMapper dozerBeanMapper;

	@Override
	public List<AccountJson> getList() {
		ListAccounts listAccounts = new ListAccounts();
		listAccounts.setData(accountService.getList());
		ListAccountsJson listUsersJson = dozerBeanMapper.map(listAccounts, ListAccountsJson.class);
		return listUsersJson.getData();
	}

	@Override
	public List<AccountJson> findAccountByUserId(Long userId) {
		ListAccounts listAccounts = new ListAccounts();
		listAccounts.setData(accountService.findAccountByUserId(userId));
		ListAccountsJson listUsersJson = dozerBeanMapper.map(listAccounts, ListAccountsJson.class);
		return listUsersJson.getData();
	}
	
	
}
