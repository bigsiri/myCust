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
import com.bigeye.mycust.facade.UserFacade;
import com.bigeye.mycust.web.view.json.AccountJson;
import com.bigeye.mycust.web.view.json.UserJson;

@Controller
public final class HomeController extends BaseController {

	private static final String LOGIN_VIEW_NAME = ".login";
	private static final String HOME_VIEW_NAME = ".home";
	private static final String MENU_SECTION = "home";

	private static final Log LOG = LogFactory.getLog(HomeController.class);
	
	@Autowired
	UserFacade userFacade;
	
	@Autowired
	AccountFacade accountFacade;

	@RequestMapping(value = "/home.html", method = RequestMethod.GET)
	public String home(ModelMap model, HttpServletRequest request) {
		if (LOG.isInfoEnabled()) {
			LOG.info("home");
		}
		
		List<UserJson> users = userFacade.getList();
		model.put("users", users);
		
		List<AccountJson> accounts = accountFacade.getList();
		model.put("accounts", accounts);
		
		model.put("menuSectionName", MENU_SECTION);

		return HOME_VIEW_NAME;
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String login(ModelMap model, HttpServletRequest request) {
		if (LOG.isInfoEnabled()) {
			LOG.info("login");
		}
		return LOGIN_VIEW_NAME;
	}

}