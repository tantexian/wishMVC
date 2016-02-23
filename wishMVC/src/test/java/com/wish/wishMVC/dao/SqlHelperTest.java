package com.wish.wishMVC.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import com.wish.wishMVC.helper.SQLHelper;

public class SqlHelperTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetSql() {
		String key = "selectUserDemoById";
		Object x = SQLHelper.getSql(key);
		System.out.println(x);
	}
	
	@Test
	public void test(){
		String fileName = "src/main/java/com/wish/wishMVC/dao/sql.properties";
		try {
			FileInputStream x = new FileInputStream(fileName);
			String str = x.toString();
			System.out.println(str);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
