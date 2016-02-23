package com.wish.wishMVC.service;

import org.junit.Before;
import org.junit.Test;

import com.wish.wishMVC.domain.UserDemo;

public class UserDemoServiceImplTest {
	
	private UserDemoService  userDemoService = new UserDemoServiceImpl();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetProduct() {
		long userId = 1;
		UserDemo product = userDemoService.getUserDemo(userId);
		System.out.println(product);
	}
}
