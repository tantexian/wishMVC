package com.wish.wishMVC.Annotatiaon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: Controller注解，该注解类处理sevlet请求
 * @author ttx
 * @since  2015年12月27日 下午11:42:07
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

}
