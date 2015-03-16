package com.bigeye.mycust.web.view.json;

import java.util.List;

public class OrderJson extends BaseEntityJson{
	private static final long serialVersionUID = -1969033709732188732L;
	
	private String editionCode;
	private String pricingDuration;
	
	private List<ItemJson> items;

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

	public List<ItemJson> getItems() {
		return items;
	}

	public void setItems(List<ItemJson> items) {
		this.items = items;
	}
	
	
}
