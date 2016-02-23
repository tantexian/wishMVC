package com.wish.wishMVC.controller;

import com.wish.wishMVC.Annotatiaon.Controller;
import com.wish.wishMVC.Annotatiaon.IOCBean;
import com.wish.wishMVC.Annotatiaon.Inject;
import com.wish.wishMVC.Annotatiaon.RequestMapping;
import com.wish.wishMVC.base.RequestMethod;
import com.wish.wishMVC.base.Result;
import com.wish.wishMVC.domain.UserDemo;
import com.wish.wishMVC.service.UserDemoService;

//类上添加IOCBean注解，则说明此类里面有成员变量需要用到IOC依赖注入
@IOCBean
@Controller
public class UserDemoController extends BaseController{
	
	@Inject
	private UserDemoService useDemoService; 
	
	@RequestMapping(url="/userDemo/id/{id}", method=RequestMethod.GET)
	public Result getProductById(Long id) {
		Result result = new Result();
		UserDemo userDemo = useDemoService.getUserDemo(id);
		System.out.println("UserDemo == " + userDemo.toJson());
		result.setData(userDemo);
		return result;
	}
	
	@RequestMapping(url="/", method=RequestMethod.GET)
	public Result index() {
		Result result = new Result();
		result.setData("Welcome to visit wishMVC!!!");
		return result;
	}
}