package com.bigeye.mycust.facade.impl;

import java.util.UUID;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bigeye.mycust.appdirect.beans.Event;
import com.bigeye.mycust.appdirect.beans.EventResult;
import com.bigeye.mycust.appdirect.enums.EventResultErrorMessageEnum;
import com.bigeye.mycust.appdirect.enums.EventResultMessageEnum;
import com.bigeye.mycust.appdirect.enums.EventTypeEnum;
import com.bigeye.mycust.appdirect.enums.account.AccountStatusEnum;
import com.bigeye.mycust.appdirect.exception.EventResultException;
import com.bigeye.mycust.appdirect.service.EventDataService;
import com.bigeye.mycust.appdirect.service.SubscriptionService;
import com.bigeye.mycust.facade.SubscriptionFacade;
import com.bigeye.mycust.model.Account;
import com.bigeye.mycust.model.Company;
import com.bigeye.mycust.model.user.User;
import com.bigeye.mycust.service.AccountService;
import com.bigeye.mycust.service.CompanyService;
import com.bigeye.mycust.service.UserService;

@Component
public class SubscriptionFacadeImpl implements SubscriptionFacade {
	@Autowired
	SubscriptionService subscriptionService;

	@Autowired
	UserService userService;

	@Autowired
	CompanyService companyService;

	@Autowired
	DozerBeanMapper dozerBeanMapper;

	@Autowired
	EventDataService eventDataExtractor;

	@Autowired
	AccountService accountService;
	
	@Override
	public EventResult orderSubscription(String eventUrl) {
		try
		{
			Event event = eventDataExtractor.fetchEvent(eventUrl);
			
			String accountIdentifier = "dummy-account";
			EventResult eventResult = EventResult.success(accountIdentifier,EventResultMessageEnum.SUBSCRIPTION_ORDER_OK.getValue());
			//TEST PURPOSE
			if (!event.getFlag().equals(EventTypeEnum.STATELESS.toString())) {
	
				User user = dozerBeanMapper.map(event.getCreator(), User.class);
				User userTmp = userService.loadUserByOpenId(user.getOpenId()) ;
				if (userTmp != null) {
					user.setId(userTmp.getId());
					if (user.getCompany() != null && user.getCompany().getUuid() != null) {
						Company company = companyService.findByUuid(user.getCompany().getUuid());
						user.setCompany(company);
					}
	
				}
				user.setRoleManager(true);
				userService.saveOrUpdate(user);
	
				Account account = dozerBeanMapper.map(event.getPayload(),
						Account.class);
				account.setStatus(AccountStatusEnum.ACTIVE.toString());
				accountIdentifier = UUID.randomUUID().toString();
				account.setAccountIdentifier(accountIdentifier);
				accountService.saveOrUpdate(account);
				
				userService.assign(user, accountIdentifier);
				
				eventResult = EventResult.success(accountIdentifier,EventResultMessageEnum.SUBSCRIPTION_ORDER_OK.getValue());
			}
			
			return eventResult;
			
		}
		catch(EventResultException e)
		{
			throw e;
			
		}
		catch(Throwable e)
		{
			throw new EventResultException(EventResultErrorMessageEnum.UNKNOWN_ERROR,e.getMessage());
		}
		
	}

	@Override
	public EventResult changeSubscription(String eventUrl) {
		
		try
		{
		
			Event event = eventDataExtractor.fetchEvent(eventUrl);
			String accountIdentifier = event.getPayload().getAccount().getAccountIdentifier();
			
			EventResult eventResult = EventResult.success(accountIdentifier,EventResultMessageEnum.SUBSCRIPTION_CHANGE_OK.getValue());
			
			//TEST PURPOSE
			if (!event.getFlag().equals(EventTypeEnum.STATELESS.toString())) {
	
				Account account = dozerBeanMapper.map(event.getPayload(), Account.class);
				Account accountToSave = accountService.findByAccountIdentifier(accountIdentifier);
				
				//SET NEW ORDER
				accountToSave.setOrder(account.getOrder());
				
				accountService.saveOrUpdate(accountToSave);
			}
			return eventResult;
		
		}
		catch(Throwable e)
		{
			throw new EventResultException(EventResultErrorMessageEnum.UNKNOWN_ERROR,e.getMessage());
		}
	}

	@Override
	public EventResult cancelSubscription(String eventUrl) {
		try
		{
			Event event = eventDataExtractor.fetchEvent(eventUrl);
			EventResult eventResult = EventResult.success(Boolean.TRUE);
			
	  		if (!event.getFlag().equals(EventTypeEnum.STATELESS.toString())) {
				Account account = accountService.findByAccountIdentifier(event.getPayload().getAccount().getAccountIdentifier());
				account.setStatus(AccountStatusEnum.CANCELLED.toString());
	
				accountService.saveOrUpdate(account);
			}
			return eventResult;
		
		}
		catch(Throwable e)
		{
			throw new EventResultException(EventResultErrorMessageEnum.UNKNOWN_ERROR,e.getMessage());
		}
	}

	@Override
	public EventResult noticeSubscription(String eventUrl) {
		try
		{
			Event event = eventDataExtractor.fetchEvent(eventUrl);
			EventResult eventResult = EventResult.success(Boolean.TRUE);
			
			if (!event.getFlag().equals(EventTypeEnum.STATELESS.toString())) {
				Account account = accountService.findByAccountIdentifier(event.getPayload().getAccount().getAccountIdentifier());
				
				if(account == null){
					throw new EventResultException(EventResultErrorMessageEnum.ACCOUNT_NOT_FOUND, "The account is not found. Unassignment can't be done.");
				}
				
				String status = event.getPayload().getAccount().getStatus();
				account.setStatus(status);
	
				accountService.saveOrUpdate(account);
			}
			return eventResult;
		
		}
		catch(EventResultException e)
		{
			throw e;
		}
		catch(Throwable e)
		{
			throw new EventResultException(EventResultErrorMessageEnum.UNKNOWN_ERROR,e.getMessage());
		}
	}

}
