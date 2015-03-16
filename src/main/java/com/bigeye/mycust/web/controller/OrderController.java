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

import com.bigeye.mycust.facade.UserFacade;
import com.bigeye.mycust.web.view.json.UserJson;

@Controller
@RequestMapping(value = "/orders")
public final class OrderController extends BaseController {

	private static final String USERS_LIST_VIEW_NAME = ".usersList";

	private static final Log LOG = LogFactory.getLog(OrderController.class);
	

	
	@Autowired
	UserFacade userFacade;

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String home(ModelMap model, HttpServletRequest request) {
		if (LOG.isInfoEnabled()) {
			LOG.info("Users list");
		}
		
		List<UserJson> users = userFacade.getList();
		
		model.put("users", users);

		return USERS_LIST_VIEW_NAME;
	}

}