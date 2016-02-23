package com.wish.wishMVC.helper;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.wish.wishMVC.Annotatiaon.IOCBean;
import com.wish.wishMVC.Annotatiaon.Inject;

/**
 *@Description: TODO
 *依赖注入IOC的步骤：
 *1、将需要进行依赖注入的class增加@IOCBean注解（这样在包扫描时候则会将哪些些需要IOC管理的类给扫描保存起来）
 *2、如果该IOCBean类中有成员变量需要使用依赖注入则使用@Inject注解，这样成员变量则会自动从IOCBeanMap中获取,假若IOCBeanMap中没有该对象，则添加一个到该IOCBeanMap中
 *@author ttx
 *@since 2015年12月30日 下午7:50:49
 */
public class IOCHelper {
	
	private static Map<Class<?>, Object> IOCBeanMap = AnnotationHelper.getAnnotationClassAndObjectByClassType(IOCBean.class);
	
	@SuppressWarnings("unchecked")
	public static <T> T getIOCBeanByClassName(Class<T> cls){
		return (T) IOCBeanMap.get(cls);
	}

	static{
		for(Entry<Class<?>, Object> IOCBeanEntry: IOCBeanMap.entrySet()){
			Class<?> IOCBeanClass = IOCBeanEntry.getKey();
			Object IOCBeanInstance = IOCBeanEntry.getValue();
			//获取IOCBeanClass类的所有成员变量
			Field[] fields = IOCBeanClass.getDeclaredFields();
			for(Field field : fields){
				//如果被IOCBean注解的类的成员变量被@Inject注解了，则说明此成员变量希望通过IOCHelper类来依赖注入
				if(field.isAnnotationPresent(Inject.class)){
					//获取被注解成员变量将要被注入的对象的类（如果成员变量将要被注入的对象有多个实现类，则取第一个(暂且这样考虑)）
					Class<?> interfaceClass = field.getType();//获取该成员变量被定义的接口类型
					Set<Class<?>> implClassSet = ClassHelper.getAllImplClassByInterface(interfaceClass);
					Class<?> firstImplClass =null;
					for(Class<?> implClass : implClassSet){
						firstImplClass = implClass;
						break;
					}
					
					Object firstImplInstance = getIOCBeanByClassName(firstImplClass);
					if(firstImplInstance == null){
						try {
							throw new Exception("The inject class " + firstImplClass + "is not exits in IOCBean map!!!");
						} catch (Exception e) {
							e.printStackTrace();
						}
					
					}
					
					//设置该字段为可访问属性（负责private属性成员变量无法注入）
					field.setAccessible(true);
					try {
						field.set(IOCBeanInstance, firstImplInstance);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				
			}
		}
		
	}
}
