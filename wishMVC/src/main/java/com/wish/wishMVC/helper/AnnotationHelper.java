package com.wish.wishMVC.helper;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHelper {

	//扫描获取整个项目根包路径下的所有class
	private static final String BasePackagePath = "com.wish.wishMVC";
	private static final Set<Class<?>> allClassSet = ClassHelper.getClassSetByPackageName(BasePackagePath, true);
	
	public static Map<Class<?>, Object> getAnnotationClassAndObjectByClassType(Class<? extends Annotation> annotationClass){
		Map<Class<?>, Object> classAndObjectMap = new HashMap<Class<?>, Object>(); 
		for(Class<?> cls : allClassSet){
			if(cls.isAnnotationPresent(annotationClass)){
				try {
					classAndObjectMap.put(cls, cls.newInstance());
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			
		}
		return classAndObjectMap;
	}
	
}
