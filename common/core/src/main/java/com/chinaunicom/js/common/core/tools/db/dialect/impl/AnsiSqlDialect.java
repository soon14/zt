package com.chinaunicom.js.common.core.tools.db.dialect.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.chinaunicom.js.common.core.tools.core.collection.CollectionUtil;
import com.chinaunicom.js.common.core.tools.core.lang.Assert;
import com.chinaunicom.js.common.core.tools.core.util.ArrayUtil;
import com.chinaunicom.js.common.core.tools.core.util.StrUtil;
import com.chinaunicom.js.common.core.tools.db.DbRuntimeException;
import com.chinaunicom.js.common.core.tools.db.Entity;
import com.chinaunicom.js.common.core.tools.db.Page;
import com.chinaunicom.js.common.core.tools.db.StatementUtil;
import com.chinaunicom.js.common.core.tools.db.dialect.Dialect;
import com.chinaunicom.js.common.core.tools.db.dialect.DialectName;
import com.chinaunicom.js.common.core.tools.db.sql.Condition;
import com.chinaunicom.js.common.core.tools.db.sql.LogicalOperator;
import com.chinaunicom.js.common.core.tools.db.sql.Query;
import com.chinaunicom.js.common.core.tools.db.sql.SqlBuilder;
import com.chinaunicom.js.common.core.tools.db.sql.Wrapper;

/**
 * ANSI SQL 方言
 * 
 * @author loolly
 *
 */
public class AnsiSqlDialect implements Dialect {

	protected Wrapper wrapper = new Wrapper();

	@Override
	public Wrapper getWrapper() {
		return this.wrapper;
	}

	@Override
	public void setWrapper(Wrapper wrapper) {
		this.wrapper = wrapper;
	}

	@Override
	public PreparedStatement psForInsert(Connection conn, Entity entity) throws SQLException {
		final SqlBuilder insert = SqlBuilder.create(wrapper).insert(entity, this.dialectName());

		return StatementUtil.prepareStatement(conn, insert);
	}

	@Override
	public PreparedStatement psForInsertBatch(Connection conn, Entity... entities) throws SQLException {
		if (ArrayUtil.isEmpty(entities)) {
			throw new DbRuntimeException("Entities for batch insert is empty !");
		}
		// 批量
		final SqlBuilder insert = SqlBuilder.create(wrapper).insert(entities[0], this.dialectName());

		final PreparedStatement ps = StatementUtil.prepareStatement(conn, insert.build());
		for (Entity entity : entities) {
			StatementUtil.fillParams(ps, CollectionUtil.valuesOfKeys(entity, insert.getFields()));
			ps.addBatch();
		}
		return ps;
	}

	@Override
	public PreparedStatement psForDelete(Connection conn, Query query) throws SQLException {
		Assert.notNull(query, "query must not be null !");

		final Condition[] where = query.getWhere();
		if (ArrayUtil.isEmpty(where)) {
			// 对于无条件的删除语句直接抛出异常禁止，防止误删除
			throw new SQLException("No 'WHERE' condition, we can't prepared statement for delete everything.");
		}
		final SqlBuilder delete = SqlBuilder.create(wrapper).delete(query.getFirstTableName()).where(LogicalOperator.AND, where);

		return StatementUtil.prepareStatement(conn, delete);
	}

	@Override
	public PreparedStatement psForUpdate(Connection conn, Entity entity, Query query) throws SQLException {
		Assert.notNull(query, "query must not be null !");

		Condition[] where = query.getWhere();
		if (ArrayUtil.isEmpty(where)) {
			// 对于无条件的删除语句直接抛出异常禁止，防止误删除
			throw new SQLException("No 'WHERE' condition, we can't prepare statement for update everything.");
		}

		final SqlBuilder update = SqlBuilder.create(wrapper).update(entity).where(LogicalOperator.AND, where);

		return StatementUtil.prepareStatement(conn, update);
	}

	@Override
	public PreparedStatement psForFind(Connection conn, Query query) throws SQLException {
		Assert.notNull(query, "query must not be null !");

		final SqlBuilder find = SqlBuilder.create(wrapper).query(query);

		return StatementUtil.prepareStatement(conn, find);
	}

	@Override
	public PreparedStatement psForPage(Connection conn, Query query) throws SQLException {
		// 验证
		if (query == null || StrUtil.hasBlank(query.getTableNames())) {
			throw new DbRuntimeException("Table name must not be null !");
		}

		final Page page = query.getPage();
		if (null == page) {
			// 无分页信息默认使用find
			return this.psForFind(conn, query);
		}

		SqlBuilder find = SqlBuilder.create(wrapper).query(query).orderBy(page.getOrders());

		// 根据不同数据库在查询SQL语句基础上包装其分页的语句
		find = wrapPageSql(find, page);

		return StatementUtil.prepareStatement(conn, find);
	}

	/**
	 * 根据不同数据库在查询SQL语句基础上包装其分页的语句<br>
	 * 各自数据库通过重写此方法实现最小改动情况下修改分页语句
	 * 
	 * @param find 标准查询语句
	 * @param page 分页对象
	 * @return 分页语句
	 * @since 3.2.3
	 */
	protected SqlBuilder wrapPageSql(SqlBuilder find, Page page) {
		// limit A offset B 表示：A就是你需要多少行，B就是查询的起点位置。
		return find.append(" limit ").append(page.getPageSize()).append(" offset ").append(page.getStartPosition());
	}

	@Override
	public PreparedStatement psForCount(Connection conn, Query query) throws SQLException {
		query.setFields(CollectionUtil.newArrayList("count(1)"));
		return psForFind(conn, query);
	}

	@Override
	public DialectName dialectName() {
		return DialectName.ANSI;
	}

	// ---------------------------------------------------------------------------- Protected method start
	// ---------------------------------------------------------------------------- Protected method end
}
