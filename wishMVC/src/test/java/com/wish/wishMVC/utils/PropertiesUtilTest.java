package com.wish.wishMVC.utils;

import org.junit.Before;
import org.junit.Test;

public class PropertiesUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetValueByKeyDefaultFileName() {
		String key = "jdbc.driver";
		PropertiesUtil.loadProperties();
		String val = (String) PropertiesUtil.getValueByKey(key);
		System.out.println(val);
	}
	
	@Test
	public void testGetValueByKey() {
		String key = "jdbc.url";
		String fileName = "application.properties";
		PropertiesUtil.loadProperties(fileName);
		String val = (String) PropertiesUtil.getValueByKey(key);
		System.out.println(val);
	}

}
