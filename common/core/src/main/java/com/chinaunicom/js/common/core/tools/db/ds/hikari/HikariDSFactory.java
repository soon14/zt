package com.chinaunicom.js.common.core.tools.db.ds.hikari;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import com.chinaunicom.js.common.core.tools.db.ds.AbstractDSFactory;
import com.chinaunicom.js.common.core.tools.setting.Setting;
import com.chinaunicom.js.common.core.tools.setting.dialect.Props;

/**
 * HikariCP数据源工厂类
 * 
 * @author Looly
 *
 */
public class HikariDSFactory extends AbstractDSFactory {

	public static final String DS_NAME = "HikariCP";

	public HikariDSFactory() {
		this(null);
	}

	public HikariDSFactory(Setting setting) {
		super(DS_NAME, HikariDataSource.class, setting);
	}

	@Override
	protected DataSource createDataSource(String jdbcUrl, String driver, String user, String pass, Setting poolSetting) {
		final Props config = new Props();
		config.putAll(poolSetting);

		config.put("jdbcUrl", jdbcUrl);
		if (null != driver) {
			config.put("driverClassName", driver);
		}
		if (null != user) {
			config.put("username", user);
		}
		if (null != pass) {
			config.put("password", pass);
		}

		final HikariDataSource ds = new HikariDataSource(new HikariConfig(config));
		return ds;
	}
}
