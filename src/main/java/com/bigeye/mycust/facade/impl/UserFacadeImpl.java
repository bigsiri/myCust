package com.bigeye.mycust.facade.impl;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bigeye.mycust.dozer.beans.ListUsers;
import com.bigeye.mycust.dozer.beans.ListUsersJson;
import com.bigeye.mycust.facade.UserFacade;
import com.bigeye.mycust.service.UserService;
import com.bigeye.mycust.web.view.json.UserJson;

@Component
public class UserFacadeImpl implements UserFacade {
	
	@Autowired
	UserService userService;
	
	@Autowired
	DozerBeanMapper dozerBeanMapper;
	
	@Override
	public List<UserJson> getList() {
		ListUsers listUsers = new ListUsers();
		listUsers.setData(userService.getList());
		ListUsersJson listUsersJson = dozerBeanMapper.map(listUsers, ListUsersJson.class);
		return listUsersJson.getData();
	}

	@Override
	public List<UserJson> findUserByAccountId(Long userId) {
		ListUsers listUsers = new ListUsers();
		listUsers.setData(userService.findUserByAccountId(userId));
		ListUsersJson listUsersJson = dozerBeanMapper.map(listUsers, ListUsersJson.class);
		return listUsersJson.getData();
	}
	

}
