package com.bigeye.mycust.web.controller.ws;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bigeye.mycust.facade.UserFacade;
import com.bigeye.mycust.web.controller.BaseController;
import com.bigeye.mycust.web.view.json.TableDataJson;
import com.bigeye.mycust.web.view.json.UserJson;

@RestController
@RequestMapping(value = "/ws/secure/user")
public class UserWsController extends BaseController {
	
	@Autowired
	UserFacade userFacade;

	@RequestMapping(value = { "/account/{accountId}"}, produces = {"application/json"})
	public TableDataJson<UserJson> findAccountByUserId(@PathVariable Long accountId, HttpServletRequest request) throws IOException {
		
		List<UserJson> accounts = userFacade.findUserByAccountId(accountId);
		
		TableDataJson<UserJson> table = new TableDataJson<UserJson>();
		table.setData(accounts);
		
		return table;
	}
}
