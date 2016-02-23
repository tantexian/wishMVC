package com.wish.wishMVC.helper;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.wish.wishMVC.domain.BaseDomain;
import com.wish.wishMVC.service.UserDemoService;

public class ClassHelperTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetAllImplClassByInterface() {
		Set set = ClassHelper.getAllImplClassByInterface(UserDemoService.class);
		System.out.println(set);
	}

	@Test
	public void testGetAllChildClassBySuper() {
		Set set = ClassHelper.getAllChildClassBySuper(BaseDomain.class);
		System.out.println(set.toString());
	}
}
