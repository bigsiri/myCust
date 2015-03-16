package com.bigeye.mycust.web.view.json;


public class AccountJson extends BaseEntityJson{
	private static final long serialVersionUID = 7038248980888468441L;
	
	private String accountIdentifier;
	private String status;
	private UserJson creator;
	private CompanyJson company;
	private OrderJson order;

	public String getAccountIdentifier()
	{
		return accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier)
	{
		this.accountIdentifier = accountIdentifier;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public UserJson getCreator() {
		return creator;
	}

	public void setCreator(UserJson creator) {
		this.creator = creator;
	}

	public CompanyJson getCompany() {
		return company;
	}

	public void setCompany(CompanyJson company) {
		this.company = company;
	}

	public OrderJson getOrder() {
		return order;
	}

	public void setOrder(OrderJson order) {
		this.order = order;
	}
	
	
}
