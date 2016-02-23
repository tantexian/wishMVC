package com.wish.wishMVC.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	private static final Properties properties = new Properties();
	
	private static String fileName = "important.properties";
	
	public static void loadProperties(){
		InputStream inStream = null;
		try {
			inStream = new BufferedInputStream(new FileInputStream(fileName));
			properties.load(inStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadProperties(String fileName){
		InputStream inStream = null;
		try {
			inStream = new BufferedInputStream(new FileInputStream(fileName));
			properties.load(inStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static <T> Object getValueByKey(String key){
		return properties.get(key);
	}
	
	public Properties getProperties(){
		return properties;
	}

	public static String getFileName() {
		return fileName;
	}

	public static void setFileName(String fileName) {
		PropertiesUtil.fileName = fileName;
	}
}
