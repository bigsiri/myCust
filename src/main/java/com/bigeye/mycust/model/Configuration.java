package com.bigeye.mycust.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Configuration extends BaseEntity{
	private static final long serialVersionUID = 5976205168760567912L;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "entry_id", nullable=true, insertable = true, updatable = true)
	Entry entry;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "order")
	Account account;

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
	
}
