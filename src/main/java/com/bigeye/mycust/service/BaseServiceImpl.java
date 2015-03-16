package com.bigeye.mycust.service;

import java.io.Serializable;
import java.util.List;

import com.bigeye.mycust.model.BaseEntity;

public abstract class BaseServiceImpl<T extends BaseEntity, K extends Serializable> implements BaseService<T, K> {

	public abstract T load(K id);

	@Override
	public abstract void saveOrUpdate(T t);

	@Override
	public abstract void delete(T t);

	@Override
	public abstract List<T> getList();

}
