package com.bigeye.mycust.service;

import java.util.List;
import java.util.Locale;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import com.bigeye.mycust.model.user.User;

public interface UserService extends BaseService<User, Long>, UserDetailsService {

	void resetUserPassword(User user);

	boolean forgotUserPassword(String email);

	void delete(Long id, String username);

	@Transactional
	boolean registerNewUser(String email, String firstname, String lastname, Locale locale);

	User loadEager(Long id);

	User findByLogin(String login);

	User loadUserByOpenId(String user);
	

	void assign(User user,String accountIdentifier);
	
	void unassign(User user,String accountIdentifier);

	List<User> findUserByAccountId(Long accountId);
	
	public User getSelf();
}
