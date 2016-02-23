package com.wish.wishMVC.helper;

import com.wish.wishMVC.utils.PropertiesUtil;

public class SQLHelper {
	
	public static <T> Object getSql(String key){
		PropertiesUtil.loadProperties("src/main/java/com/wish/wishMVC/dao/sql.properties");
		return PropertiesUtil.getValueByKey(key);
	}
}
