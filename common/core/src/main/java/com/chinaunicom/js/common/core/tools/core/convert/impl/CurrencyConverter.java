package com.chinaunicom.js.common.core.tools.core.convert.impl;

import java.util.Currency;

import com.chinaunicom.js.common.core.tools.core.convert.AbstractConverter;

/**
 * 货币{@link Currency} 转换器
 * 
 * @author Looly
 * @since 3.0.8
 */
public class CurrencyConverter extends AbstractConverter<Currency> {

	@Override
	protected Currency convertInternal(Object value) {
		return Currency.getInstance(value.toString());
	}

}
