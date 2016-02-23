package com.wish.wishMVC.helper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import com.wish.wishMVC.base.ControllerBean;
import com.wish.wishMVC.base.RequestBean;

public class ControllerHelperTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetControllerMap() {
		Map<RequestBean, ControllerBean> map = ControllerHelper.getControllerMap();
		System.out.println(map);
	}

	@Test
	public void testReg(){
		String str = "GET:/useDemo/userName/{userName}/password/{password}";
		String regex = "\\w+:\\/\\w+";
		boolean isMatch = str.matches(regex);
		System.out.println("isMatch = " + isMatch);
		
		str.replaceAll("\\{\\w+\\}", "\\(\\w+)");
		System.out.println(str);
	}
	
	/**
	 *@Description: TODO
	 *
	 *@author ttx
	 *@since 2015年12月29日 下午4:36:06
	 *判断当前请求url是否与当前遍历的注解@RequestMapping对象一致
	 *处理RequestMapping例子："/useDemo/userName/{userName}/password/{password}"，其中{}内都为占字符
	 *真实请求的url为："/useDemo/userName/ttx/password/123456"
	 */
	@Test
	public void testUrlMatch(){
		System.out.println("testUrlMatch start -------------------------------\n");
		String str1 = "/useDemo/userName/{userName}/password/{password}";
		String str2 = "/useDemo/userName/ttx/password/123456";
		
		String regStr = "/useDemo/userName/\\w+/password/\\w+";
		Matcher matcher = Pattern.compile(regStr).matcher(str2);
		
		
		System.out.println(" now isMatch = " + matcher.matches());
		
		String getStr = str1.replaceAll("\\{\\w+\\}", "\\\\\\\\w+");
		
		System.out.println("getStr == " + getStr);
		
//		String testStr = "/useDemo/{userName}";
//		Matcher match1 = Pattern.compile("\\/*.\\{*\\}").matcher(testStr);
//		
//		System.out.println("match1 = " + match1.matches());
		
		String regStr1 = "\\\\{\\\\w+\\\\}";
		System.out.println("regStr1 == " + regStr1);
		
		String str_test = "/useDemo/userName/{userName}/password/{password}";
		
		Matcher matcher1 = Pattern.compile("\\{(\\w+)\\}").matcher(str_test);
		
		System.out.println("???match == " + matcher1.matches());
		System.out.println("matchgroup1 =" + matcher1.group());
		
		System.out.println("testUrlMatch end  -------------------------------\n");
	}

	
	@Test
	public void testInvoke(){
		try {
			// 创建 Controller 实例
			Object controllerInstance = ControllerHelperTest.class.newInstance();
			Method method = ControllerHelperTest.class.getMethod("testCall", Integer.class, Long.class);
			// 调用 Controller 方法（传入请求参数）
			List list = new ArrayList<Object>();
			list.add(Integer.parseInt("6"));
			list.add(Long.parseLong("3"));

			long result = (Long) method.invoke(controllerInstance, list.toArray());
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public Long testCall(Integer a, Long b){
		return a * b* 100;
	}
}





























