package com.wish.wishMVC.Annotatiaon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.wish.wishMVC.base.RequestMethod;


/**
 * @Description: 自定义Request注解
 * 
 * ---- 具体注解使用示例：@RequestMapping(url="/userDemo/id/{id}", method=RequestMethod.GET)
 *   -- 1、其中url，method对应为注解的两个方法
 *   -- 2、@Target(ElementType.METHOD)表示为方法基本注释
 *   -- 3、@Retention(RetentionPolicy.RUNTIME)表示注解的信息生命周期（例如只保存在class，source等，此处为，保留在虚拟机运行期间）
 *   
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
