package com.bigeye.mycust.facade;

import com.bigeye.mycust.appdirect.beans.EventResult;


public interface SubscriptionFacade {
	EventResult orderSubscription(String eventUrl);

	EventResult changeSubscription(String token);

	EventResult cancelSubscription(String token);

	EventResult noticeSubscription(String token);
}
