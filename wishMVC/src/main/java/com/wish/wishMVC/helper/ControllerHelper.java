package com.wish.wishMVC.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.wish.wishMVC.Annotatiaon.Controller;
import com.wish.wishMVC.Annotatiaon.RequestMapping;
import com.wish.wishMVC.base.ControllerBean;
import com.wish.wishMVC.base.RequestBean;
import com.wish.wishMVC.base.RequestMethod;


/**
 *@Description: TODO将所有controller的@RequestMapping("GET:/useDemo/{id}")映射关系注解保存到map中
 *@author ttx
 *@since 2015年12月29日 下午3:08:27
 */
public class ControllerHelper {
	
	private static final Map<com.wish.wishMVC.base.RequestBean, ControllerBean> controllerMap = new HashMap<RequestBean, ControllerBean>();
	
	static{
		Set<Class<?>> controllerClassSet = ClassHelper.getClassSetWithAnnotationClass(Controller.class);
		for(Class<?> cls : controllerClassSet){
			//获取controller类中所有的方法
			Method[] methods = cls.getMethods();
			for(Method method : methods){
				//获取被RequestMapping注解的方法
				if(method.isAnnotationPresent(RequestMapping.class)){
					//获取当前方法被RequestMapping注解的值
					RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
					String url = requestMapping.url();
					RequestMethod[] requestMethos = requestMapping.method();
	
					RequestBean requestBean = new RequestBean(requestMethos, url);
					ControllerBean controllerBean = new ControllerBean(cls, method);
					controllerMap.put(requestBean, controllerBean);
				}
			}
		}
	}

	public static Map<com.wish.wishMVC.base.RequestBean, ControllerBean> getControllerMap(){
		return controllerMap;
	}
}
