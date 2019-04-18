package com.chinaunicom.js.common.core.tools.bloomfilter.filter;

import com.chinaunicom.js.common.core.tools.core.util.HashUtil;

public class RSFilter extends AbstractFilter {

	public RSFilter(long maxValue, int machineNum) {
		super(maxValue, machineNum);
	}

	public RSFilter(long maxValue) {
		super(maxValue);
	}

	@Override
	public long hash(String str) {
		return HashUtil.rsHash(str) % size;
	}

}
