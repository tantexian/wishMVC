package com.wish.wishMVC.base;

public class RequestBean {

	private RequestMethod[] requestMethods;
	private String requestURL;
	
	public RequestBean(RequestMethod[] requestMethods, String requestURL) {
		this.requestMethods = requestMethods;
		this.requestURL = requestURL;
	}

	public RequestMethod[] getRequestMethods() {
		return requestMethods;
	}

	public void setRequestMethods(RequestMethod[] requestMethods) {
		this.requestMethods = requestMethods;
	}

	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
}
