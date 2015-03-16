package com.bigeye.mycust.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bigeye.mycust.model.Order;

public interface OrderDao extends JpaRepository<Order, Long> {


}
