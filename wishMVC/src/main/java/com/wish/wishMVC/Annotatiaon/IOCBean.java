package com.wish.wishMVC.Annotatiaon;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * @Description: 类上如果注解IOCBean，则说明该类里面有成员变量需要用到IOC依赖注入
 * @author ttx
 * @since  2015年12月27日 上午12:39:32
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface IOCBean {

}
