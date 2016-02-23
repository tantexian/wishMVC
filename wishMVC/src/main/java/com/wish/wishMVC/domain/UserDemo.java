package com.wish.wishMVC.domain;



/**
 * @Description: TODO其他id/name/created_time等字段，继承自BaseDomain
 * @author ttx
 * @since  2015年12月30日 下午11:28:37
 */
public class UserDemo extends BaseDomain{

	private static final long serialVersionUID = 1L;
	
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
