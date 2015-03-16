package com.bigeye.mycust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bigeye.mycust.dao.AccountDao;
import com.bigeye.mycust.model.Account;
import com.bigeye.mycust.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDao accountDao;

	@Override
	public Account load(Long id) {
		return accountDao.findOne(id);
	}

	@Override
	public void saveOrUpdate(Account entity) {
		accountDao.save(entity);
	}

	@Override
	public void delete(Account entity) {
		accountDao.delete(entity);
	}

	@Override
	public List<Account> getList() {
		return accountDao.findAll();
	}

	@Override
	public Account findByAccountIdentifier(String uuid) {
		return accountDao.findByAccountIdentifier(uuid);
	}

	@Override
	public List<Account> findAccountByUserId(Long userId) {
		return accountDao.findAccountByUsersId(userId);
	}

	
	
}
