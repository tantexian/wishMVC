package com.wish.wishMVC.Annotatiaon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.wish.wishMVC.base.RequestMethod;


/**
 * @Description: 自定义Request注解
 * @author ttx
 * @since  2015年12月21日 下午9:47:42
 * 2015-12-29 16:05:36 目前暂且只支持restful风格的url请求
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
	
	String url();
	RequestMethod[] method() default {};
}
