package com.bigeye.mycust.facade.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bigeye.mycust.appdirect.beans.Event;
import com.bigeye.mycust.appdirect.beans.EventResult;
import com.bigeye.mycust.appdirect.enums.EventResultErrorMessageEnum;
import com.bigeye.mycust.appdirect.enums.EventTypeEnum;
import com.bigeye.mycust.appdirect.exception.EventResultException;
import com.bigeye.mycust.appdirect.service.EventDataService;
import com.bigeye.mycust.facade.UserManagementFacade;
import com.bigeye.mycust.model.Account;
import com.bigeye.mycust.model.Company;
import com.bigeye.mycust.model.user.User;
import com.bigeye.mycust.service.AccountService;
import com.bigeye.mycust.service.CompanyService;
import com.bigeye.mycust.service.UserService;

@Component
public class UserManagementFacadeImpl implements UserManagementFacade {

	@Autowired
	AccountService accountService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	EventDataService eventDataExtractor;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	DozerBeanMapper dozerBeanMapper;
	
	@Override
	public EventResult assign(String eventUrl) {
		Event event = eventDataExtractor.fetchEvent(eventUrl);
		EventResult eventResult = EventResult.success(Boolean.TRUE);
		try
		{
			//TEST PURPOSE
			if (!event.getFlag().equals(EventTypeEnum.STATELESS.toString())) {
				String accountIdentifier = event.getPayload().getAccount().getAccountIdentifier();
				
				Account account = accountService.findByAccountIdentifier(accountIdentifier);
				if(account == null){
					throw new EventResultException(EventResultErrorMessageEnum.ACCOUNT_NOT_FOUND, "The account is not found. Unassignment can't be done.");
				}
				
				
				User user = dozerBeanMapper.map(event.getPayload().getUser(), User.class);
				User userTmp = userService.loadUserByOpenId(user.getOpenId()) ;
				if (userTmp != null) {
					user.setId(userTmp.getId());
					
					if (user.getCompany() != null && user.getCompany().getUuid() != null) {
						Company company = companyService.findByUuid(user.getCompany().getUuid());
						user.setCompany(company);
					}
				}
				
				userService.assign(user, accountIdentifier);
			}
			
			return eventResult;
		}
		catch(Throwable e)
		{
			throw new EventResultException(EventResultErrorMessageEnum.UNKNOWN_ERROR,e.getMessage());
		}
	}

	@Override
	public EventResult unassign(String eventUrl) {
		
		try
		{
			Event event = eventDataExtractor.fetchEvent(eventUrl);
			EventResult eventResult = EventResult.success(Boolean.TRUE);
			
	
			//TEST PURPOSE
			if (!event.getFlag().equals(EventTypeEnum.STATELESS.toString())) {
				String accountIdentifier = event.getPayload().getAccount()
						.getAccountIdentifier();
				
				Account account = accountService.findByAccountIdentifier(accountIdentifier);
				if(account == null){
					throw new EventResultException(EventResultErrorMessageEnum.ACCOUNT_NOT_FOUND, "The account is not found. Unassignment can't be done.");
				}
				
				
				User user = dozerBeanMapper.map(event.getPayload().getUser(), User.class);
				User userTmp = userService.loadUserByOpenId(user.getOpenId()) ;
				if (userTmp != null) {
					user.setId(userTmp.getId());
					
					if (user.getCompany() != null && user.getCompany().getUuid() != null) {
						Company company = companyService.findByUuid(user.getCompany().getUuid());
						user.setCompany(company);
					}
					
					userService.unassign(user, accountIdentifier);
				}else{
					throw new EventResultException(EventResultErrorMessageEnum.USER_NOT_FOUND, "The user is not found. Unassignment can't be done.");
				}
				
				
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
