package com.bigeye.mycust.model.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.commons.lang.ObjectUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bigeye.mycust.SpringMVCConstants;
import com.bigeye.mycust.model.Account;
import com.bigeye.mycust.model.BaseEntity;
import com.bigeye.mycust.model.Company;
import com.bigeye.mycust.model.Language;
import com.bigeye.mycust.validator.FieldMatch;

@Entity
@NamedQueries({
		@NamedQuery(name = "getUserByUserName", query = "select u from User u where u.username = :username and u.enabled = 1"),
		@NamedQuery(name = "getEnabledUsers", query = "select u from User u where u.enabled = 1 order by u.lastname") })
@FieldMatch.List({ @FieldMatch(first = "newPassword", second = "confirmPassword", message = "The password fields must match") })
public final class User extends BaseEntity implements UserDetails {

	private static final long serialVersionUID = -6749990853059667647L;

	@NotBlank
	@Email
	@Length(max = 250)
	@Column(unique = true, nullable = false, length = 250)
	private String username;

	@Column(nullable = true, length = 200)
	private String password;

	@Length(max = 100)
	@Column(nullable = true, length = 100)
	private String firstname;

	@Length(max = 100)
	@Column(nullable = true, length = 100)
	private String lastname;

	@Column
	private boolean enabled;

	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private Language language;
	
	@Column(unique = true)
	private String openId;
	
	@Column(unique = true)
	private String uuid;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private List<Authoritie> authorities;

	@Column
	private Date lastLoginTime;
	
	@Transient
	private String newPassword;

	@Transient
	private String confirmPassword;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "company_id", nullable=true, insertable = true, updatable = true)
	private Company company;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinTable(name = "account_user", joinColumns = { 
			@JoinColumn(name = "USER_ID", nullable = false, updatable = true) }, 
			inverseJoinColumns = { @JoinColumn(name = "ACCOUNT_ID", 
					nullable = false, updatable = true) })
    private List<Account> accounts;

	public User() {
		super(); 
		enabled = true;
	}

	public User(String username, String password, String firstname, String lastname, boolean enabled, String[] roles,
			Language language) {
		super();
		this.enabled = enabled;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		newPassword = password;
		confirmPassword = password;
		this.language = language;

		authorities = new ArrayList<Authoritie>();
		final List<String> authoritieKeys = Arrays.asList(roles);
		for (final String authoritieKey : authoritieKeys) {
			authorities.add(new Authoritie(this.username, authoritieKey));
		}
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		if (authorities != null) {
			for (final Authoritie authoritie : getAuthoritiesAsList()) {
				authoritie.setUsername(username);
			}
		}
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return new ArrayList<GrantedAuthority>(authorities);
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	private boolean haveRole(String role) {
		boolean found = false;
		if (authorities != null) {
			for (final Authoritie authoritie : getAuthoritiesAsList()) {
				if (authoritie.getAuthority().equals(role)) {
					found = true;
				}
			}
		}

		return found;
	}

	private void setRole(String role, boolean value) {
		Authoritie found = null;
		if (authorities != null) {
			for (final Authoritie authoritie : getAuthoritiesAsList()) {
				if (authoritie.getAuthority().equals(role)) {
					found = authoritie;
				}
			}
		}

		if (found == null) {
			if (value) {
				if (authorities == null) {
					authorities = new ArrayList<Authoritie>();
				}
				authorities.add(new Authoritie(username, role));
			}
		} else {
			if (!value) {
				authorities.remove(found);
			}
		}
	}

	public void setAuthorities(List<Authoritie> authorities) {
		this.authorities = authorities;
	}

	public List<Authoritie> getAuthoritiesAsList() {
		return authorities;
	}

	public boolean isRoleAdmin() {
		return haveRole(SpringMVCConstants.ROLE_ADMIN);
	}

	public void setRoleAdmin(boolean roleAdmin) {
		setRole(SpringMVCConstants.ROLE_ADMIN, roleAdmin);
	}

	public boolean isRoleManager() {
		return haveRole(SpringMVCConstants.ROLE_MANAGER);
	}

	public void setRoleManager(boolean roleManager) {
		setRole(SpringMVCConstants.ROLE_MANAGER, roleManager);
	}
	
	public boolean isRoleUser() {
		return haveRole(SpringMVCConstants.ROLE_USER);
	}
	
	public void setRoleUser(boolean roleUser) {
		setRole(SpringMVCConstants.ROLE_USER, roleUser);
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLoginTime() {
		return (Date) ObjectUtils.clone(lastLoginTime);
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = (Date) ObjectUtils.clone(lastLoginTime);
	}
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	
}
