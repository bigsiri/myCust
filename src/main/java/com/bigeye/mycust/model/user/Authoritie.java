package com.bigeye.mycust.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;

import com.bigeye.mycust.model.BaseEntity;

@Entity
public final class Authoritie extends BaseEntity implements GrantedAuthority {

	private static final long serialVersionUID = 6496914414651672131L;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String authority;

	public Authoritie() {
		super();
	}

	public Authoritie(String username, String authority) {
		super();
		this.authority = authority;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
