package com.wish.wishMVC.utils;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class ClassUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetClassSetByPackageName() {
		String packageName = "com.wish.wishMVC";
		boolean isRecursive =true;
		Set<Class<?>> classSet = ClassUtil.getClassSetByPackageName(packageName, isRecursive);
		System.out.println(classSet);
	}

}
