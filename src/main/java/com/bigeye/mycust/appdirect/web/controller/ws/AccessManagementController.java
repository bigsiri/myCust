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
import com.bigeye.mycust.facade.UserManagementFacade;

@RestController
@RequestMapping(value ="/api/v1/appDirect/user/")
public class AccessManagementController {
	@Autowired
	UserManagementFacade userManagementFacade;
	
	
	@RequestMapping(value = "assign", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody EventResult assign(@RequestParam String token) {
		return userManagementFacade.assign(token);
		
	}
	
	@RequestMapping(value = "unassign", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody EventResult unassign(@RequestParam String token) {
		return userManagementFacade.unassign(token);
		
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
