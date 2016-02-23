package com.wish.wishMVC;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Set;

import org.junit.Test;

import com.wish.wishMVC.Annotatiaon.IOCBean;
import com.wish.wishMVC.Annotatiaon.Inject;
import com.wish.wishMVC.helper.ClassHelper;
import com.wish.wishMVC.helper.IOCHelper;
import com.wish.wishMVC.service.UserDemoService;
import com.wish.wishMVC.service.UserDemoServiceImpl;

@IOCBean
public class ClassHelperTest{
	
	@Inject
	private UserDemoService userDemoService;
	
	@Test
    public void getClassListTest()
    {
    	
    	Set<Class<?>> classSet = ClassHelper.getAllChildClassBySuper(ClassHelperTest.class);
    	for(Class<?> clazz : classSet){
    		System.out.println(clazz);
    	}
    }
	
	@Test
	public void test1(){
		Class<UserDemoService> cls = UserDemoService.class;
		Type[] tmp = cls.getGenericInterfaces();
		Type tmp1 = cls.getGenericSuperclass();
		Class[] tmp2 = cls.getInterfaces();
		cls.isAssignableFrom(cls);
	}
	
	@Test
	public void test2(){
		Class<ClassHelperTest> cls = ClassHelperTest.class;
		Field[] fields = cls.getDeclaredFields();
		for(Field field : fields){
			if(field.isAnnotationPresent(Inject.class)){
				Object obj = null;
				Object value = null;
				field.setAccessible(true);
				try {
					obj = IOCHelper.getIOCBeanByClassName(cls);
					value = UserDemoServiceImpl.class.newInstance();
					field.set(obj, value);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		
		ClassHelperTest classHelperTest = IOCHelper.getIOCBeanByClassName(cls);
		UserDemoService newProductService = classHelperTest.userDemoService;
		System.out.println(newProductService);
	}
}