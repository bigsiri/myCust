package com.bigeye.mycust.web.view.json;

public class ItemJson extends BaseEntityJson{
	private static final long serialVersionUID = -4452681995713209291L;

	private Long quantity;
	
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
