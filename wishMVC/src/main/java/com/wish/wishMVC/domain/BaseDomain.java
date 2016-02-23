package com.wish.wishMVC.domain;

import java.util.Date;

import com.wish.wishMVC.base.BaseBean;


public abstract class BaseDomain extends BaseBean{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private Date created_time;
	
	private Date updated_time;
	
	private Date deleted_time;
	
	private Integer deleted;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	public Date getUpdated_time() {
		return updated_time;
	}

	public void setUpdated_time(Date updated_time) {
		this.updated_time = updated_time;
	}

	public Date getDeleted_time() {
		return deleted_time;
	}

	public void setDeleted_time(Date deleted_time) {
		this.deleted_time = deleted_time;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
}
