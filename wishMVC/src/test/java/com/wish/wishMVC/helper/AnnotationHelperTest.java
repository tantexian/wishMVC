package com.wish.wishMVC.helper;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.wish.wishMVC.Annotatiaon.IOCBean;
import com.wish.wishMVC.controller.UserDemoController;

public class AnnotationHelperTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetAnnotationClassAndObjectByClassType() {
		Map<Class<?>, Object> classMap = AnnotationHelper.getAnnotationClassAndObjectByClassType(IOCBean.class);
		System.out.println(classMap);
		
		boolean isAnnotation = UserDemoController.class.isAnnotationPresent(IOCBean.class);
		System.out.println(isAnnotation);
	}

}
