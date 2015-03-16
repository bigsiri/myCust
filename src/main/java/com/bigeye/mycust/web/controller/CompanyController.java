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

import com.bigeye.mycust.facade.CompanyFacade;
import com.bigeye.mycust.web.view.json.CompanyJson;

@Controller
@RequestMapping(value = "/company")
public class CompanyController extends BaseController{

	private static final String COMPANIES_LIST_VIEW_NAME = ".companiesList";
	private static final String MENU_SECTION = "companies";

	private static final Log LOG = LogFactory.getLog(CompanyController.class);
	
	@Autowired
	CompanyFacade companyFacade;

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String home(ModelMap model, HttpServletRequest request) {
		if (LOG.isInfoEnabled()) {
			LOG.info("Companies list");
		}
		
		List<CompanyJson> companies = companyFacade.getList();
		
		model.put("companies", companies);

		model.put("menuSectionName", MENU_SECTION);
		
		return COMPANIES_LIST_VIEW_NAME;
	}
}
