package com.bigeye.mycust.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@MappedSuperclass
public abstract class BaseEntity implements Serializable, Cloneable {

	private static final long serialVersionUID = 8128965653406223642L;

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;

	/**
	 * Create by
	 */
	@Column(name = "createdBy", nullable = false, updatable= false)
	private String createdBy;

	/**
	 * Creation date
	 */
	@Column(name = "creationDate", nullable = false, updatable= false)
	private Date creationDate;

	/**
	 * Modified by
	 */
	@Column(name = "modifiedBy", nullable = true)
	private String modifiedBy;

	/**
	 * Modification date
	 */
	@Column(name = "modificationDate", nullable = true)
	private Date modificationDate;

	@PrePersist
	protected void setCreationFields() {
		creationDate = new Date();
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			createdBy = "system";
		} else if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
			createdBy = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getUsername();
		} else {
			createdBy = "anonymous";
		}
	}

	@PreUpdate
	protected void setModificationFields() {
		modificationDate = new Date();
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			modifiedBy = "system";
		} else if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
			modifiedBy = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getUsername();
		} else {
			modifiedBy = "anonymous";
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return (Date) ObjectUtils.clone(creationDate);
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = (Date) ObjectUtils.clone(creationDate);
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModificationDate() {
		return (Date) ObjectUtils.clone(modificationDate);
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = (Date) ObjectUtils.clone(modificationDate);
	}

}
