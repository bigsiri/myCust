package com.bigeye.mycust.dozer.beans;

import java.util.List;

import com.bigeye.mycust.web.view.json.AccountJson;

public class ListAccountsJson {
	private List<AccountJson> data;

	public List<AccountJson> getData() {
		return data;
	}

	public void setData(List<AccountJson> data) {
		this.data = data;
	}
	
	
}
