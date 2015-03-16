package com.bigeye.mycust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigeye.mycust.dao.OrderDao;
import com.bigeye.mycust.model.Order;
import com.bigeye.mycust.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;
	
	@Override
	public Order load(Long id) {
		return orderDao.findOne(id);
	}

	@Override
	public void saveOrUpdate(Order entity) {
		orderDao.save(entity);
	}

	@Override
	public void delete(Order entity) {
		orderDao.delete(entity);
	}

	@Override
	public List<Order> getList() {
		return orderDao.findAll();
	}

}
