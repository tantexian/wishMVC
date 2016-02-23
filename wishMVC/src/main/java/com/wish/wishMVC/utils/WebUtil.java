package com.wish.wishMVC.utils;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wish.wishMVC.base.Result;

public class WebUtil extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static HttpServletRequest request;

	public static void writeJSON(HttpServletResponse response, Result result) {
		try {
			response.getWriter().print(result.toJson());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getContextPath(){
		return request.getContextPath();
		
	}
	
}
