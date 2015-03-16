package com.bigeye.mycust.web.controller.ws;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bigeye.mycust.facade.AccountFacade;
import com.bigeye.mycust.web.controller.BaseController;
import com.bigeye.mycust.web.view.json.AccountJson;
import com.bigeye.mycust.web.view.json.TableDataJson;

@RestController
@RequestMapping(value = "/ws/secure/account")
public class AccountWsController extends BaseController {
	
	@Autowired
	AccountFacade accountFacade;

	@RequestMapping(value = { "/user/{userId}"}, produces = {"application/json"})
	public TableDataJson<AccountJson> findAccountByUserId(@PathVariable Long userId, HttpServletRequest request) throws IOException {
		
		List<AccountJson> accounts = accountFacade.findAccountByUserId(userId);
		
		TableDataJson<AccountJson> table = new TableDataJson<AccountJson>();
		table.setData(accounts);
		
		return table;
	}
}
