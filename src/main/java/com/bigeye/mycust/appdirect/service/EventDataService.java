package com.bigeye.mycust.appdirect.service;

import com.bigeye.mycust.appdirect.beans.Event;

public interface EventDataService
{
	public Event fetchEvent(String eventUrl);

}
