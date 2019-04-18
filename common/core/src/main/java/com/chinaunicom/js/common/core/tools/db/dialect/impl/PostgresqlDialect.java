package com.chinaunicom.js.common.core.tools.db.dialect.impl;

import com.chinaunicom.js.common.core.tools.db.dialect.DialectName;
import com.chinaunicom.js.common.core.tools.db.sql.Wrapper;


/**
 * Postgree方言
 * @author loolly
 *
 */
public class PostgresqlDialect extends AnsiSqlDialect{
	public PostgresqlDialect() {
		wrapper = new Wrapper('"');
	}

	@Override
	public DialectName dialectName() {
		return DialectName.POSTGREESQL;
	}
}
