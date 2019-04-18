package com.chinaunicom.js.common.core.tools.bloomfilter.filter;

import com.chinaunicom.js.common.core.tools.core.util.HashUtil;

public class FNVFilter extends AbstractFilter {

	public FNVFilter(long maxValue, int machineNum) {
		super(maxValue, machineNum);
	}

	public FNVFilter(long maxValue) {
		super(maxValue);
	}

	@Override
	public long hash(String str) {
		return HashUtil.fnvHash(str);
	}

}
