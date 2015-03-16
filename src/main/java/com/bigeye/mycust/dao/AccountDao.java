package com.bigeye.mycust.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bigeye.mycust.model.Account;


public interface AccountDao extends JpaRepository<Account, Long> {

	Account findByAccountIdentifier(String uuid);

	List<Account> findAccountByUsersId(Long userId);
	
}
