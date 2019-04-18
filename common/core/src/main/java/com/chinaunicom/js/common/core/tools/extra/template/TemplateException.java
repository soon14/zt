package com.chinaunicom.js.common.core.tools.extra.template;

import com.chinaunicom.js.common.core.tools.core.exceptions.ExceptionUtil;
import com.chinaunicom.js.common.core.tools.core.util.StrUtil;

/**
 * 模板异常
 * 
 * @author xiaoleilu
 */
public class TemplateException extends RuntimeException {
	private static final long serialVersionUID = 8247610319171014183L;

	public TemplateException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public TemplateException(String message) {
		super(message);
	}

	public TemplateException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}

	public TemplateException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public TemplateException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}
}
