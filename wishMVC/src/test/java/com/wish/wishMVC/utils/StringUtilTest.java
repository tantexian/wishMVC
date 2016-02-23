package com.wish.wishMVC.utils;

import org.junit.Before;
import org.junit.Test;

public class StringUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCamel2Underline() {
		String camelName = "theHellowWord";
		String underlineName = StringUtil.camel2Underline(camelName);
		System.out.println(underlineName);
	}

}
