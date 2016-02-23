package com.wish.wishMVC.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class XmlUtilTest {
	
	@Test
	public void testXmlUtil(){
		HashMap<Integer,ArrayList<String>> hashMap = new HashMap<Integer, ArrayList<String>>();
		ArrayList<String> strList = new ArrayList<String>();
		strList.add("Hello");
		hashMap.put(1, strList);
		strList.clear();
		strList.add(" word!");
		hashMap.put(2, strList);
		
		String xml = XmlUtil.toXml(hashMap);
		System.out.println(xml);
	}
}
