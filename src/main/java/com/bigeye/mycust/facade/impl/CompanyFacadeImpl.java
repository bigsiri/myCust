package com.bigeye.mycust.facade.impl;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bigeye.mycust.dozer.beans.ListCompany;
import com.bigeye.mycust.dozer.beans.ListCompanyJson;
import com.bigeye.mycust.facade.CompanyFacade;
import com.bigeye.mycust.service.CompanyService;
import com.bigeye.mycust.web.view.json.CompanyJson;
@Component
public class CompanyFacadeImpl implements CompanyFacade {

	@Autowired
	CompanyService companyService;
	
	@Autowired
	DozerBeanMapper dozerBeanMapper;
	
	@Override
	public List<CompanyJson> getList() {
		ListCompany listCompany = new ListCompany();
		listCompany.setData(companyService.getList());
		ListCompanyJson listCompanyJson = dozerBeanMapper.map(listCompany, ListCompanyJson.class);
		return listCompanyJson.getData();
	}

}
