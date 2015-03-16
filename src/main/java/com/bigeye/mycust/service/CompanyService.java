package com.bigeye.mycust.service;

import com.bigeye.mycust.model.Company;

public interface CompanyService extends BaseService<Company, Long>{
	
	Company findByUuid(String uuid);

}
