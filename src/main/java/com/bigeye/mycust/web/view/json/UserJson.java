package com.bigeye.mycust.web.view.json;

import java.util.Date;


public class UserJson extends BaseEntityJson{
	private static final long serialVersionUID = 2550800922639399291L;

	private String username;

	private String password;

	private String firstname;

	private String lastname;

	private String openId;
	
	private String uuid;
	
	private CompanyJson company;
	
	private Date lastLoginTime;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public CompanyJson getCompany() {
		return company;
	}

	public void setCompany(CompanyJson company) {
		this.company = company;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	
}
