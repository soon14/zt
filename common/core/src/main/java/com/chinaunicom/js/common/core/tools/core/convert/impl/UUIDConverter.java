package com.chinaunicom.js.common.core.tools.core.convert.impl;

import java.util.UUID;

import com.chinaunicom.js.common.core.tools.core.convert.AbstractConverter;

/**
 * UUID对象转换器转换器
 * 
 * @author Looly
 * @since 4.0.10
 *
 */
public class UUIDConverter extends AbstractConverter<UUID> {

	@Override
	protected UUID convertInternal(Object value) {
		return UUID.fromString(convertToStr(value));
	}

}
