package com.bigeye.mycust.appdirect.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Order {
	private String editionCode;
	private String pricingDuration;
	
	@XmlElement(name="item")
	private List<Item> items;

	public String getEditionCode() {
		return editionCode;
	}

	public void setEditionCode(String editionCode) {
		this.editionCode = editionCode;
	}

	public String getPricingDuration() {
		return pricingDuration;
	}

	public void setPricingDuration(String pricingDuration) {
		this.pricingDuration = pricingDuration;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	
}
