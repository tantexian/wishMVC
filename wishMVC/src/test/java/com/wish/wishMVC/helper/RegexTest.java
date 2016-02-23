package com.wish.wishMVC.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class RegexTest {

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void test1(){
		String str = "/useDemo/userName/{userName}/password/{password}";
		System.out.println(str);
		//下面()则表示匹配子串，groupCount则是子串个数
		String pattern = ".*\\{(\\w+)\\}.*\\{(\\w+)\\}";
		
		String pattern2  = "/useDemo/userName/\\{(\\w+)\\}/password/\\{(\\w+)\\}";

		Pattern r = Pattern.compile(pattern2);
		Matcher m = r.matcher(str);
		System.out.println(m.matches());
		
		System.out.println("m.groupCount() = " + m.groupCount());
		
		System.out.println(m.group(1));
		
		System.out.println(m.group(2));
	}
	
	@Test
	public void test2(){
		String string1 = "/useDemo/userName/{userName}/password/{password}";
		
		String string2 = "/useDemo/userName/ttx/password/123456";
		
		
		
		String regexString = string1.replaceAll("\\{\\w+\\}", "(\\\\w+)");
		
		//regexString = "/useDemo/userName/(.*)/password/(.*)";
		
		System.out.println(regexString);
		
		Pattern pattern = Pattern.compile(regexString);
		Matcher matcher = pattern.matcher(string2);
		System.out.println(matcher.matches());
		
		
		for(int i = 1; i <= matcher.groupCount(); i++){
			System.out.println(matcher.group(i));
		}
		
	}
	
}





























