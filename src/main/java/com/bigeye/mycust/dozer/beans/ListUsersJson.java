package com.bigeye.mycust.dozer.beans;

import java.util.List;

import com.bigeye.mycust.web.view.json.UserJson;

public class ListUsersJson {
	private List<UserJson> data;

	public List<UserJson> getData() {
		return data;
	}

	public void setData(List<UserJson> data) {
		this.data = data;
	}
	
	
}
