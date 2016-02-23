package com.wish.wishMVC.helper;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;

import com.wish.wishMVC.dao.MyDataSource;
import com.wish.wishMVC.utils.DBUtil;
import com.wish.wishMVC.utils.PropertiesUtil;

public class DBHelper {
	static{
		String fileName = "important.properties";
		PropertiesUtil.setFileName(fileName);
		PropertiesUtil.loadProperties();
	}
	
	private static final String driver = (String) PropertiesUtil.getValueByKey("jdbc.driver");
	private static final String url = (String) PropertiesUtil.getValueByKey("jdbc.url");
	
	private static final MyDataSource dataSource = new MyDataSource();
	
	static {
		try {
			dataSource.setUrl(url);
			dataSource.setDriver(driver);
			dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
				
	private static final QueryRunner queryRunner = new QueryRunner(dataSource);
	
	public static QueryRunner getQueryRunner(){
		return queryRunner;
	}
	
	public static <T> T queryBean(Class<T> cls, String sql, Object... params){
		Map<String, String> map = EntityHelper.getEntityMap().get(cls);
		return DBUtil.queryBean(sql, cls, map, params);
	}
	
	// 执行查询语句（返回多个对象）
    public static <T> List<T> queryBeanList(Class<T> cls, String sql, Object... params) {
        Map<String, String> map = EntityHelper.getEntityMap().get(cls);
        return DBUtil.queryBeanList(sql, cls, map, params);
    }
 
    // 执行更新语句（包括 UPDATE、INSERT、DELETE）
    public static int update(String sql, Object... params) {
        return DBUtil.update(sql, params);
    }
}
