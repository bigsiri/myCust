package com.bigeye.mycust.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bigeye.mycust.facade.AccountFacade;
import com.bigeye.mycust.web.view.json.AccountJson;

@Controller
@RequestMapping(value = "/account")
public final class AccountController extends BaseController {

	private static final String USERS_LIST_VIEW_NAME = ".usersList";

	private static final Log LOG = LogFactory.getLog(AccountController.class);
	
	@Autowired
	AccountFacade accountFacade;

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String home(ModelMap model, HttpServletRequest request) {
		if (LOG.isInfoEnabled()) {
			LOG.info("Users list");
		}
		
		List<AccountJson> users = accountFacade.getList();
		
		model.put("users", users);

		return USERS_LIST_VIEW_NAME;
	}

}