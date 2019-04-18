package com.chinaunicom.js.common.core.tools.db.dialect.impl;

import com.chinaunicom.js.common.core.tools.db.Page;
import com.chinaunicom.js.common.core.tools.db.dialect.DialectName;
import com.chinaunicom.js.common.core.tools.db.sql.SqlBuilder;
import com.chinaunicom.js.common.core.tools.db.sql.Wrapper;

/**
 * MySQL方言
 * @author loolly
 *
 */
public class MysqlDialect extends AnsiSqlDialect{
	
	public MysqlDialect() {
		wrapper = new Wrapper('`');
	}

	@Override
	protected SqlBuilder wrapPageSql(SqlBuilder find, Page page) {
		return find.append(" LIMIT ").append(page.getStartPosition()).append(", ").append(page.getPageSize());
	}
	
	@Override
	public DialectName dialectName() {
		return DialectName.MYSQL;
	}
}
