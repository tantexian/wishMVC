package com.wish.wishMVC.utils;

import java.util.Map;

public class MapUtil {

	public static boolean isEmpty(Map<?, ?> map){
		if(map == null || map.isEmpty()){
			return true;
		}
		return false;
	}
}
