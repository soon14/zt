package com.chinaunicom.js.common.core.tools.log.dialect.console;

import com.chinaunicom.js.common.core.tools.log.Log;
import com.chinaunicom.js.common.core.tools.log.LogFactory;

/**
 * 利用System.out.println()打印日志
 * @author Looly
 *
 */
public class ConsoleLogFactory extends LogFactory {
	
	public ConsoleLogFactory() {
		super("Hutool Console Logging");
	}

	@Override
	public Log createLog(String name) {
		return new ConsoleLog(name);
	}

	@Override
	public Log createLog(Class<?> clazz) {
		return new ConsoleLog(clazz);
	}

}
