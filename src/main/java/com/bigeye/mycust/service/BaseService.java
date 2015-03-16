package com.bigeye.mycust.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface BaseService<BaseEntity, KeyType> {

	BaseEntity load(Long id);

	@Transactional
	void saveOrUpdate(BaseEntity entity);

	@Transactional
	void delete(BaseEntity entity);

	List<BaseEntity> getList();

}
