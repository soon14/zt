package com.chinaunicom.js.common.core.tools.extra.tokenizer;

import com.chinaunicom.js.common.core.tools.core.exceptions.ExceptionUtil;
import com.chinaunicom.js.common.core.tools.core.util.StrUtil;

/**
 * 分词异常
 * 
 * @author Looly
 */
public class TokenizerException extends RuntimeException {
	private static final long serialVersionUID = 8074865854534335463L;

	public TokenizerException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public TokenizerException(String message) {
		super(message);
	}

	public TokenizerException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}

	public TokenizerException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public TokenizerException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}
}
