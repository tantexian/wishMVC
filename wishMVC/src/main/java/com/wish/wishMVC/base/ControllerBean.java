package com.wish.wishMVC.base;

import java.lang.reflect.Method;

/**
 * @author ttx
 *
 */
public class ControllerBean {
	private Class<?> controllerClass;
	private Method controllerMethod;
	
	public ControllerBean(Class<?> controllerClass, Method controllerMethod) {
		this.controllerClass = controllerClass;
		this.controllerMethod = controllerMethod;
	}
	
	public void setControllerClass(Class<?> controllerClass) {
		this.controllerClass = controllerClass;
	}

	public void setControllerMethod(Method controllerMethod) {
		this.controllerMethod = controllerMethod;
	}


	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public Method getControllerMethod() {
		return controllerMethod;
	}

}
