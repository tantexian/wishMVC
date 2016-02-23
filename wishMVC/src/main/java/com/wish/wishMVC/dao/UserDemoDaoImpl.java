package com.wish.wishMVC.dao;

import com.wish.wishMVC.Annotatiaon.IOCBean;
import com.wish.wishMVC.domain.UserDemo;
import com.wish.wishMVC.helper.DBHelper;
import com.wish.wishMVC.helper.SQLHelper;

@IOCBean
public class UserDemoDaoImpl implements UserDemoDao{

	public UserDemo getUserDemo(long userId) {
		String sql = (String) SQLHelper.getSql("selectUserDemoById");
		return DBHelper.queryBean(UserDemo.class, sql, userId);
	}
}
