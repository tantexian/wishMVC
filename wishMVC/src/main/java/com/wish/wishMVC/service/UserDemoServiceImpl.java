package com.wish.wishMVC.service;

import com.wish.wishMVC.Annotatiaon.IOCBean;
import com.wish.wishMVC.Annotatiaon.Inject;
import com.wish.wishMVC.dao.UserDemoDao;
import com.wish.wishMVC.domain.UserDemo;

@IOCBean
public class UserDemoServiceImpl implements UserDemoService{

	@Inject
	private UserDemoDao userDemoDao;
	public UserDemo getUserDemo(long userId) {
		return userDemoDao.getUserDemo(userId);
	}
}
