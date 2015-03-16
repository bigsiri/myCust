package com.bigeye.mycust.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bigeye.mycust.model.Company;

public interface CompanyDao extends JpaRepository<Company, Long> {
	Company findByUuid(String uuid);
}
