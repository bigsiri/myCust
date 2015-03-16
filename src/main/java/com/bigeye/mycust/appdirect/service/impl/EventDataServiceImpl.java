package com.bigeye.mycust.appdirect.service.impl;

import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Service;

import com.bigeye.mycust.appdirect.beans.Event;
import com.bigeye.mycust.appdirect.enums.EventResultErrorMessageEnum;
import com.bigeye.mycust.appdirect.exception.EventResultException;
import com.bigeye.mycust.appdirect.service.EventDataService;

@Service("eventDataService")
public class EventDataServiceImpl implements EventDataService
{
	private OAuthRestTemplate restTemplate;

	@Override
	public Event fetchEvent(String eventUrl)
	{
		try
		{
			return restTemplate.getForEntity(eventUrl, Event.class).getBody();
		}
		catch(Throwable e)
		{
			throw new EventResultException(EventResultErrorMessageEnum.INVALID_RESPONSE, "Error fetching event. URL = " + eventUrl);
		}
	}
	
	public OAuthRestTemplate getRestTemplate()
	{
		return restTemplate;
	}

	public void setRestTemplate(OAuthRestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}
}
