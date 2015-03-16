package com.bigeye.mycust.appdirect.beans;

public class Event {
	
	private String type;
	
	private MarketPlace marketPlace;
	
	private User creator;
	
	private Payload payload;
	
	private String returnUrl;
	
	private String flag;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MarketPlace getMarketPlace() {
		return marketPlace;
	}

	public void setMarketPlace(MarketPlace marketPlace) {
		this.marketPlace = marketPlace;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	
	
}
