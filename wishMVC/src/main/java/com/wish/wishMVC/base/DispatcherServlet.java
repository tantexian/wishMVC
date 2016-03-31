package com.wish.wishMVC.base;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wish.wishMVC.helper.ControllerHelper;
import com.wish.wishMVC.helper.IOCHelper;
import com.wish.wishMVC.utils.WebUtil;

/**
 * @Description: servlet即 server+applet 运行在服务器端的小程序，
 *               能够接收和处理http请求，交互式地浏览和修改数据，生成动态 Web内容
 *               
 * ---- 1、整个 Web 应用中，只有一个 Servlet，它就是 DispatcherServlet集成自HttpServlet。它拦截了所有的请求
 *   -- 2、使用到了Serlvet3.0新特性注解@WebServlet，此方法拦截处理所有的请求，配置为@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
 *   -- 3、通过request.getMethod()、request.getPathInfo()获取SOAP的请求url、及请求方法
 *   -- 4、获取被@Controller注解的所有类，遍历该控制类中所有使用了@RequestMapping注解的方法即：method.isAnnotationPresent(RequestMapping.class)
 *   -- 5、将@RequestMapping(url="/userDemo/id/{id}", method=RequestMethod.GET)注解进行解析，全部保存到controllerMap中
 *   -- 6、再讲当前的url请求及请求方法（get/post/put等），与controllerMap中保存的映射进行匹配。
 *   -- 7、如果匹配成功：则使用controllerMethod.invoke(controllerInstance, needObjParamList.toArray());调用对应controller注解方法
 * 
 * @author ttx
 * @since 2015-12-17 00:30:07 
 *
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public void init(ServletConfig config) throws ServletException {
		// 用 Default Servlet 来映射静态资源
		ServletContext servletContext = config.getServletContext();
		//获取 Default Servlet 处理请求的 servletRegistration 对象
		ServletRegistration servletRegistration = servletContext.getServletRegistration("default");
		//将静态资源url添加到servletRegistration对象，后续对于静态资源的处理则直接默认处理
		servletRegistration.addMapping("/static/*");
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		
		String currentRequestMethod = request.getMethod();
		String currentRequestUrl = request.getPathInfo();
		
		//此处用来获取以soap方式请求的url?后面参数名字
		//Enumeration<String> paramNames  = request.getParameterNames();
		
		//获取并遍历 Controller 映射
		Map<RequestBean, ControllerBean> controllerMap = ControllerHelper.getControllerMap();

		for (Map.Entry<RequestBean, ControllerBean> controllerEntry : controllerMap
				.entrySet()) {
			// 从 RequestBean 中获取 Request 相关属性
			RequestBean requestBean = controllerEntry.getKey();
			String requestURL = requestBean.getRequestURL(); // 正则表达式
			
			//判断当前请求方法是否与当前遍历的注解@RequestMapping对象一致
			RequestMethod[] requestMethods = requestBean.getRequestMethods();			
			boolean isMethodMatch = false;
			for(RequestMethod requestMethod : requestMethods){
				if(requestMethod.toString().equals(currentRequestMethod)){
					isMethodMatch = true;
				}
			}
			//假若判断当前请求方法是否与当前遍历的注解@RequestMapping对象不一致，则说明不匹配，直接跳出循环，进行下一个对象匹配
			if(isMethodMatch == false){
				break;
			}
			
			//判断当前请求url是否与当前遍历的注解@RequestMapping对象一致
			//处理RequestMapping例子："/useDemo/userName/{userName}/password/{password}"，其中{}内都为占字符
			//真实请求的url为：/useDemo/userName/ttx/password/123456
			//替换为正则表达式模式regexRequestUrl为："/useDemo/userName/\\w+/password\\w+";
			String requestUrl = requestURL;
			boolean isUrlMatch = false;
			//其中在java中由于转移\\\\表示两个个\\，而在正则表达式中\也表示转移符号，因此\\转换到正则表达式则为一个\
			//下面将RequestMapping字符串中的{userName}、{password}等转换为正则表达式\\w+
			String regexRequestUrl = requestUrl.replaceAll("\\{\\w+\\}", "(\\\\w+)");
			Matcher matcher = Pattern.compile(regexRequestUrl).matcher(currentRequestUrl);
			List<String> stringParamList = new ArrayList<String>();
			
			if(matcher.matches()){
				isUrlMatch = true;
				for(int i = 1; i <= matcher.groupCount(); i++){
					Object numOrStringObj = matcher.group(i); 
					stringParamList.add((String) numOrStringObj);
				}
			}else{
				//不匹配则此处此轮循环
				break;
			}
			
			//请求方法和URL同时匹配，则调用匹配方法
			if(isUrlMatch && isMethodMatch){
				ControllerBean controllerBean = controllerEntry.getValue();
				// 从 ControllerBean 中获取 Controller 相关属性
				Class<?> controllerClass = controllerBean.getControllerClass();
				Method controllerMethod = controllerBean.getControllerMethod();
				
				//将paramList里面的numOrStringObj根据参数类型转换为相应的java对象
				Class<?>[] paramTypes = controllerMethod.getParameterTypes();
				List<?> needObjParamList = castObj2NeedObjList(stringParamList, paramTypes);
				
				try {
					// 创建 Controller 实例
					//Object controllerInstance = controllerClass.newInstance();
					
					Object controllerInstance = IOCHelper.getIOCBeanByClassName(controllerClass);
					// 调用 Controller 方法（传入请求参数）
					Object controllerMethodResult = controllerMethod.invoke(controllerInstance, needObjParamList.toArray());
					if (controllerMethodResult instanceof Result) {
						// 获取 Controller 方法返回值
						Result result = (Result) controllerMethodResult;
						// 将返回值转为 JSON 格式并写入 Response 中
						WebUtil.writeJSON(response, result);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 *@Description: TODO 将请求url中的字符串参数，根据controller对应的将被调用的方法的对应参数格式进行转换
	 * @param stringParamList
	 * @param paramTypes
	 * @return
	 *@author ttx
	 *@since 2015年12月31日 下午1:14:57
	 */
	private List<?> castObj2NeedObjList(List<String> stringParamList, Class<?>[] paramTypes){
		List<Object> ParamList = new ArrayList<Object>();
		for(int i = 0; i < paramTypes.length; i++){
			Class<?> cls = paramTypes[i];
			String strParam = stringParamList.get(i);
			if(cls.equals(Integer.class)){
				ParamList.add(Integer.parseInt(strParam));
			}else if(cls.equals(Long.class)){
				ParamList.add(Long.parseLong(strParam));
			}else if(cls.equals(Double.class)){
				ParamList.add(Double.parseDouble(strParam));
			}else if(cls.equals(String.class)){
				ParamList.add(strParam);
			}
		}
		
		return ParamList;
	}
}
