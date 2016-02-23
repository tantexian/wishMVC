package com.wish.wishMVC.helper;

import org.junit.Before;
import org.junit.Test;

import com.wish.wishMVC.Annotatiaon.IOCBean;
import com.wish.wishMVC.Annotatiaon.Inject;
import com.wish.wishMVC.base.Result;
import com.wish.wishMVC.controller.UserDemoController;
import com.wish.wishMVC.service.UserDemoService;

@IOCBean
public class IOCHelperTest {

	@Inject
	private UserDemoService userDemoService;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIOC() {
		Class<UserDemoController> cls = UserDemoController.class;
		//此处应该注意，通过IOCBean管理的对象，则应该使用getIOCBeanByClassName方式去获取
		UserDemoController userDemoControllerBean = IOCHelper.getIOCBeanByClassName(cls);
		long productId = 1;
		Result result = userDemoControllerBean.getProductById(productId);
		System.out.println(result.getData());
	}

}
