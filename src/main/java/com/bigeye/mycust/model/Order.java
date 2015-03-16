package com.bigeye.mycust.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity(name="Orders")
public class Order extends BaseEntity{

	private static final long serialVersionUID = 6579411186653159918L;
	
	private String editionCode;
	private String pricingDuration;

	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "order_id")
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
