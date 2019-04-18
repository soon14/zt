package com.chinaunicom.js.common.core.tools.db.dialect.impl;

import com.chinaunicom.js.common.core.tools.db.dialect.DialectName;
import com.chinaunicom.js.common.core.tools.db.sql.Wrapper;

/**
 * SqlLite3方言
 * @author loolly
 *
 */
public class Sqlite3Dialect extends AnsiSqlDialect{
	public Sqlite3Dialect() {
		wrapper = new Wrapper('[', ']');
	}
	
	@Override
	public DialectName dialectName() {
		return DialectName.SQLITE3;
	}
}
