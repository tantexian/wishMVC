package com.wish.wishMVC.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WebUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetContextPath() {
		String contextPath = WebUtil.getContextPath();
		System.out.println(contextPath);
	}

}
