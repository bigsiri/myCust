package com.bigeye.mycust.web.view.json;

import java.io.Serializable;

public abstract class BaseEntityJson implements Serializable, Cloneable {
	private static final long serialVersionUID = 3423804561003993528L;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
