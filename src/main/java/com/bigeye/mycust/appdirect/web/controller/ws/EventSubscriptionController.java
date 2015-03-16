package com.bigeye.mycust.appdirect.web.controller.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bigeye.mycust.appdirect.beans.EventResult;
import com.bigeye.mycust.appdirect.enums.EventResultErrorMessageEnum;
import com.bigeye.mycust.appdirect.exception.EventResultException;
import com.bigeye.mycust.appdirect.service.EventService;
import com.bigeye.mycust.facade.SubscriptionFacade;

@RestController
@RequestMapping(value ="/api/v1/appDirect/subscription/")
public class EventSubscriptionController {
	
	@Autowired
	EventService eventService;
	
	@Autowired
	SubscriptionFacade subscriptionFacade;
	
	@RequestMapping(value = "order", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody EventResult orderSubscription(@RequestParam String token) {
		return subscriptionFacade.orderSubscription(token);
		
	}
	
	@RequestMapping(value = "change", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody EventResult changeSubscription(@RequestParam String token) {
		return subscriptionFacade.changeSubscription(token);
		
	}
	
	@RequestMapping(value = "cancel", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody EventResult cancelSubscription(@RequestParam String token) {
		return subscriptionFacade.cancelSubscription(token);
		
	}
	
	@RequestMapping(value = "notice", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody EventResult noticeSubscription(@RequestParam String token) {
		return subscriptionFacade.noticeSubscription(token);
		
	}
	
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(EventResultException.class)
	public EventResult handleEventResultException(EventResultException ex) {
 
		return EventResult.error(ex.getError(), ex.getMessage());
 
	}
 
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(Exception.class)
	public EventResult handleAllException(Exception ex) {
 
		return EventResult.error(EventResultErrorMessageEnum.UNKNOWN_ERROR, ex.getMessage());
 
	}
}
