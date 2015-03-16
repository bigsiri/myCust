package com.bigeye.mycust.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Entry extends BaseEntity{
	
	private static final long serialVersionUID = 8209501583087399614L;
	
	@Column(name="keyVal")
	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
