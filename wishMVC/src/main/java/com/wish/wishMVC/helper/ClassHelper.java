package com.wish.wishMVC.helper;

import java.lang.annotation.Annotation;
import java.util.Set;

import com.wish.wishMVC.utils.ClassUtil;


/**
 *@Description: 根据包路径名获取该包路径下面所有class
 *@author ttx
 *@since 2015年12月22日 下午6:30:50
 */
public class ClassHelper {
	
	
	/**
	 * @Description: TODO根据父类名称获取所有子类
	 * @param superClass
	 * @return
	 * @author ttx
	 * @since  2015年12月28日 上午10:35:25
	 */
	public static Set<Class<?>> getAllChildClassBySuper(Class<?> superClass){
		return ClassUtil.getAllChildClassBySuper(superClass);
	}
	
	/**
	 * @Description: TODO获取接口类interfaceClass所有的实现类
	 * @param interfaceClass
	 * @return
	 * @author ttx
	 * @since  2015年12月28日 上午12:17:39
	 */
	public static Set<Class<?>> getAllImplClassByInterface(Class<?> interfaceClass){
		return ClassUtil.getAllImplClassByInterface(interfaceClass);
	}
	
	/**
	 * @Description: TODO获取根据packageName及递归参数获取所有的class集
	 * @param packageName
	 * @param isRecursive
	 * @return
	 * @author ttx
	 * @since  2015年12月28日 上午12:17:41
	 */
	public static Set<Class<?>> getClassSetByPackageName(String packageName, boolean isRecursive){
		return ClassUtil.getClassSetByPackageName(packageName, isRecursive);
	}
	
	/**
	 * @Description: TODO获取项目中所有注解了annotationClass的所有class集
	 * @param annotationClass
	 * @return
	 * @author ttx
	 * @since  2015年12月28日 上午12:17:43
	 */
	public static Set<Class<?>> getClassSetWithAnnotationClass(Class<? extends Annotation> annotationClass){
		return ClassUtil.getClassSetWithAnnotationClass(annotationClass);
	}
}
