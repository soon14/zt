package com.chinaunicom.js.common.core.tools.cron;

import com.chinaunicom.js.common.core.tools.core.util.StrUtil;

/**
 * 定时任务异常
 * @author xiaoleilu
 */
public class CronException extends RuntimeException{
	private static final long serialVersionUID = 8247610319171014183L;

	public CronException(Throwable e) {
		super(e.getMessage(), e);
	}
	
	public CronException(String message) {
		super(message);
	}
	
	public CronException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}
	
	public CronException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}
}
