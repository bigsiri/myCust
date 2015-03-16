package com.bigeye.mycust.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class AdminBaseController extends BaseController {

	protected abstract String getMenuSectionName();

	@Override
	@ModelAttribute
	public void init(ModelMap model, HttpServletRequest request) {
		super.init(model, request);

		model.put("menuSectionName", getMenuSectionName());
	}
}
