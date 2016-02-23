package com.wish.wishMVC.utils;

public class StringUtil {
	
	
	/**
	 * @Description: 将驼峰命名转变为下划线命名
	 * @param camelStr
	 * @return
	 * @author ttx
	 * @since  2015年12月23日 上午11:37:12
	 */
	public static String camel2Underline(String camelName){
		final String  UNDERLINE = "_";
		if (camelName == null || "".equals(camelName.trim())){  
	           return "";  
	       }  
	       int len = camelName.length();  
	       StringBuilder sb = new StringBuilder(len);  
	       for (int i = 0; i < len; i++) {  
	           char c = camelName.charAt(i);  
	           if (Character.isUpperCase(c)){  
	               sb.append(UNDERLINE);  
	               sb.append(Character.toLowerCase(c));  
	           }else{  
	               sb.append(c);  
	           }  
	       }  
	       return sb.toString(); 
	}
}
