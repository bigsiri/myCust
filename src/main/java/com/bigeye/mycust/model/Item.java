package com.bigeye.mycust.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="Items")
public class Item extends BaseEntity {

	private static final long serialVersionUID = 8552918743035857789L;
	
	@Column(nullable = false, length = 10)
	private Long quantity;
	
	@Column(nullable = false, length = 15)
    private String unit;
    
	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	
}
