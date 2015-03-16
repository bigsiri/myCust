package com.bigeye.mycust.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bigeye.mycust.model.user.User;

public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
	@Query("Select u from User u where u.uuid = :login or u.username = :login or u.openId = :login ")
	User findUserByUuidOrUsernameOrOpenId(@Param("login")String login);

	User findUserByOpenId(String openId);

	List<User> findUserByAccountsId(Long userId);

}
