package com.chinaunicom.js.common.core.tools.db.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 结果集处理类 ，处理出的结果为Bean列表
 * 
 * @param <E> 处理对象类型
 * @author loolly
 * @since 3.1.0
 */
public class BeanListHandler<E> implements RsHandler<List<E>> {

	private Class<E> elementBeanType;

	/**
	 * 创建一个 BeanListHandler对象
	 * 
	 * @param <E> 处理对象类型
	 * @param beanType Bean类型
	 * @return BeanListHandler对象
	 */
	public static <E> BeanListHandler<E> create(Class<E> beanType) {
		return new BeanListHandler<E>(beanType);
	}

	/**
	 * 构造
	 * @param beanType Bean类型
	 */
	public BeanListHandler(Class<E> beanType) {
		this.elementBeanType = beanType;
	}

	@Override
	public List<E> handle(ResultSet rs) throws SQLException {
		return HandleHelper.handleRsToBeanList(rs, new ArrayList<E>(), elementBeanType);
	}
}
