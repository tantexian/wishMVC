package com.wish.wishMVC.helper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.wish.wishMVC.domain.BaseDomain;
import com.wish.wishMVC.utils.StringUtil;

public class EntityHelper {
	
	 private static final Map<Class<?>, Map<String, String>> entityMap = new HashMap<Class<?>, Map<String, String>>();
	 
	 public static Map<Class<?>, Map<String, String>> getEntityMap(){
		 //获取所有的entity类
		 Set<Class<?>> entityClasseList = ClassHelper.getAllChildClassBySuper(BaseDomain.class);
		 for(Class<?> entityClass : entityClasseList){
			 Field[] fields = entityClass.getDeclaredFields();
			 for(Field field : fields){
				 String filedName = field.getName();
				 if("serialVersionUID".equals(filedName)){
					 continue;
				 }
				 String columnName = StringUtil.camel2Underline(filedName);
				 
				 HashMap<String, String> camel2UnderlineMap = new HashMap<String, String>();
				//如果名称的驼峰格式与下划线格式不一致则保持映射关系
				 if(!filedName.equals(columnName)){
					 camel2UnderlineMap.put(filedName, columnName);
				 }
				 if(camel2UnderlineMap.size() != 0){
					 entityMap.put(entityClass, camel2UnderlineMap);
				 }
			 }
		 }
		 
		return entityMap;
	 }
}
