package com.bigeye.mycust.facade;

import java.util.List;

import com.bigeye.mycust.web.view.json.UserJson;

public interface UserFacade {
	List<UserJson> getList();

	List<UserJson> findUserByAccountId(Long userId);
}
