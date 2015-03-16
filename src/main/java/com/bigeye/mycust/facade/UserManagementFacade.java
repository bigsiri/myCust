package com.bigeye.mycust.facade;

import com.bigeye.mycust.appdirect.beans.EventResult;


public interface UserManagementFacade {

	EventResult assign(String token);
	
	EventResult unassign(String token);
	
}
