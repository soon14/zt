package com.chinaunicom.js.common.core.tools.core.convert.impl;

import com.chinaunicom.js.common.core.tools.core.convert.AbstractConverter;

/**
 * 字符串转换器
 * @author Looly
 *
 */
public class StringConverter extends AbstractConverter<String>{

	@Override
	protected String convertInternal(Object value) {
		return convertToStr(value);
	}

}
