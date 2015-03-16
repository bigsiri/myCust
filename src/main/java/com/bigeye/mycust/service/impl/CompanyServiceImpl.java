package com.bigeye.mycust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigeye.mycust.dao.CompanyDao;
import com.bigeye.mycust.model.Company;
import com.bigeye.mycust.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	CompanyDao companyDao;

	@Override
	public Company load(Long id) {
		return companyDao.findOne(id);
	}

	@Override
	public void saveOrUpdate(Company entity) {
		companyDao.save(entity);
	}

	@Override
	public void delete(Company entity) {
		companyDao.delete(entity);
	}

	@Override
	public List<Company> getList() {
		return companyDao.findAll();
	}

	@Override
	public Company findByUuid(String uuid) {
		return companyDao.findByUuid(uuid);
	}

	
	
}
