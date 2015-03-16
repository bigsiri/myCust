package com.bigeye.mycust.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigeye.mycust.dao.PingDao;

@Service
public final class PingServiceImpl implements PingService {

	@Autowired
	private PingDao pingDao;

	@Override
	public Date getCurrentDate() {
		return pingDao.getCurrentDate();
	}
	
}
