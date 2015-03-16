package com.bigeye.mycust.facade;

import java.util.List;

import com.bigeye.mycust.web.view.json.AccountJson;

public interface AccountFacade {

	List<AccountJson> getList();

	List<AccountJson> findAccountByUserId(Long userId);

}
