package com.wish.wishMVC.utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wish.wishMVC.helper.DBHelper;

public class DBUtil {

	private static final QueryRunner queryRunner = DBHelper.getQueryRunner();
	
	public static Object[] queryArray(String sql, Object... params){
		Object[] result = null;
		try {
			result = queryRunner.query(sql, new ArrayHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	 
	
	public static List<Object[]> queryArrayList(String sql, Object... params){
		List<Object[]> result = null;
		try {
			result = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Map<String, Object> queryMap(String sql, Object... params){
		Map<String, Object> result = null;
		try {
			result = queryRunner.query(sql, new MapHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static List<Map<String, Object>> queryMapList(String sql, Object... params){
		List<Map<String, Object>> result = null;
		try {
			result = queryRunner.query(sql, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static <T> T queryBean(String sql, Class<T> cls, Map<String, String> map, Object... params){
		T result = null;
		try {
			if(!MapUtil.isEmpty(map)){
				result =  queryRunner.query(sql, new BeanHandler<T>(cls, new BasicRowProcessor(new BeanProcessor(map))), params);
			}else{
				result =  queryRunner.query(sql, new BeanHandler<T>(cls), params);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static <T> List<T> queryBeanList(String sql, Class<T> cls,
			Map<String, String> map, Object... params) {
		List<T> result = null;

		try {
			if (!MapUtil.isEmpty(map)) {
				result = queryRunner.query(sql, new BeanListHandler<T>(cls,
						new BasicRowProcessor(new BeanProcessor(map))), params);
			} else {
				result = queryRunner.query(sql, new BeanListHandler<T>(cls),
						params);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static <T> T queryColumn(String sql, String column, Object...params){
		T result = null;
		try {
			result = queryRunner.query(sql, new ScalarHandler<T>(column), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static <T> List<T> queryColumnList(String sql, String column, Object...params){
		List<T> result = null;
		try {
			result = queryRunner.query(sql, new ColumnListHandler<T>(column), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	 // 查询指定列名对应的记录映射
    public static <T> Map<T, Map<String, Object>> queryKeyMap(String sql, String column, Object... params) {
        Map<T, Map<String, Object>> result = null;
        try {
            result = queryRunner.query(sql, new KeyedHandler<T>(column), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
 
    // 更新（包括 UPDATE、INSERT、DELETE，返回受影响的行数）
    public static int update(String sql, Object... params) {
        int result = 0;
        try {
            result = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
