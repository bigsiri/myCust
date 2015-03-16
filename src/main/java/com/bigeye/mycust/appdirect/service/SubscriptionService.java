package com.bigeye.mycust.appdirect.service;

import com.bigeye.mycust.appdirect.beans.Event;
import com.bigeye.mycust.appdirect.beans.EventResult;

public interface SubscriptionService {
	EventResult orderSubscription(Event event);
}
