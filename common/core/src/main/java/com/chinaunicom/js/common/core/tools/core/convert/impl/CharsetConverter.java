package com.chinaunicom.js.common.core.tools.core.convert.impl;

import java.nio.charset.Charset;

import com.chinaunicom.js.common.core.tools.core.convert.AbstractConverter;
import com.chinaunicom.js.common.core.tools.core.util.CharsetUtil;

/**
 * 编码对象转换器
 * @author Looly
 *
 */
public class CharsetConverter extends AbstractConverter<Charset>{

	@Override
	protected Charset convertInternal(Object value) {
		return CharsetUtil.charset(convertToStr(value));
	}

}
